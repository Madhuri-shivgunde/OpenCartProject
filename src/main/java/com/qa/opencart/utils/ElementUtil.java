package com.qa.opencart.utils;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;


public class ElementUtil 
{
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) 
	{
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
//************************************    getElement(By locator) finds a web element******************************
	public  WebElement getElement(By locator)
	{
		WebElement ele=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{jsUtil.flash(ele);}
		
		return ele;
		
	}
	
//************************************    getElements(By locator) finds web elements ******************************
	
	public  List<WebElement> getElements(By locator) 
	{
		
		return driver.findElements(locator);
				
	}
	
	
// **************************************enterURL(String url) to hit url
public void enterURL(String url)
	{
		driver.get(url);
	}

//************************************    doGetURL() ******************************
	public  String doGetURL()
	{
		return driver.getCurrentUrl();
		
	}

//************************************    doGetTitle() ******************************
		public  String doGetTitle()
		{
			return driver.getTitle();
		}

//************************************   doSendKeys(By locator ,String value) calls getelement method to find webelement and inputs data to webelement ***********************

	public  void doSendKeys(By locator ,String value)
	{
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		
	}
	
//************************************    doSendKeys(String LocatorType, String LocatorValue, String value) calls getelement method to finds webelement and input data in it ****************
	public  void doSendKeys(String LocatorType, String LocatorValue, String value) 
	{
		WebElement ele=getElement(getBy(LocatorType,LocatorValue));
		ele.clear();
		ele.sendKeys(value);
	}
	
//**************************************  	doActionSendKeys(By locator, String value)
	public  void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value);
	}
	
	
// ***************************************doClick(By Locator) calls getelement to find element and then clicks on it*************************************
	
	public  void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	
//******************************************    doActionClick(By locator)
	
	public  void doActionClick(By locator)
	{
			Actions act=new Actions(driver);
			act.click(getElement(locator)).perform();
	}
	
//************************************** doGetText(By locator) calls getElement to find element and returns text of element 
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	

//********************************* doGetAttribute(By locator, String attrName)
	public String doGetAttribute(By locator, String attrName)
	{
		return getElement(locator).getAttribute(attrName);
	}
	
	
//********************************* doisDisplay(By locator)
	public boolean doisDisplay(By locator)
	{
		return getElement(locator).isDisplayed();
	}
		
//********************************* doisEnabled(By locator)
	public boolean doisEnabled(By locator)
	{
		return getElement(locator).isEnabled();
	}
	
 //************************************     getBy(String LocatorType, String LocatorValue)  gives By locator by prviding locator type **********************
	
	public By getBy(String LocatorType, String LocatorValue) 
	{
		By locator =null;
		switch (LocatorType) {
		case "id":
			locator=By.id(LocatorValue);
				
			break;
		case "name":
			locator=By.name(LocatorValue);
				
			break;
			
		case "class":
			locator=By.className(LocatorValue);
				
			break;
		case "xpath":
			locator=By.xpath(LocatorValue);
				
			break;
		case "cssSelector":
			locator=By.cssSelector(LocatorValue);
				
			break;
		default:
			break;
		}
		return locator;
	}
	
	
//************************************************  getElementsCount(By locator)  given total no of element

	public int getElementsCount(By locator) 
	{
		return getElements(locator).size();
	}
	
//**********************************************   printElementsAttribute(By locator,String attr)

public  void printElementsAttribute(By locator,String attr) 
	{
		List<WebElement> eleList=getElements(locator);
		for(WebElement e: eleList) 
		{
			String att =e.getAttribute(attr);
			System.out.println(" : "+att);
			
		}
	}

//***********************************************   List<String> getElementsAttributeList(By locator, String attr)
public  List<String> getElementsAttributeList(By locator, String attr)
{
	List<WebElement> eleList=getElements(locator);
	List<String> attri_List = new ArrayList<String>();
	for(WebElement e: eleList) 
	{
		String text=e.getAttribute(attr);
		
		if(!attr.isEmpty()) 
		{
			attri_List.add(text);
		}
	}
	return attri_List;
}



//*************************************************  printElementsText(By locator)
public void printElementsText(By locator) 
{
	List<WebElement> eleList=getElements(locator);
	int count=0;
	for(WebElement e: eleList) {
		String text=e.getText();
		//String href =e.getAttribute("href");
		if(!text.isEmpty()) {
			System.out.print(count+" : "+text);
			//System.out.println(" : "+href);
		}
		count++;
		}

}

//***********************************************   List<String> getElementsTextList(By locator)
public  List<String> getElementsTextList(By locator)
{
	List<WebElement> eleList=getElements(locator);
	List<String> textList = new ArrayList<String>();
	for(WebElement e: eleList) 
	{
		String text=e.getText();
		//String href =e.getAttribute("href");
		if(!text.isEmpty()) 
		{
			textList.add(text);
			//System.out.println(" : "+href);
		}
	}
	return textList;
}

//***********************************************************  clickOnLinks(By locator,String lonkText)
	public  void clickOnLinks(By locator,String linkText) 
	{
		
		List<WebElement> eleList=getElements(locator);
		
		for(WebElement e: eleList) 
		{
			String text=e.getText();
			if(text.equals(linkText))
			{
				e.click();
				break;
			}
			
		}
	
}

//************************************************************ dropdown utils *******************************************

public  void doSelectDropdownByIndex(By locator, int index)
{
	Select ele=new Select(getElement(locator));
	ele.selectByIndex(index);
}

public  void doSelectDropdownByVisibleText(By locator, String visibleText)
{
	Select ele=new Select(getElement(locator));
	ele.selectByVisibleText(visibleText);
}
public  void doSelectDropdownByValue(By locator, String value)
{
	Select ele=new Select(getElement(locator));
	ele.selectByVisibleText(value);
}

public int getDropdownOptiosCount(By locator)
{
	 Select options=new Select(getElement(locator));
	 return options.getOptions().size();
	 
	//return getDropdownAllOptionsList(locator).size();
}

public  List<String> getDropdownAllOptionsList(By locator)
{
	Select alloptions=new Select(getElement(locator));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	List<WebElement>allElementList=alloptions.getOptions();
	List<String> allOptionsTextList=new ArrayList<String>();
	int c=0;
	for(WebElement e : allElementList)
	{
		String option=e.getText();
		//System.out.println(option);
		allOptionsTextList.add(c+":"+option);
		c++;
	
		
	}
	return allOptionsTextList;
}

public  void selectValuefromDropdownOptions(By locator, String value)
{
	Select alloptions=new Select(getElement(locator));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	List<WebElement>allElementList=alloptions.getOptions();
	
	for(WebElement e : allElementList)
	{
		String option=e.getText();
		if(option.equals(value))
		{
			e.click();
			break;
		}
	
	}

}
//**********************************************************************Wait Utils


//********************************************************************* Wait methods for element and Non Element

/**
 *
 An expectation for checking an element is visible and enabled such that you can click it.

 *
 * @param locator
 * @param timeout
 * @return
 */

public WebElement clickWhenElementReady(By locator, int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.elementToBeClickable(locator));
}

/**
 An expectation for checking that an element is present on the DOM of a page. This does notnecessarily mean that the element is visible
 
 * @param locator
 * @param timeout
 * @return
 */
public  WebElement waitForElementToBePresent(By locator, long timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}

/**
 
 An expectation for checking that there is at least one element present on a web page.
 * @param locator
 * @param timeout
 * @return
 */
public  List<WebElement> waitForAllElementToBePresent(By locator, long timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
}


/**
 
 An expectation for checking that an element is present on the DOM of a page and visible.
 Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
 * @param locator
 * @param timeout
 * @return
 */
public  WebElement waitForElementToBeVisible(By locator, long timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}

/**
 An expectation for checking that all elements present on the web page that match the locatorare visible. 
 Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
 
 * @param locator
 * @param timeout
 * @return
 */
public  List<WebElement> waitForAllElementToBeVisible(By locator, long timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
}


/**
 *An expectation for checking that the title contains a case-sensitive substring

 *
 * @param title
 * @param timeout
 * @return
 */
public  Boolean waitForTitleContains(String title, long timeout) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.titleContains(title));
}

/**
 
 An expectation for the URL of the current page to contain specific text.

 * @param urlFraction
 * @param timeout
 * @return
 */
public  Boolean waitFotURLFractionContains(String urlFraction, long timeout) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.urlContains(urlFraction));
}




public  String doGetPageTitle(String actTitle, long timeout)
{
	if(waitForTitleContains(actTitle,timeout))
	{
		return driver.getTitle();
	}
	return null;
}

public  String waitForURLToBe(String urlFraction, long timeout)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
	
	if(wait.until(ExpectedConditions.urlContains(urlFraction)))
	{
		return driver.getCurrentUrl();
	}
	return null;
}


public  WebElement waitForElmentPresenceWithFluentWait(By locator, int timeout, int pollingTime, String msg)
{
	Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeout))
			.pollingEvery(Duration.ofSeconds(pollingTime))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.withMessage(locator+" is not found within given timeout");
			
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}


//************************************************************** Wait methods for alert 

public  Alert waitForAlert(int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.alertIsPresent());
}

public String getAlertText(int timeout)
{
	return waitForAlert(timeout).getText();
}
public void acceptAlert(int timeout)
{
	 waitForAlert(timeout).accept();
}
public void dismissAlert(int timeout)
{
	waitForAlert(timeout).dismiss();
}


//************************************************************ wait methods for Frame

public  WebDriver waitForFrameByIndex(int frameIndex, int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
}

public  WebDriver waitForFrameByName(String fname, int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(fname));
}
public  WebDriver waitForFrameByLocator(By locator, int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
}

public  WebDriver waitForFrameByFrameElement(WebElement frameElement, int timeout)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
}


//**************************************************** ScreenShot********************

public void takeScreenShotOfPage(WebDriver driver)  {
	
	//create TakesScreenshot interface reference and cast driver object of type TakesScreenshot i.e. convert driver to Takesscreenshot
		TakesScreenshot shot=(TakesScreenshot)driver;
		
		
	//caall getScreenshotAs() method . it will take screenshot and stores as image file type in memory
		File srcShotfile=shot.getScreenshotAs(OutputType.FILE);
		
	//Move image file to new destination file
		Random ranNum=new Random();
		int num=ranNum.nextInt(50);
		System.out.println(num);
		String name="./screenShot"+num+".png";
		 File destShotfile=new File(name);
		 
		//Copy file at destination
		 
		 try {
			FileUtils.copyFile(srcShotfile, destShotfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

public  void takeScreenShotOfElement(WebElement ele)
{

File srcEleFile=ele.getScreenshotAs(OutputType.FILE);
try {
	FileUtils.copyFile(srcEleFile, new File("./Ele.png"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

}




