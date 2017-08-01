package com.TestNGSelenium.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestNGSelenium.PageFactory.HomePageFactory;
import com.TestNGSelenium.PageFactory.LoginPageFactory;
import com.TestNGSelenium.Pojos.User;
import com.TestNGSelenium.helpers.GenericHelper;


public class Test99GuruLoginWithPageFactoryUsingDB extends BaseTest{

	HomePageFactory objHomePage;

	LoginPageFactory objLogin;

	
	
	String img_path;
	
	//static WebDriver driver;
	
	String testcaseName; 
	
	

	/**

	 * This test go to http://demo.guru99.com/V4/

	 * Verify login page title as guru99 bank

	 * Login to application

	 * Verify the home page using Dashboard message

	 */

	@Test

	public void test_Home_Page_Appear_Correct(){

		//Create Login Page object

		 GenericHelper.launchUrl("Gurru99URL"); 
		 
		objLogin = new LoginPageFactory();

		//Verify login page title

		String loginPageTitle = objLogin.getLoginTitle();

		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

		//login to application
		
		User user = GenericHelper.GetUserDetailsFromDb();
					
		objLogin.loginToGuru99(user.getUserName(), user.getPassword());

		// go the next page

		objHomePage = new HomePageFactory();

		//Verify home page

		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr86459"));
	
		
		GenericHelper.ScreenshotToPDf(testcaseName);
		
	}
	
	

}
