package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.AnnotationTransformerListener;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//Allure provides class level annotation
@Listeners({TestAllureListener.class, AnnotationTransformerListener.class})
@Epic("Epic-100: Design Login Page for Opencart Applicatio....")
@Story("User Story-101- User Login Page Functionality")
public class LoginPageTest extends BaseTest{
	//this class contains only testNG test methods, no selenium code
	// all method should return void
	//all test method should contain at leatst one Assert
	//all method name ends with "Test"
	//if no priority is defined to test methods, test methods will get execute in alphabetic order
	
	@Description("TC-01- Login page Title Test") //this is allure annotation
	@Severity(SeverityLevel.NORMAL)				//this is allure annotation
	@Test
	public void loginPageTitleTest(){
		String title=loginPage.getLoginPageTitle();
		System.out.println("Title of LOginPage is :"+title);
		Assert.assertEquals(title,Constant.LOGIN_PAGE_TITLE);
	}
	
	@Description("TC-02- Login page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test void loginPageURLTest() {
		String url=loginPage.getLoginPageUrl();
		System.out.println("URL of LOginPage is :"+url);
		Assert.assertTrue(url.contains(Constant.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("TC-04- Login page-Forgot Password Link Test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void forgotPwdLinkTest(){
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("TC 03- User Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=1)
	public void loginTest(){
		String userName=prop.getProperty("username").trim();
		String passWord=prop.getProperty("password").trim();
		accPage=loginPage.doLogin(userName,passWord);
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
