/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.*;
import java.io.*;

import message.PortInfo;

 
public class CustomerService {

    public static void main(String[] args) throws IOException {
        boolean listening = true;
 
        try (ServerSocket serverSocket = new ServerSocket(PortInfo.PORT)){
            while (listening)
                new CSThread(serverSocket.accept()).start();
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("Could not listen on port: " + PortInfo.PORT);
            System.exit(-1);
        }
    }
}