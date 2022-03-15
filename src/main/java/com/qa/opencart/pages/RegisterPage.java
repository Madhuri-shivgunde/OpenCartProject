package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constant;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By header=By.cssSelector("div#content h1");
	
	private By firstName = By.cssSelector("input#input-firstname");
	private By lastName = By.cssSelector("input#input-lastname");
	
	private By email = By.cssSelector("input#input-email");
	private By tele= By.cssSelector("input#input-telephone");
	
	private By password=By.cssSelector("input#input-password");
	private By confirmPass=By.cssSelector("input#input-confirm");
	
	private By subScribe_Yes= By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subScribe_No= By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	
	private By agreeCheck =By.xpath("//input[@type='checkbox']");
	private By btnContinue= By.xpath("//input[@type='submit']");
	
	private By successMsg=By.cssSelector("div#content h1"); //Congratulations! Your new account has been successfully created!
	private By errorMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By errorFname=By.xpath("//div[text()='First Name must be between 1 and 32 characters!']");
	private By errorLname=By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']");
	private By errorEmail=By.xpath("//div[text()='E-Mail Address does not appear to be valid!']");
	private By errorTele=By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']");
	private By errorCpass=By.xpath("//div[text()='Password confirmation does not match password!']");
	private By errorPass=By.xpath("//div[text()='Password must be between 4 and 20 characters!']");


	//div[text()='Password confirmation does not match password!']

	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil =new ElementUtil(driver);
	}
	
	public String getRegisterPageTitle()
	{
		String title=eleUtil.doGetPageTitle(Constant.REGISTER_PAGE_TITLE, Constant.TIME_OUT);
		return title;
	}
	
	public String getRegisterPageUrl()
	{
		String url=eleUtil.waitForURLToBe(Constant.REGISTER_PAGE_URL_FRACTION, Constant.TIME_OUT);
		return url;
	}
		
	public String getRegisterPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean accountRegistration(String firstName,String lastName,
						   String email,String tele,String password,
						   String confirmPass,String subscribe)
	{
		eleUtil.doSendKeys(this.firstName,firstName);
		eleUtil.doSendKeys(this.lastName,lastName);
		eleUtil.doSendKeys(this.email,email);
		eleUtil.doSendKeys(this.tele,tele);
		eleUtil.doSendKeys(this.password,password);
		eleUtil.doSendKeys(this.confirmPass,confirmPass);
		
		if(subscribe.equalsIgnoreCase("Yes"))
		{
			eleUtil.doClick(subScribe_Yes);
		}
		else
		{
			eleUtil.doClick(subScribe_No);
		}
		
		eleUtil.doClick(agreeCheck);
		eleUtil.doClick(btnContinue);
		
		String Success_Msg=eleUtil.doGetText(successMsg);
		System.out.println(Success_Msg);
		

		if(Success_Msg.contains(Constant.REGISTER_SUCCESS_MESSAGE))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}
	
	
	
	
	public boolean accountRegistrationWithWrongData(String firstName,String lastName,
			   String email,String tele,String password,
			   String confirmPass,String subscribe)
	{
		eleUtil.doSendKeys(this.firstName,firstName);
		eleUtil.doSendKeys(this.lastName,lastName);
		eleUtil.doSendKeys(this.email,email);
		eleUtil.doSendKeys(this.tele,tele);
		eleUtil.doSendKeys(this.password,password);
		eleUtil.doSendKeys(this.confirmPass,confirmPass);

		if(subscribe.equalsIgnoreCase("Yes"))
		{
			eleUtil.doClick(subScribe_Yes);
		}
		else
		{
			eleUtil.doClick(subScribe_No);
		}

		//eleUtil.doClick(agreeCheck);
		eleUtil.doClick(btnContinue);

		
		if(firstName.isEmpty() || firstName.length()==0 || firstName.length()>32)
		{
			System.out.println("First name is: "+firstName);
			if(eleUtil.doGetText(errorFname).contains(Constant.REGISTER_PAGE_FNAME_SIZE_ERROR)) {
				return true;
			}
		}
		
		if(lastName.isEmpty() || lastName.length()==0 || lastName.length()>32)
		{
			System.out.println("Last name is: "+lastName);
			if(eleUtil.doGetText(errorLname).contains(Constant.REGISTER_PAGE_LNAME_SIZE_ERROR)) {
				return true;
			}
		}
		
		if(email.isEmpty() || email.length()==0 || !email.endsWith(".com") || !email.contains("@"))
		{
			if(eleUtil.doGetText(errorEmail).contains(Constant.REGISTER_PAGE_EMAIL_ERROR)) {
				return true;
			}
		}
		//isNumberic(tele)
		
		if(tele.isEmpty() || tele.length()<3 || tele.length()>32 || !isNumeric(tele))
		{
			System.out.println("tele no is: "+tele);
			if(eleUtil.doGetText(errorTele).contains(Constant.REGISTER_PAGE_TELE_SIZE_ERROR)) {
				return true;
			}
		}
		
		if(password.isEmpty() || password.length()<0 || password.length()>20)
		{
			System.out.println("Password is: "+password);
			if(eleUtil.doGetText(errorPass).contains(Constant.REGISTER_PAGE_PASS_ERROR)) {
				return true;
			}
		}
		
		if(confirmPass.isEmpty() || confirmPass.length()<0 || confirmPass.length()>20)
		{
			System.out.println("Confirm Password is: "+confirmPass);
			if(eleUtil.doGetText(errorPass).contains(Constant.REGISTER_PAGE_PASS_ERROR)) {
				return true;
			}
		}
		
		if(!confirmPass.equals(password))
		{
			
			if(eleUtil.doGetText(errorCpass).contains(Constant.REGISTER_PAGE_CPASS_ERROR)) {
				System.out.println("Password Missmathch");
				return true;
			}
		}
		
		
		String error_Msg=eleUtil.doGetText(errorMsg);
		
		if(!eleUtil.getElement(agreeCheck).isSelected())
		{
			if(error_Msg.contains(Constant.REGISTER_PAGE_POLICY_ERROR_MESSAGE))
			{
				System.out.println(" Warning: You must agree to the Privacy Policy!");
				return true;
			}
		}
		
		if(error_Msg.contains(Constant.REGISTER_PAGE_ERROR_EMAIL_MESSAGE))
		{
			System.out.println("Email is already in use!!!");
			return true;
		}
		if(error_Msg.contains(Constant.REGISTER_PAGE_POLICY_ERROR_MESSAGE))
		{
			System.out.println(" Warning: You must agree to the Privacy Policy!");
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Check for Numeric telephone number
	 * @param tnum
	 * @return
	 */
	public boolean isNumeric(String tnum){
		Long val;
		try {
			if(!tnum.isEmpty()) {
	        val = Long.parseLong(tnum);
	        return true;
			}
	    } catch (NumberFormatException e) {
	        System.out.println("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}

	

}
