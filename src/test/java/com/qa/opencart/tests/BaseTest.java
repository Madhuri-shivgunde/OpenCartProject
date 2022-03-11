package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	AccountPage accPage;
	RegisterPage regPage;
	SearchResultPage searchResultPage;
	ProductInfoPage productInfoPage;
	
	
	@BeforeTest
	public void setUp() 
	{
		df=new DriverFactory();  //object of DriverFactory
		prop=df.init_prop();    // initialize properties to read 
		driver=df.init_driver(prop); //call method init_driver of Driver factory to initialize driver and launch browser, pass properties reference
		loginPage=new LoginPage(driver);  //pass driver to Loginpage to perform test on Loginpage
	}
	
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}

}
