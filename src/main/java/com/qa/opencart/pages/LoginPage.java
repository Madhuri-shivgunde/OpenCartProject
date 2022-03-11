package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
		private WebDriver driver;
		private ElementUtil eleUtil;
	
	// 1. initialize private By locators of Login Page as class variables
		
		private By email=By.id("input-email");
		private By pass=By.id("input-password");
		private By lgnbtn=By.xpath("//input[@value='Login']");
		private By forgotpassLink=By.xpath("//div[@class='list-group']/a[text()='Forgotten Password']");
		private By registerLink=By.linkText("Register");
		private By loginErrorMgs=By.cssSelector("div.alert.alert-danger.alert-dismissible");
		
	// 2. create constructor which initializes driver
		
		public LoginPage(WebDriver driver)
		{
			this.driver=driver;
			eleUtil=new ElementUtil(driver);
		}
		
	// 3. define Login page action methods
		
		
		//adding Allure annotations to each method
		
		@Step("getting login page title....")
		public String getLoginPageTitle()
		{
			String title=eleUtil.doGetPageTitle(Constant.LOGIN_PAGE_TITLE, Constant.TIME_OUT);
			return title;
		}
		
		@Step("getting login page URL....")
		public String getLoginPageUrl()
		{
			String url=eleUtil.waitForURLToBe(Constant.LOGIN_PAGE_URL_FRACTION, Constant.TIME_OUT);
			return url;
		}
		
		@Step("check for forgot password link....")
		public boolean isForgotPasswordLinkExist()
		{
			return eleUtil.doisDisplay(forgotpassLink);
			 //driver.findElement(forgotpassLink).isDisplayed();
			
		}
		
		@Step("check for registration link...")
		public boolean isRegisterLinkExist()
		{
			return eleUtil.doisDisplay(registerLink);
			 //driver.findElement(forgotpassLink).isDisplayed();
			
		}
		
		@Step("clicking registration page link....")
		public RegisterPage goToRegistrationPage()
		{
			eleUtil.doClick(registerLink);
			return new RegisterPage(driver);
		}
		
		
		@Step("getting user login with valid credentials- username: {0} and Password: {1}")
		public AccountPage doLogin(String unm, String pwd)
		{
			eleUtil.doSendKeys(email, unm);
			eleUtil.doSendKeys(pass, pwd);
			eleUtil.doClick(lgnbtn);
			return new AccountPage(driver);
		}
		
		@Step("getting user login with invalid credentials - username: {0} and Password: {1}")
		public boolean doLoginWithWrongCredentials(String unm, String pwd)
		{
			System.out.println("Trying to login with wrong credentials....");
			eleUtil.doSendKeys(email, unm);
			eleUtil.doSendKeys(pass, pwd);
			eleUtil.doClick(lgnbtn);
			
			String err_msg=eleUtil.doGetText(loginErrorMgs).trim();
			System.out.println(err_msg);
			if(err_msg.contains(Constant.LOGIN_PAGE_ERROR_MESSAGE)) {
				System.out.println("Login Unsuccessful..");
				return false;
			}
			return true;
		
		}
	

}
