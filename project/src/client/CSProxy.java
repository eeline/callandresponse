/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import serialization.CustomerDAO;

import collections.Traverser;

import datatypes.Customer;

import message.Message;

/**
 *
 * @author eugene
 */


public class CSProxy {

    
    enum State {FIRST_RUN, NAME_ENTERED, VALUE_ENTERED, DATA_SEND}
    private String[] menu;
    private String[] inputs;
    private Socket socket;
    boolean wasSaved = false;
    CustomerDAO cdao = new CustomerDAO();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private static final int TRAV = 0;
    private static final int SAVE = 1;
    private static final int EXIT = 2;

    
    CSProxy() throws IOException {
        this.socket = new Socket(InetAddress.getByName(null), message.PortInfo.PORT);
        out = new ObjectOutputStream(this.socket.getOutputStream());
        in  = new ObjectInputStream(this.socket.getInputStream());
        
        this.menu = new String[4];
        this.inputs = new String[4];
        
        this.menu[0] = "Type 0 to see customers  (WARNING: EMPTIES CUSTOMER LIST).";
        this.menu[1] = "Type 1 to enter Customer."; 
        this.menu[2] = "Type 2 to save list to server.";
        this.menu[3] = "Type EXIT to quit.";
        
        this.inputs[0] = "Input Name to find: ";
        this.inputs[1] = "Input Name: ";
        this.inputs[2] = "Input Data: ";
        this.inputs[3] = "Input Name to Find: ";

    }
    
    void sendInput() {
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            printMenu(stdIn);
        } catch (IOException | ClassNotFoundException e){
            Logger.getGlobal().log(Level.SEVERE, null, e);
        } finally{
            try {
                this.in.close();
                this.out.close();
            } catch (IOException ex) {
                Logger.getGlobal().log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void printMenu(BufferedReader stdIn) throws IOException, ClassNotFoundException{
        for (int i=0; i<menu.length; i++)
            System.out.println(menu[i]);
        
        String input = stdIn.readLine();
        
        getSelection(input, stdIn);
    }
    
    private void getSelection(String input, BufferedReader stdIn) throws IOException, ClassNotFoundException {
        switch(input){
            case "1":
                cdao.put(enterCustomer(stdIn));
                printMenu(stdIn);
                break;
            case "2": 
                saveCDAO();
                printMenu(stdIn);
            case "0":
                showCustomers(stdIn);
                break;
            case "EXIT":
                sendExit();
                System.exit(1);
            default:
                printMenu(stdIn);
                break;
        }
    }
    
    private Customer enterCustomer(BufferedReader stdIn) throws IOException{
        
        String name = null;
        do {
            System.out.println(this.inputs[1]);
        } while((name = stdIn.readLine()) == null);
        
        String data = null;
        do {
            System.out.println(this.inputs[2]);
        } while((data = stdIn.readLine()) == null);
        
        return new Customer(name, data);
    }
    
    private void showCustomers(BufferedReader stdIn) throws IOException, ClassNotFoundException{
        if (!this.wasSaved) {
            System.out.println("ERROR: No save exists, create a save");
            this.printMenu(stdIn);
        }
        
        Traverser trav = getTraverser();
        
        while(trav.hasNext()){
            System.out.println(trav.next().toString());
        }
        this.printMenu(stdIn);
    }
    
    private Traverser getTraverser() throws IOException, ClassNotFoundException{
        out.writeObject(new Message(TRAV));
        Message message = (Message) in.readObject();
        
        if(message.getMessage() ==TRAV) {
            CustomerDAO cdao2 = (CustomerDAO) message.getData();
            return cdao2.traverser();
        }
        else return null;
    }
    
    private void saveCDAO() throws IOException, ClassNotFoundException{
        out.writeObject(new Message(SAVE, this.cdao));
        
        if(in.readObject().equals(SAVE))
            System.out.println("Saved.");
        
        this.wasSaved = true;
    }
    
    private void sendExit() throws IOException{
        out.writeObject(new Message(EXIT));
    }
}

