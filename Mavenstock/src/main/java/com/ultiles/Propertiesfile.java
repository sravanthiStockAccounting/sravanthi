package com.ultiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propertiesfile {

	public static String getvalueforkey (String key) throws FileNotFoundException, IOException
	{
		Properties configpro=new Properties();
		configpro.load(new FileInputStream(new File("D:\\sravanthi\\workspace\\Mavenstock\\properties file\\environment.properties")));
		return configpro.getProperty(key);

	}

}
