package com.stocksravanthi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Jenkins {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\sravanthi\\workspace\\Mavenstock\\commonjarfiles\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.facebook.com");
	}

}
