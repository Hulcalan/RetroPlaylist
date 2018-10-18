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
                output = output + listEntry(files,0, 8, 6) +"\r\n";
            }

            //System.out.println(output);
            
            //add system to either console name or manual name for playlist.
            try (PrintWriter writer = new PrintWriter("output/" + consoleSelect(8)[0] + ".lpl")) {
                writer.println(output);
                //Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
            }
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
        String entry, truncName, coreName;
        String[] consoleName;
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
            System.out.println("\n" + gameName + "\n");
            truncName = scan.nextLine();
        }
        //System.out.println(truncName);

        entry = "/roms/" + consoleName[1] + "/" + gameName
                + "\r\n" + truncName
                + "\r\n/retroarch/cores/" + coreName
                + "\r\n" + consoleName[1] 
                + "\r\nDETECT"
                + "\r\n" + consoleName[0] + ".lpl";
        return entry;
    }

    public static String[] consoleSelect(int consoleSelect) {
        String[] consoleName = new String[2];
        //System.out.println("In method consoleSelect(int) " + consoleSelect);
        //console selector
        switch (consoleSelect) {
            case 1:
                consoleName[0] = "Nintendo - Nintendo Entertainment System";
                consoleName[1] = "nes";
            case 2:
                consoleName[0] = "Nintendo - Super Nintendo Entertainment System";
                consoleName[1] = "snes";
                break;
            case 3:
                consoleName[0] = "Nintendo - Game Boy";
                consoleName[1] = "gb";
                break;
            case 4:
                consoleName[0] = "Nintendo - Game Boy Color";
                consoleName[1] = "gbc";
                break;
            case 5:
                consoleName[0] = "Nintendo - Game Boy Advance";
                consoleName[1] = "gba";
                break;
            case 6:
                consoleName[0] = "Nintendo - Nintendo 64";
                consoleName[1] = "n64";
                break;
            case 7:
                consoleName[0] = "Sega - Mega Drive - Genesis";
                consoleName[1] = "gen";
                break;
            case 8:
                consoleName[0] = "Sony - PlayStation";
                consoleName[1] = "psx";
                break;
            case 9:
                consoleName[0] = "FB Alpha - Arcade Games";
                consoleName[1] = "fba";
                break;
            case 10:
                consoleName[0] = "MAME";
                consoleName[1] = "mame2003";
                break;
                
            default:
                consoleName[0] = "unknown";
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
                //nes
                coreName = "fceumm_libretro_libnx.nro";
                break;
            case 2:
                //fbalpha
                coreName = "fbalpha_libretro_libnx.nro";
                break;
            case 3:
                //gb and gbc
                coreName = "gearboy_libretro_libnx.nro";
                break;
            case 4:
                //mame2003
                coreName = "mame2003_libretro_libnx.nro";
                break;
            case 5:
                //n64
                coreName = "mupen64plus_libretro_libnx.nro";
                break;
            case 6:
                //psx
                coreName = "pcsx_rearmed_libretro_libnx.nro";
                break;
            case 7:
                //sega genesis
                coreName = "picodrive_libretro_libnx.nro";
                break;
            case 8:
                //snes
                coreName = "snes9x2010_libretro_libnx.nro";
                break;
            case 9:
                //gba
                coreName = "vba_next_libretro_libnx.nro";
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
                count--;
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
