package com.TestNGSelenium.Tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TestNGSelenium.ReusableClasses.TestNGUsingYamlReusable;
import com.TestNGSelenium.helpers.GenericHelper;
import com.TestNGSelenium.helpers.Log;


public class TestNGWithPageFactoryUsingYaml extends BaseTest{

	
	
	
	@BeforeClass
	public void setUp(){
		
		DOMConfigurator.configure("src\\test\\resources\\log4j.xml");
		 
		Log.startTestCase("TestNGUsingYaml is started");
	}
	
	
	@Test
	public void fillPracticeForm() {

		GenericHelper.launchUrl("ToolSQAURL");

		TestNGUsingYamlReusable objPracticeform = new TestNGUsingYamlReusable();
		
		try {
			
			objPracticeform.fillPracticeFormReusable();
			//GenerateExtentReport();
			//helper.ScreenshotToPDf(testcaseName);
		
		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}
	
	
}
