package serialization;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import collections.Traverser;
import collections.Vector;

import datatypes.Customer;

public class CustomerDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7926902507973796735L;
	Vector vector;
	
	public CustomerDAO(){
		vector = new Vector();
	}
	
	private void writeObject(ObjectOutputStream outputstream) throws IOException{
		outputstream.defaultWriteObject();
	}
	
	public void put(Customer customer){
		vector.put(customer);
	}
	
	public Traverser traverser(){
		return vector.toTraverser();
	}
	
	public boolean fileInput(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (verifyFile(file)) {
			ObjectInputStream in = inStreamGetter(file);
			Object object = in.readObject();
			this.vector = (Vector) object;
			in.close();
			return true;
		}
		return false;
	}
	
	public boolean fileOutput(File file) throws IOException, FileNotFoundException {
		if (verifyFile(file)) {
			ObjectOutputStream out = outStreamGetter(file);
			out.writeObject(this.vector);
			out.close();
			return true;
		}
		return false;
	}
	
	public boolean appendToFile(File file) throws IOException, ClassNotFoundException {
		if(verifyFile(file)){
			Vector vec2 = this.vector;
			fileInput(file);
			
			Traverser trav = vec2.toTraverser();
			while (trav.hasNext()){
				this.put((Customer)trav.next());
			}
			
			fileOutput(file);
			return true;
		}
		return false;
	}
	
	//TODO: fail gracefully by creating the file, or tell them things are fubar?
	private boolean verifyFile(final File file) throws IOException{
		return file.exists();
	}
	
	private ObjectInputStream inStreamGetter(File file) throws FileNotFoundException, IOException{
		ObjectInputStream in;
		in = new ObjectInputStream(new FileInputStream(file));
		return in;
	}
	
	private ObjectOutputStream outStreamGetter(File file) throws IOException{
		OutputStream os = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(os);
		return out;
	}
}
