/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistdriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

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

        String pathName;
        String[] fileNames;

        System.out.println("please enter a path: ");

        //this reads all the files in the folder
        Scanner scan = new Scanner(System.in);

        pathName = scan.nextLine();
        try {

            fileNames = readDir(pathName);

            for (String files : fileNames) {
                System.out.println(listEntry(files, 1));
                //System.out.println(files);
            }

            //Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch (Exception e) {
            System.out.println("The path \"" + pathName + "\" doesn't exist");
            e.printStackTrace();
        }
    }

    /**
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
     *
     * @param gameName - filename of the game.
     * @param console - determines which console it is
     * @return a list of files in the selected directory
     */
    public static String listEntry(String gameName, int console) {
        String entry, truncName, consoleName;
        int count = 0;
        Scanner scan = new Scanner(System.in);
        
        //console selector
        switch(console){
            case 1:  consoleName = "Nintendo - Nintendo Entertainment System";
                     break;
            default: consoleName = "unknown";
                    break;
        }
        
        //strips away access stuff from the file name.
        if (console < 8) {
            //assumes there is a paren... and everythign before is fine.
            //i'm sure there is a cleaner and faster way to do this.
            while (gameName.charAt(count) != '(')
                    //&&  gameName.charAt(count) != '.'
                    //&& count < gameName.length() - 1) 
            {
                count++;
            }
        truncName = gameName.substring(0, count);
        //manually enter for arcade games.
        } else {
            System.out.println(gameName);
            truncName = scan.nextLine();
        }
        //System.out.println(truncName);

        entry = "/roms/nes/" + gameName
                + "\n" + truncName
                + "\n/retoarch/cores/fceumm_libretro_libnx.nro"
                + "\n" + consoleName
                + "\nDETECT"
                + "\n" + consoleName;
        return entry;
    }

    /**
     * ******** thinking out loud for retroarch playlist creator *************
     */
    //print string method
    //choose "game name" for each file
    //can probably write to file after each game instead of one 
    //large array. 
    //write to file method
    //append print string to the file
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
    // use "game name" from print string to change name of file for 
    // boxart and title pictures. 
}
