package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
	
		//Create java representation object of physical File
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\Commondata.properties");

		//Create object of Properties class
		Properties prop=new Properties();
		
		//Load the keys from java representation object
		prop.load(fis);
		
		String Browser=prop.getProperty("Browser");
		
		System.out.println(Browser);
		
	}

}
