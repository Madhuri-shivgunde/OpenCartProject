package com.qa.opencart.pages;


import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header=By.cssSelector("div#content h1");
	private By productResults=By.cssSelector("div.caption a");
	private By productImages=By.cssSelector("div.image img");
	
	
	public SearchResultPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	public String getSearchResultPageTitle(String productName)
	{
		String title=eleUtil.doGetPageTitle(Constant.SEARCH_RESULT_PAGE_TITLE_FRACTION, Constant.TIME_OUT);
		return title+" productName";
	}
	
	public String getSearchResultPageUrl()
	{
		String url=eleUtil.waitForURLToBe(Constant.SEARCH_RESULT_PAGE_URL_FRACTION, Constant.TIME_OUT);
		return url;
	}
	
	public String getSearchResultHeader()
	{
		return eleUtil.waitForElementToBeVisible(header,Constant.TIME_OUT).getText();
	}
	
	public int getProductListCount()
	{
		int count=eleUtil.waitForAllElementToBeVisible(productResults, Constant.TIME_OUT).size();
		System.out.println(count + "products founds related to your serach");
		return count;
	}
	
	public int getProductImageCounts()
	{
		int imageCount=eleUtil.waitForAllElementToBeVisible(productResults, Constant.TIME_OUT).size();
		return imageCount;
	}
	
	
	public ProductInfoPage selectProduct(String productName)
	{
		System.out.println("Selected Product name is: "+productName);
		List<WebElement> searchProductList=eleUtil.waitForAllElementToBeVisible(productResults, Constant.TIME_OUT);
		for(WebElement e:searchProductList)
		{
			if(e.getText().equalsIgnoreCase(productName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
	

}
