package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionManager {
	
	ChromeOptions co;
	FirefoxOptions fo;
	SafariOptions so;
	Properties prop;
	
	OptionManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions(){
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Browser is running in headless mode");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("Browser is running in incognito mode");
			co.addArguments("--incognito");
			}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions(Properties prop){
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Browser is running in headless mode");
			fo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("Browser is running in incognito mode");
			fo.addArguments("--incognito");
			}
		return fo;
	}
	
	public SafariOptions getSafariOptions(Properties prop){
		so=new SafariOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Browser is running in headless mode");
			so.setCapability("--headless", true);
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("Browser is running in incognito mode");
			so.setCapability("--incognito",true);
			}
		return so;
	}

}
