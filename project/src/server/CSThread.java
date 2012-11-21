/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;



import java.net.*;
import java.io.*;

import serialization.CustomerDAO;

import message.Message;
 
public class CSThread extends Thread implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3856023837619368781L;
	private static final int TRAV = 0;
    private static final int SAVE = 1;
    private static final int EXIT = 2;
    ObjectOutputStream out;
    ObjectInputStream in;
    private CustomerDAO cdao = new CustomerDAO();
 
    public CSThread(Socket socket) throws IOException {
        super("CSServer");
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
 
    public void run() {
        try {
            Object object;
            while ((object = in.readObject()) != null)
                handleMessage(object);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

                
    }
    
    private void handleMessage(Object object) throws IOException{
        Message message = (Message) object;
        
        switch(message.getMessage()){
            case TRAV:
                out.writeObject(new Message(TRAV, this.cdao));
            case SAVE:
                this.cdao = (CustomerDAO) message.getData();
                out.writeObject(SAVE);
            case EXIT:
                this.interrupt();
        }
    }
}

