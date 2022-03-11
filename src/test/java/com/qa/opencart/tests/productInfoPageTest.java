package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class productInfoPageTest extends BaseTest{

	@BeforeClass
	public void productInfoPageSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSelectedProductDataInfo(){
		Object[][] data= {
				{"macbook","MacBook Air"},
				{"imac","iMac"},
				{"iphone","iPhone"},
				{"Apple","Apple Cinema 30\""}
		};
		return data;
	}
	
	
	@Test(dataProvider="getSelectedProductDataInfo")
	public void productInfoHeaderTest(String productName,String selectedProductName) {
		searchResultPage=accPage.searchItem(productName);
		productInfoPage=searchResultPage.selectProduct(selectedProductName);
		String actProductHeaderName=productInfoPage.getProductInfoHeader();
		System.out.println("Actual Product Name:"+actProductHeaderName);
		Assert.assertEquals(actProductHeaderName, selectedProductName);
	}
	
	
	
	@DataProvider
	public Object[][] getSelectedProductImagesCount(){
		Object[][] data= {
				{"macbook","MacBook Air",4},
				{"imac","iMac",3},
				{"iphone","iPhone",6},
				{"Apple","Apple Cinema 30\"",6}
		};
		return data;
	}
	
		
	@Test(dataProvider="getSelectedProductImagesCount")
	public void productInfoImagesCountTest(String productName,String selectedProductName,int imagesCount){
		searchResultPage=accPage.searchItem(productName);
		productInfoPage=searchResultPage.selectProduct(selectedProductName);
		int totalImages=productInfoPage.getProductInfoImageCount();
		System.out.println("Total images count of selected product :"+totalImages);
		Assert.assertEquals(totalImages, imagesCount);
	}
	
	
	@Test
	public void productMetaDataTest()
	{
		searchResultPage=accPage.searchItem("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Air");
		Map<String,String>pInfo=productInfoPage.getProductData();
		pInfo.forEach((k,v)->System.out.println(k+":"+v));
	}
	
	
	
}
