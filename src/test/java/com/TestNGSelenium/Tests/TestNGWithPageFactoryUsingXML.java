package com.TestNGSelenium.Tests;

import org.testng.annotations.Test;

import com.TestNGSelenium.ReusableClasses.TestNGUsingXmlReusable;

public class TestNGWithPageFactoryUsingXML extends BaseTest{
		
	@Test
	public void getDataFromXML(){


		TestNGUsingXmlReusable objXmlReusable = new TestNGUsingXmlReusable();

		objXmlReusable.getDataFromXMLReusable();

	}
	

}
