package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountPageSearchproductTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp(){
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] searchProductData()
	{
		Object[][] data= {
				{"macbook"},
				{"imac"},
				{"iphone"},
				{"Apple"}
		};
		return data;
	}
	
	@Test(dataProvider="searchProductData")
	public void searchProductTest(String productName)
	{
		searchResultPage=accPage.searchItem(productName);
		int searchPrCount=searchResultPage.getProductListCount();
		Assert.assertTrue(searchPrCount>0);
	}
	
	@DataProvider
	public Object[][] selectProductData()
	{
		Object[][] data= {
				{"macbook","MacBook Air"},
				{"imac","iMac"},
				{"iphone","iPhone"},
				{"Apple","Apple Cinema 30\""}
		};
		return data;
	}
	
	
	@Test(dataProvider="selectProductData")
	public void selectProductTest(String productName,String selectProductName)
	{
		searchResultPage=accPage.searchItem(productName);
		productInfoPage=searchResultPage.selectProduct(selectProductName);
		Assert.assertEquals(productInfoPage.getProductInfoHeader(), selectProductName);
		
		
	}
	
	
}
