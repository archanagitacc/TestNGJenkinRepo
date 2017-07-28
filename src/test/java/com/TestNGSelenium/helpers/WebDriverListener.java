package com.TestNGSelenium.helpers;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;


public class WebDriverListener implements IInvokedMethodListener {

public void WebDriverListner() {
// Initializing it as part of constructor to be available for usage lifelong for the testsuite

}
public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isTestMethod()) {
        String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
        WebDriver driver = WebDriverSetup.createInstance(browserName);
        WebDriverManager.setWebDriver(driver);
    }
}

public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isTestMethod()) {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
         System.out.println("driver Success");
         if(testResult.isSuccess()){
        	driver.quit();
        	  driver = null;
        	}

        }
    }
}

     
  
  
   
}
