package driverfactory;

import org.testng.annotations.Test;

public class Apptest {

	
	
		@Test
		public void kickStart() throws Throwable
		{
			
			try
			
			{
				DriverScript ds=new DriverScript();
				ds.starttest();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	


