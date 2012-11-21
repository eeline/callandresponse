package serialization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileHandler {
	private static Properties properties = null;
	
	private PropertyFileHandler (){
		super();
	}
	
	public static String getProperty(String key){
		if (properties == null)
			PropertyFileHandler.loadProperties();
		
		return properties.getProperty(key);
	}
	
	private static String getPropertyFileName(){
		return "properties.txt";
	}
	
	private static void loadProperties(){
			if(properties == null) {
				properties = new Properties();
				try {
					properties.load(new FileInputStream(getPropertyFileName()));
				} catch (FileNotFoundException e) {
					File file = new File("properties.txt");
					try {
						file.createNewFile();
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("filename = file.ser");
						bw.close();
						loadProperties();	
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
