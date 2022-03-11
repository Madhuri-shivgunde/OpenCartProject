package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constant;

public class AccountPageTest extends BaseTest {
	
	//precondition to land on AccountPage i.e. Login first before applying Test on AccountPage
	@BeforeClass
	public void accPageSetUp(){
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitileTest(){
		String actTitle=accPage.getAccountPageTitle();
		System.out.println("Account Page title getting during test: "+actTitle);
		Assert.assertEquals(actTitle, Constant.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLFractionTest(){
		String actURLFraction=accPage.getAccountPageURLFraction();
		System.out.println("Account Page URL fraction getting during test: "+actURLFraction);
		Assert.assertTrue(actURLFraction.contains(Constant.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void accPageHeaderTest(){
		String actHeader=accPage.getAccountPageHeader();
		System.out.println("Account Page Header getting during test: "+actHeader);
		Assert.assertEquals(actHeader, Constant.ACCOUNT_PAGE_HEADER);
		
	}
	
	@Test
	public void sectionTextTest(){
		List<String> SectionsTextList=accPage.getSectionHeadersText();
		System.out.println("List of sectionText getting: "+SectionsTextList);
		Assert.assertEquals(SectionsTextList, Constant.ACCOUNT_PAGE_SECTION_TEXT_LIST);
	}
	
	
	
	@Test
	public void logoutLinkExistTest(){
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest(){
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	

}
