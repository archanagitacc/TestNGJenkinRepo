package com.TestNGSelenium.PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TestNGSelenium.helpers.GenericHelper;

public class ToolSQAPracticeFormPageFactory extends GenericHelper{
	/**

     * All WebElements are identified by @FindBy annotation

     */

   
    
    @FindBy(id="continents")

    public WebElement continent_selectlist;

  
    
    
    public ToolSQAPracticeFormPageFactory(){

       
        
        //This initElements method will create all WebElements

        PageFactory.initElements(getDriver(), this);

    }

}
