package com.jet.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static final Properties prop = loadProperties("config.properties");
	public static void main(String[] args) {
		String str = "https://www.googleapis.com/plus/v1/people/105038927904030539036/activities/public?key=AIzaSyDm5uCEhrL9NBfQ6u2FzrbiidaYJrTICWw";
		String str2 = Config.getProp("url");
		System.out.println(str);
		System.out.println(str2);
		System.out.println(str.equals(str2));

	}

	public static Properties loadProperties(String propertiesFile){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties");

			// load a properties file
			prop.load(inputStream);

			// get the property value and print it out
			return prop;


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
	public static String getProp(String key){
		return prop.getProperty(key);
	}
}
