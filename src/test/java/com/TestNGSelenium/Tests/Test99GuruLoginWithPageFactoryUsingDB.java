package com.TestNGSelenium.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TestNGSelenium.PageFactory.HomePageFactory;
import com.TestNGSelenium.PageFactory.LoginPageFactory;
import com.TestNGSelenium.Pojos.User;
import com.TestNGSelenium.helpers.GenericHelper;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;


public class Test99GuruLoginWithPageFactoryUsingDB {

	HomePageFactory objHomePage;

	LoginPageFactory objLogin;

	ExtentReports extent;
	
	ExtentTest test;
	
	String img_path;
	
	//static WebDriver driver;
	
	String testcaseName; 
	
	@BeforeTest 
	public void startReport(){
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyReport.html", false,DisplayOrder.NEWEST_FIRST, NetworkMode.OFFLINE);
	
		extent.addSystemInfo("HostName", "Archana")
		.addSystemInfo("Environment", "QA")
		.addSystemInfo("UserName", "Archana Shinde");
		
		test = extent.startTest("demoReportPass","filling practice form").assignCategory("practiceForm");
	}

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

		GenerateExtentReport();
		
		GenericHelper.ScreenshotToPDf(testcaseName);
		
	}
	
	public void GenerateExtentReport(){

		testcaseName = Test99GuruLoginWithPageFactoryUsingDB.class.getSimpleName();
		
		String img_path = GenericHelper.TakeScreenshot(testcaseName);

		// report with snapshot

		String img = test.addScreenCapture(img_path);

		test.log(LogStatus.INFO, "Image", "Image example: " + img);
	}

	@AfterTest
	public void endReport(){
		extent.endTest(test);
		extent.flush();
		extent.close();
	}

}
