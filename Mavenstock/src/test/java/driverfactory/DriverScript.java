package driverfactory;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.customers.Functionlibrary;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ultiles.Excelfileultils;

public class DriverScript 
{
protected static WebDriver driver;
ExtentReports reports;
ExtentTest test;


	public void starttest() throws Throwable
	{
		reports=new ExtentReports("D:\\sravanthi\\workspace\\Mavenstock\\reports\\extreports.html");
		Excelfileultils excel=new Excelfileultils();
		
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) 
		{
		String modulestatus="";
		
			if (excel.getaData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCMOdule=excel.getaData("MasterTestCases", i, 1);
				System.out.println("SRAVS"+TCMOdule);
				int rowcount=excel.rowCount(TCMOdule);
				
				test=reports.startTest(TCMOdule);
				for (int j = 1; j <=rowcount; j++)
				{
					String Descripition=excel.getaData(TCMOdule, j, 0);
					String Object_Type=excel.getaData(TCMOdule, j, 1);
					System.out.println(Object_Type);
					String Locator_Type=excel.getaData(TCMOdule, j, 2);
					String Locator_value=excel.getaData(TCMOdule, j, 3);
					String Test_Data=excel.getaData(TCMOdule, j,4);
				try
				{
						
					if (Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=Functionlibrary.startBrowser();
						test.log(LogStatus.INFO,  Descripition);
					}
					if (Object_Type.equalsIgnoreCase("openApplication")) 
					{
						Functionlibrary.openapplication();
						test.log(LogStatus.INFO,  Descripition);
					}
					if (Object_Type.equalsIgnoreCase("WaitforElement"))
					{
						Functionlibrary.waitForElement(driver, Locator_Type, Locator_value, Test_Data);
						test.log(LogStatus.INFO,  Descripition);
					}
					if (Object_Type.equalsIgnoreCase("typeAction")) 
					{
						Functionlibrary.typeaction(driver,Locator_Type,Locator_value,Test_Data);
						test.log(LogStatus.INFO,  Descripition);
					}
					if (Object_Type.equalsIgnoreCase("clickAction"))
					{
						Functionlibrary.clickaction(driver, Locator_Type, Locator_value);
						test.log(LogStatus.INFO,  Descripition);
					}
					if (Object_Type.equalsIgnoreCase("closeBrowser")) 
					{
							Functionlibrary.closebrowser(driver);
							test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("captureData"))
					{
						Functionlibrary.captureData(driver, Locator_Type, Locator_value);
						test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("mouseAction"))
					{
						Functionlibrary.mouseAction(driver);
						test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("tableValidation"))
					{
						Functionlibrary.tableValidation(driver, Test_Data);
						test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("alertHandle"))
					{
						Functionlibrary.alertHandle(driver);
						test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("stockcategories"))
					{
						Functionlibrary.stockcategories(driver);
						test.log(LogStatus.INFO,  Descripition);
					}
					if(Object_Type.equalsIgnoreCase("stockValidation"))
					{
						Functionlibrary.stockValidation(driver,Test_Data);
						test.log(LogStatus.INFO,  Descripition);
					}
					excel.setdata(TCMOdule, j, 5, "PASS");
					modulestatus="true";
					test.log(LogStatus.PASS,  Descripition);
					}catch(Exception e)
					{
						
					excel.setdata(TCMOdule, j, 5, "FAIL");
						modulestatus="False";
						test.log(LogStatus.FAIL, Descripition);
						break;
					}
				}
				if (modulestatus.equalsIgnoreCase("true"))
				{
					excel.setdata("MasterTestCases", i, 3, "PASS");
				}else if(modulestatus.equalsIgnoreCase("false"))
				{
				excel.setdata("MasterTestCases", i, 3, "FAIL");
				}
			}
			else
			{
				excel.setdata("MasterTestCases",i, 3, "Not Execution");
			}
			reports.endTest(test);
			reports.flush();
			}
		}
		
		
	}


