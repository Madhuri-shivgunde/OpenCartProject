package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Constant {
	
	public static final long TIME_OUT = 10;
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="account/login";
	public static final String LOGIN_PAGE_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";

	
	
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	public static final List<String> ACCOUNT_PAGE_SECTION_TEXT_LIST = Arrays.asList("My Account",
																"My Orders",
																"My Affiliate Account",
																"Newsletter");
	
	
	public static final String REGISTER_PAGE_TITLE="Register Account";
	public static final String REGISTER_PAGE_URL_FRACTION="route=account/register";
	public static final String REGISTER_PAGE_HEADER ="Register Account";
	public static final String REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created!";
	
	
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String REGISTER_SHEET_NEGATIVE_NAME ="register_wrongData";
	
	public static final String REGISTER_PAGE_POLICY_ERROR_MESSAGE = " Warning: You must agree to the Privacy Policy!";

	public static final String REGISTER_PAGE_FNAME_SIZE_ERROR = "First Name must be between 1 and 32 characters!";
	public static final String REGISTER_PAGE_LNAME_SIZE_ERROR = "Last Name must be between 1 and 32 characters!";
	public static final String REGISTER_PAGE_EMAIL_ERROR = "E-Mail Address does not appear to be valid!";
	public static final String REGISTER_PAGE_ERROR_EMAIL_MESSAGE =" Warning: E-Mail Address is already registered!";
	public static final String REGISTER_PAGE_PASS_ERROR = "Password must be between 4 and 20 characters!";
	public static final String REGISTER_PAGE_TELE_SIZE_ERROR = "Telephone must be between 3 and 32 characters!";

	
	public static final String SEARCH_RESULT_PAGE_TITLE_FRACTION = "search";
	public static final String SEARCH_RESULT_PAGE_URL_FRACTION = "route=product/search&search=";
	
	public static final String PRODUCT_INFO_PAGE_URL_FRACTION = "oute=product/product&product_id=";

	public static final String REGISTER_PAGE_CPASS_ERROR = "Password confirmation does not match password!";


	

	
	
	
	

}
