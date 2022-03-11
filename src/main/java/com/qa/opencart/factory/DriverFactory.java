package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionManager optionManager;
	public static String highlight;
	
	
	/**
	 This class provides thread-local variables.
	  These variables differ fromtheir normal counterparts in that each thread 
	  that accesses one (via its get or set method) has its own, independently
	   initializedcopy of the variable. ThreadLocal instances are typically 
	   privatestatic fields in classes that wish to associate state with a
	    thread (e.g.,a user ID or Transaction ID). 
	 */
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	
	/**
	 * this method initializes driver using browsername and launches browser
	 * @param browserName
	 * @return this returns WebDriver
	 */
	public WebDriver init_driver(Properties prop) {
		optionManager=new OptionManager(prop);
		highlight=prop.getProperty("highlight");
		String browserName=prop.getProperty("browser").trim();
		System.out.println("Your Browser Name is : "+browserName);
		System.out.println("Highlight Element is: "+highlight);
		
		
		//code for cross browser
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionManager.getFirefoxOptions(prop));
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions(prop)));
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			//WebDriverManager.chromedriver().setup();
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass valid Browser Name: "+browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	
	/**
	 * this will return thread local copy of webdriver
	 * @return
	 */
	public WebDriver getDriver(){
		return tlDriver.get();
	}
	
	/**
	 * this will read/ initialize properties value required for environment setup like browser name,url etc fro config file
	 * A property list can contain another property list as its"defaults"; 
	 * this second property list is searched ifthe property key is not found in the original property list. 
	 * @return properties reference holding all values
	 */
	public Properties init_prop() {
		prop =new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
	
	
	//Thread Local: jdk 1.8, here, we are using to create local copy of driver
	//serdriver with TL
	//getDriver() will give driver without Null
	//so diver NULL problem will get solve
	//using so, you can take your driver anywhere in your framework
	//better thread management
	//bu not used for multithreaded envirnment
	//to avoid deadlock condition ,use TL driver copy
	
	
	
	/**
	 * This methods when invoked takes screenshot of screen
	 * @return
	 */
	
	
	public String getScreenshot()
	{
		File scrShot=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(scrShot, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	
	}
	
	
	
	
	

}
