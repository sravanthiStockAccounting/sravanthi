package driverfactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.customers.Functionlibrary;
import com.ultiles.Propertiesfile;

public class Basic {
	public static void main(String[] args) throws Throwable, Throwable {
		Propertiesfile fis=new Propertiesfile();
		fis.getvalueforkey("url");
		System.out.println(fis.getvalueforkey("url"));
		Functionlibrary.startBrowser();
		Functionlibrary.openapplication();
		
	}
	
	
	

}
