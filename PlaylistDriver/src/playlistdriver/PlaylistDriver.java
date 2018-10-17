/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistdriver;

import java.io.File;
import java.io.IOException;
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
        
        String Alan;
        File f;
        String[] paths;
        int check;
        check = 0;
         
       
        System.out.println("please enter a path: ");
        
        //this reads all the files in the folder
        Scanner scan = new Scanner(System.in);
        
        Alan = scan.nextLine();
        try{
            f = new File(Alan);
            
            paths = f.list();
            
            for(String path:paths){
                System.out.println(path);
            }
            
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch(Exception e){
            e.printStackTrace();
        }
              
    }
    /********** thinking out loud for retroarch playlist creator **************/
    //read folder method
        
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
