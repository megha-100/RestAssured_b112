package com.qa.api.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager
{
	 private  static Properties p=new Properties();
	 
		static
		{
//			 try {
//			        FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties"); // Adjust path as needed
//			        p.load(fis);
//			    } catch (IOException e) {
//			        e.printStackTrace();
//			    }
			InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");
			if(input!=null)
			{
			try {
				p.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
			public static String get(String key)
			{
				return p.getProperty(key).trim();
			}
			
			public static void set(String key,String value)
			{
				 p.setProperty(key, value);
			}
			
}

