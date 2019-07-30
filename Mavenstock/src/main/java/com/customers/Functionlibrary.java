package com.customers;




import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.ultiles.Propertiesfile;

public class Functionlibrary
{
		static WebDriver driver;
	 
		 public static WebDriver startBrowser() throws Throwable, IOException
		{
			if (Propertiesfile.getvalueforkey("browser").equalsIgnoreCase("firefox"))
			{
				 driver=new FirefoxDriver();
			}else 
				if (Propertiesfile.getvalueforkey("browser").equalsIgnoreCase("chrome"))
			{
			System.setProperty("webdriver.chrome.driver","D:\\sravanthi\\workspace\\Mavenstock\\commonjarfiles\\chromedriver.exe");
			driver=new ChromeDriver();
			}else 
				if (Propertiesfile.getvalueforkey("browser").equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.chrome.driver","D:\\sravanthi\\workspace\\Mavenstock\\chromedriver.exe");
				driver=new InternetExplorerDriver();
			}
			return driver;
			}
	//openurl
		public static void openapplication() throws Throwable, IOException
	{
		driver.get(Propertiesfile.getvalueforkey("url"));
		driver.manage().window().maximize();
	}
		public static void clickaction(WebDriver driver,String locatortype,String locatorvalue )
		{
			if (locatortype.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(locatorvalue)).click();
			}else
				if (locatortype.equalsIgnoreCase("name"))
				{
					driver.findElement(By.name(locatorvalue)).click();
			}else if (locatortype.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorvalue)).click();
			}
			}
	
		//TYPEACTION
		public static void typeaction(WebDriver driver,String locatortype, String locatorvalue,String data)
		{
			
			if (locatortype.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(locatorvalue)).clear();
				driver.findElement(By.id(locatorvalue)).sendKeys(data);
				}else if (locatortype.equalsIgnoreCase("name"))
				{
					driver.findElement(By.name(locatorvalue)).clear();
					driver.findElement(By.name(locatorvalue)).sendKeys(data);
				}else if (locatortype.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorvalue)).clear();
					driver.findElement(By.xpath(locatorvalue)).sendKeys(data);
				}
				//CLOSE BROWSER
				}
			public static void closebrowser(WebDriver driver)
			{
				driver.close();
			}
				//wait
				public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waittime)
				{
					WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(waittime));
							if (locatortype.equalsIgnoreCase("id"))
							{
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
							}else
							
								if (locatortype.equalsIgnoreCase("name"))
								{
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
								}else
									
								if (locatortype.equalsIgnoreCase("xpath"))
								{
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
								}
							if (locatortype.equalsIgnoreCase("css selector"))
							{
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorvalue)));
							}
							}//mouse click
							public static void stockcategories(WebDriver driver)
							{
							Actions act=new Actions(driver);
							act.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']"))).build().perform();
							act.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']"))).click().build().perform();
							}
					public static void generateDate()
					{
											}
						
					public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws Throwable
					{
						String data="";
						if(locatortype.equalsIgnoreCase("id"))
						{
							data=driver.findElement(By.id(locatorvalue)).getAttribute("value");
						}else
							if (locatortype.equalsIgnoreCase("name"))
							{
								data=driver.findElement(By.name(locatorvalue)).getAttribute("value");
							}else
								if (locatortype.equalsIgnoreCase("xpath"))
								{
									data=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
								}
							FileWriter fw=new FileWriter("D:\\sravanthi\\workspace\\Mavenstock\\capturedata\\data.txt");
							BufferedWriter bw=new BufferedWriter(fw);
							bw.write(data);
							bw.flush();
							bw.close();
							}
					public static void alertHandle(WebDriver driver)
					{
						driver.switchTo().alert().dismiss();
					}
					public static void mouseAction(WebDriver driver)
				{
					Actions act=new Actions(driver);
					act.sendKeys(Keys.PAGE_DOWN).perform();
					
				}
					//tablevalidation
					public static void stockValidation(WebDriver driver,String exp_data) throws Throwable, IOException
					{
						if (driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).isDisplayed())
							
						{
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
						}else
						{
							//Thread.sleep(4000);
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.panel"))).click();
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
							driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
						}
						
						WebElement webtable=driver.findElement(By.xpath(Propertiesfile.getvalueforkey("webtable.path1")));
						List<WebElement>rows=webtable.findElements(By.tagName("tr"));
						for (int i =1; i < rows.size(); i++)
						{
							
							String act_data=driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
							Assert.assertEquals(exp_data, act_data);
						}
					}
						public static void tableValidation(WebDriver driver,String colnum) throws Throwable, IOException
						{
							FileReader fr=new FileReader("D:\\sravanthi\\workspace\\stock accounting\\capturedata\\data.txt");
							BufferedReader br=new BufferedReader(fr);
							String exp_data=br.readLine();
							int colnum1=Integer.parseInt(colnum);
							if (driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).isDisplayed())
									
							{
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
							}else
							{
								//Thread.sleep(4000);
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.panel"))).click();
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
								driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
							}
							
							WebElement webtable=driver.findElement(By.xpath(Propertiesfile.getvalueforkey("webtable.path")));
							List<WebElement>rows=webtable.findElements(By.tagName("tr"));
							for (int i =1; i < rows.size(); i++)
							{
								
								String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colnum1+"]/div/span/span")).getText();
								Assert.assertEquals(exp_data, act_data);
								/*public static void CustomerValidation(WebDriver driver,String exp_data) 
								{
									if (driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).isDisplayed())
										
									{
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
									}else
									{
										//Thread.sleep(4000);
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.panel"))).click();
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).clear();
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.box"))).sendKeys(exp_data);
										driver.findElement(By.xpath(Propertiesfile.getvalueforkey("search.button"))).click();
									}
									
									WebElement webtable=driver.findElement(By.xpath(Propertiesfile.getvalueforkey("webtable.path2")));
									List<WebElement>rows=webtable.findElements(By.tagName("tr"));
									for (int i =1; i < rows.size(); i++)
									{
										
										String act_data=driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
										Assert.assertEquals(exp_data, act_data);
									}
								}*/
								break;
							
							}
							
							
						}
				
					
							
						}



