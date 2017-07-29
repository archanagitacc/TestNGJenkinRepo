package com.TestNGSelenium.Pojos;

public class User {

	String Username;
	String Password;

	public String getUserName(){

		return this.Username;

	}
	
	public void setUserName(String strUserName){

		this.Username = strUserName;

	}



	public String getPassword(){

		return this.Password;

	}  


	public void setPassword(String strPassword){

		this.Password = strPassword;

	}
}
