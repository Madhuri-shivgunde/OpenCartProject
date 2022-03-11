package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	// 1. initialize private By locators of My Account Page as class variables

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By header=By.cssSelector("div#logo h1");
	By search=By.cssSelector("div#search input");
	By btnSearch=By.cssSelector("span.input-group-btn");
	By sectionHeaders=By.cssSelector("div#content h2");
	By logoutLink=By.linkText("Logout");
	
	
	// 2. create constructor which initializes driver
	
	public AccountPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	// 3. define Account page action methods
	
	public String getAccountPageTitle() {
		String title=eleUtil.doGetPageTitle(Constant.ACCOUNT_PAGE_TITLE, Constant.TIME_OUT);
		return title;
	}
	
	
	public String getAccountPageURLFraction(){
		return eleUtil.waitForURLToBe(Constant.ACCOUNT_PAGE_URL_FRACTION, Constant.TIME_OUT);
	}

	public String getAccountPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isSearchExist(){
		return eleUtil.doisDisplay(search);
	}
	
	
	public SearchResultPage searchItem(String productName){
		System.out.println("You are searching product: "+productName);
		eleUtil.doSendKeys(search, productName);
		eleUtil.doClick(btnSearch);
		return new SearchResultPage(driver);
	}
	
	
	public boolean isLogoutLinkExist(){
		return eleUtil.doisDisplay(logoutLink);
	}
	
	
	public boolean logout(){
		if(isLogoutLinkExist()){
			eleUtil.doClick(logoutLink);
			return true;
		}
		return false;
	}
	
	
	public List<String> getSectionHeadersText(){
		List<WebElement> sectionsHeadersList=eleUtil.waitForAllElementToBeVisible(sectionHeaders, Constant.TIME_OUT);;
		List<String> sectionsText= new ArrayList<String>();
		for(WebElement e:sectionsHeadersList)
		{
			sectionsText.add(e.getText());
		}
		return sectionsText;
	}
	

}
