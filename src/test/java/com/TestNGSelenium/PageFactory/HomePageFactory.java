package com.TestNGSelenium.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TestNGSelenium.helpers.GenericHelper;

public class HomePageFactory extends GenericHelper{

	//WebDriver driver;

    @FindBy(xpath="//table//tr[@class='heading3']")

    WebElement homePageUserName;

    

    public HomePageFactory(){

    	
        
        //This initElements method will create all WebElements

        PageFactory.initElements(getDriver(), this);

    }

    

    //Get the User name from Home Page

        public String getHomePageDashboardUserName(){

         return    homePageUserName.getText();

        }
}
