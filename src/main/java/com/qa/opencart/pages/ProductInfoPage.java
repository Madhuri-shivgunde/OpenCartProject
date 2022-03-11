package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header= By.cssSelector("div#content div.col-sm-4 h1");
	private By images=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li h2");
	private By productQuantity=By.cssSelector("div#product input#input-quantity");
	private By btnAddToCart=By.cssSelector("button#button-cart");
	private Map<String,String> productMap;
	
	
	public ProductInfoPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductInfoPageTitle(){
		String title=eleUtil.doGetTitle();
		return title;
	}
	
	public String getProductInfoPageUrl(){
		String url=eleUtil.waitForURLToBe(Constant.PRODUCT_INFO_PAGE_URL_FRACTION, Constant.TIME_OUT);
		return url;
	}
	
	public String getProductInfoHeader(){
		return eleUtil.doGetText(header);
	}
	
	public int getProductInfoImageCount(){
		return eleUtil.waitForAllElementToBeVisible(images, Constant.TIME_OUT).size();
	}
	
	/*
	 
	 //HashMap: check o/p----> no order maintained
	  	Reward Points::700
		Brand::Apple
		Product Code::Product 17
		Name of Product:MacBook Air
		price ::$1,000.00
		Ex Taxprice ::$1,000.00
		Availability::Out Of Stock
	 */
	
	/*
	 // LinkedHashMap o/p: maintain order of insertion
	 	Brand::Apple
		Product Code::Product 17
		Reward Points::700
		Availability::Out Of Stock
		price ::$1,000.00
		Ex Taxprice ::$1,000.00
	 
	 */
	
	/*
	 * 
	 Treemap o/p: Alphabetically ordered o/pp
	 Availability::Out Of Stock
	Brand::Apple
	Ex Taxprice ::$1,000.00
	Name of Product:MacBook Air
	Product Code::Product 17
	Reward Points::700
	price ::$1,000.00
	 */
	
	
	public Map<String,String> getProductData(){
		//productMap=new HashMap<String,String>();
		productMap=new LinkedHashMap<String,String>();
		//productMap=new TreeMap<String,String>();
		productMap.put("Name of Product", getProductInfoHeader());
		getProductMetaData();
		getPriceData();
		return productMap;
	}
	
	private void getProductMetaData()
	{
		List<WebElement>proData_list=eleUtil.getElements(productMetaData);
		for(WebElement e: proData_list) {
			String[] metaData=e.getText().split(":");
			String key=metaData[0].trim();
			String value=metaData[1].trim();
			productMap.put(key+":", value);
		}
	}
	
	private void getPriceData()
	{
		List<WebElement>priceData=eleUtil.getElements(productPriceData);
		productMap.put("price :", priceData.get(0).getText().trim());
		productMap.put("Ex Taxprice :", priceData.get(0).getText().trim());
		
	}
	
	
}
