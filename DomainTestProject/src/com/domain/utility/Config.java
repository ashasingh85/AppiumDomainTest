package com.domain.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static Properties getProperty() {
		 Properties prop = new Properties();
		 FileInputStream fs;
		try {
			fs = new FileInputStream("D:\\JavaTrainingSession\\DomainTestProject\\src\\com\\Domain\\Test\\data.properties");
			prop.load(fs);
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
		}
		 
		 return prop;
		 
	}
	
	public static String getStringValue(String key,String defaultValue) {
		return getProperty().getProperty(key, defaultValue);
	}
	
	public static String getStringValue(String key) {
		return getProperty().getProperty(key);
	}
	

}
