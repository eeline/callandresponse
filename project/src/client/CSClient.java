/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eugene
 */

public class CSClient {
    public static void main(String[] args){
        try {
            CSProxy csProxy = new CSProxy();
            
            csProxy.sendInput();
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        }

    }
}   
