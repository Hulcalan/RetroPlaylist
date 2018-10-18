/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistdriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author Alan
 */
public class PlaylistDriver {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) throws IOException {

        String pathName, output;
        String[] fileNames;
        output = "";
        System.out.println("please enter a path: ");

        //this reads all the files in the folder
        Scanner scan = new Scanner(System.in);

        pathName = scan.nextLine();
        try {

            fileNames = readDir(pathName);

            //appends all entries into one string
            for (String files : fileNames) {
                output = output + listEntry(files,1, 1, 1) +"\r\n";
            }

            System.out.println(output);
            PrintWriter writer = new PrintWriter("output/" + consoleSelect(1) + ".lpl");
            writer.println(output);
            writer.close();
            //Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch (FileNotFoundException e) {
            System.out.println("The path \"" + pathName + "\" doesn't exist");
            e.printStackTrace();
        }
    }

    /**
     * reads all the files in the directory and puts the restuls into a string
     * array
     *
     * @param pathName - name of the directory to read.
     * @return a list of files in the selected directory
     */
    public static String[] readDir(String pathName) {
        String[] gameList;
        File f = new File(pathName);
        gameList = f.list();
        return gameList;
    }

    /**
     * creates the format for each game to be put in the playslist
     *
     * @param gameName - filename of the game.
     * @param manual - user selects if wants to manual change game name
     *                 or is formatted like mine.
     * @param console - determines which console it is
     * @param core - used for core selection method
     * @return a list of files in the selected directory
     */
    public static String listEntry(String gameName,int manual, int console, int core) {
        String entry, truncName, consoleName, coreName;
        int count;
        Scanner scan = new Scanner(System.in);

        consoleName = consoleSelect(console);
        coreName = coreSelect(core);
        //System.out.println(consoleName);
        //strips away access stuff from the file name.
        if (manual != 0) {

            count = gameNameStrip(gameName);

            truncName = gameName.substring(0, count);
            //manually enter for arcade games.
        } else {
            truncName = scan.nextLine();
        }
        //System.out.println(truncName);

        entry = "/roms/nes/" + gameName
                + "\r\n" + truncName
                + "\r\n/retoarch/cores/" + coreName
                + "\r\n" + consoleName
                + "\r\nDETECT"
                + "\r\n" + consoleName + ".lpl";
        return entry;
    }

    public static String consoleSelect(int consoleSelect) {
        String consoleName;
        //System.out.println("In method consoleSelect(int) " + consoleSelect);
        //console selector
        switch (consoleSelect) {
            case 1:
                consoleName = "Nintendo - Nintendo Entertainment System";
                break;
            default:
                consoleName = "unknown";
                break;
        }
        return consoleName;

    }

    public static String coreSelect(int coreSelect) {
        String coreName;
        //System.out.println("In method consoleSelect(int) " + consoleSelect);
        //console selector
        switch (coreSelect) {
            case 1:
                coreName = "fceumm_libretro_libnx.nro";
                break;
            default:
                coreName = "unknown";
                break;
        }
        return coreName;

    }

    public static Integer gameNameStrip(String gameName) {
        //System.out.println("In method gameNameStrip(String) " + gameName);

        int count, exit;
        exit = 0;
        count = 0;

        //assumes there is a paren... and everythign before is fine.
        //i'm sure there is a cleaner and faster way to do this.
        while (exit != 1) {

            //could count right to left and subtract that number from
            // the string length and that might make it easier/faster/effcient
            //can probably clean this up
            if (gameName.charAt(count) != '('
                    && count != gameName.length() - 1) {
                count++;
            } else if (gameName.charAt(count) == '(') {
                exit = 1;
            } else {
                count++;
                exit = 1;
            }
        }
        return count;
    }
    /**
     * ******** thinking out loud for retroarch playlist creator *************
     */

    //write to file method
    //save to lpl file
    

    //ui method?
    //basic switch loop UI
    //user inputs rom root folder
    //mebbe use read folder to get the systems?
    //choose system
    //not be able to type in but a list to choose
    //choice auto file name for output
    //choice auto decides path name 
    //output file(s)
    //use array to sort many systems at once
    //or just out put the file as it's made so no need to store
    //method to file name change
    //use "game name" from print string to change name of file for 
    // boxart and title pictures. 
}
