package com.TestNGSelenium.PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TestNGSelenium.helpers.GenericHelper;

public class LoginPageFactory extends GenericHelper{

	/**

	 * All WebElements are identified by @FindBy annotation

	 */



	@FindBy(name="uid")

	WebElement user99GuruName;



	@FindBy(name="password")

	WebElement password99Guru;



	@FindBy(className="barone")

	WebElement titleText;



	@FindBy(name="btnLogin")

	WebElement login;



	public LoginPageFactory(){

		//This initElements method will create all WebElements

		PageFactory.initElements(getDriver(), this);

	}

	//Set user name in textbox

	public void setUserName(String strUserName){

		user99GuruName.sendKeys(strUserName);



	}



	//Set password in password textbox

	public void setPassword(String strPassword){

		password99Guru.sendKeys(strPassword);

	}



	//Click on login button

	public void clickLogin(){

		login.click();

	}



	//Get the title of Login Page

	public String getLoginTitle(){

		return    titleText.getText();

	}

	/**

	 * This POM method will be exposed in test case to login in the application

	 * @param strUserName

	 * @param strPasword

	 * @return

	 */

	public void loginToGuru99(String strUserName,String strPasword){

		//Fill user name

		user99GuruName.sendKeys(strUserName);


		//Fill password

		password99Guru.sendKeys(strPasword);

		//Click Login button

		this.clickLogin();



	}

}
