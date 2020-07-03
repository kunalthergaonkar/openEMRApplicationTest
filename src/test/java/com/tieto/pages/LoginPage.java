package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private  By userLocator=By.id("authUser");
	private  By passLocator=By.id("clearPass");
	private  By languageLocator=By.name("languageChoice");
	//private  By expectedValue=By.name("languageChoice");
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	public  void enterUserName(String username) 
	{
	driver.findElement(userLocator).sendKeys(username);
	}
	
	public  void enterPassWord(String password) 
	{
	driver.findElement(passLocator).sendKeys(password);
	}
	
	public  void enterLanguage(String language) 
	{
	driver.findElement(languageLocator).sendKeys(language);
	}
	
	public  String expectedValue(String expectedValue) 
	{
	return expectedValue;
	}
	
}
