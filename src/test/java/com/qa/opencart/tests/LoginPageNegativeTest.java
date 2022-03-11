package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.AnnotationTransformerListener;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({TestAllureListener.class, AnnotationTransformerListener.class})
@Epic("Epic-100: Design Login Page for Opencart Applicatio....")
@Story("User Story-101- User Login Page Functionality")
public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginNegativeTestData()
	{
		Object[][] data= {{"madhuri@gmail.com",""},
						  {"","Madhuri@123"},
						  {"",""},
						  {"123","!@#!@#"}
						 };
		return data;
	}
	
	
	@Description("TC-05 User Login Test with Invalid Credentials ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="loginNegativeTestData")
	public void loginNegativeTest(String username,String password) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));;
	}
}
