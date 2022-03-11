package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void setUpTest()
	{
		regPage=loginPage.goToRegistrationPage();
	}
	
	@Test
	public void regPageTitleTest(){
		String actTitle=regPage.getRegisterPageTitle();
		System.out.println("Registration Page Title is :"+actTitle);
		Assert.assertEquals(actTitle, Constant.REGISTER_PAGE_TITLE);
		
	}
	
	@Test
	public void regPageURLFractionTest(){
		String actURLFraction=regPage.getRegisterPageUrl();
		System.out.println("Registration Page URl Fraction is :"+actURLFraction);
		Assert.assertTrue(actURLFraction.contains(Constant.REGISTER_PAGE_URL_FRACTION));
	}
	
	@Test
	public void regPageHeaderTest() {
		String header=regPage.getRegisterPageHeader();
		System.out.println("Registration page Header is :"+header);
		Assert.assertEquals(header, Constant.REGISTER_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getRegisterData(){
		Object[][] data=ExcelUtil.getTestDataFromSheet(Constant.REGISTER_SHEET_NAME);
		return data;
		
		
	}
	public String getEmail()
	{
		Random rand=new Random();
		String email="testMadhuri"+rand.nextInt(100)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider="getRegisterData")
	public void userRegistrationTest(String fname,String lname,String tele,String pass, String cpass, String subScribe) {
	
		Assert.assertTrue(regPage.accountRegistration( fname,lname,getEmail(),tele,pass, cpass,subScribe));
		
	}
	
	@DataProvider
	public Object[][] getRegisterNegativeData(){
		Object[][] data=ExcelUtil.getTestDataFromSheet(Constant.REGISTER_SHEET_NEGATIVE_NAME);
		return data;
	}
	
	@Test(dataProvider="getRegisterNegativeData")
	public void userRegistrationNegativeTest(String fname,String lname,String tele,String pass, String cpass, String subScribe) {
	
		Assert.assertTrue(regPage.accountRegistrationWithWrongData( fname,lname,getEmail(),tele,pass, cpass,subScribe));
		
	}
	
}
