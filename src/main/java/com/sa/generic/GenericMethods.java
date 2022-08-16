package com.sa.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class GenericMethods {
	
	public WebDriver driver;
	//Generic method to read data from Properties file
	public static String getPpt(String path,String key)
	{
		String v="";
		try {
		Properties p=new Properties();
		
			p.load(new FileInputStream(path));
			v=p.getProperty(key);
			
		} catch (IOException e) {
			
			
		}
		return v;		
	}
	
	public static String genRandomText() {
		Random random = new Random();   
		// Generates random integers from 0 to 1000  
		int ranNum = random.nextInt(1000); 
		return String.valueOf(ranNum);
	}
	
	@BeforeMethod
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().getCookies();
		Thread.sleep(2000);
		String url=GenericMethods.getPpt("Configuration\\Config.properties", "URL");
		System.out.println("The Url is:"+url);		
		Thread.sleep(2000);
		driver.manage().window().maximize();
		System.out.println("Maximised");
		driver.get(url);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
