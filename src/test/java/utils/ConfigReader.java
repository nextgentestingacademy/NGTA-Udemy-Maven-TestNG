package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;
	
	public static void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String fetchValue(String key) {
		if(prop == null) {
			loadProperties();
		}
		return prop.getProperty(key);
	}
}