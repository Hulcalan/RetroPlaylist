/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retroplaylist;

import java.io.IOException;

/**
 *
 * @author Alan
 */
public class RetroPlaylist {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //clean this up
        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd .. && cd PlaylistDriver "
                + "&&cd dist && java -jar PlaylistDriver.jar\"");
    }
    
}
