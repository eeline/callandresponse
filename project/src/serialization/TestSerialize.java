package serialization;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import collections.Traverser;

import datatypes.Customer;

public class TestSerialize {
	
	public static void main(String[] arg){
            File file = new File(PropertyFileHandler.getProperty("filename"));
            CustomerDAO cdao = new CustomerDAO();
            
            for (int i=0; i<100; i++){
            	Customer customer = new Customer(Integer.toString(i), Integer.toString(i));
            	cdao.put(customer);
            }
            
            try {
				cdao.fileOutput(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            CustomerDAO cdao2 = new CustomerDAO();
            for (int i=100; i>0; i--) {
            	Customer customer = new Customer(Integer.toString(i),Integer.toString(i));
            	cdao2.put(customer);
            }
            
            try {
				cdao2.appendToFile(file);
				cdao2.fileInput(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Traverser traverser = cdao2.traverser();
           
            while (traverser.hasNext()){
        	   Customer cust = (Customer) traverser.next();
        	   System.out.println(cust.toString());
           }
	}
}
        