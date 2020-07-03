package com.tieto.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtilis;

public class LoginTest extends WebDriverWrapper {
	
	
	
	
	@Test(priority = 1)
	public void validCredentialTest()
	{

		//driver.findElement(By.id("authUser")).sendKeys("admin");
		//LoginPage.enterUserName(driver, "admin");
		//LoginPage.enterPassWord(driver, "pass");
		//driver.findElement(By.id("clearPass")).sendKeys("pass");
		LoginPage login= new LoginPage(driver);
		login.enterPassWord("pass");
		login.enterUserName("admin");
		login.enterLanguage("language");
		Select select= new Select(driver.findElement(By.name("languageChoice")));
		select.selectByValue("25");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Message Center']")));
		
		String actualTitle=driver.getTitle();
		Reporter.log(actualTitle);
		Assert.assertEquals(actualTitle,"OpenEMR");
		
	}
	@DataProvider
	public Object[][] validCredentialData() throws IOException
	{
		Object [][]main=ExcelUtilis.getSheetIntoObject("testdata/openEMRData.xlsx", "validCredentialData");
		return main;
	}
	
	@Test(dataProvider="validCredentialData")
	public void validCredentialTest(String username,String password,String language,String expectedValue)
	{
		LoginPage login= new LoginPage(driver);
		login.enterPassWord(password);
		login.enterUserName(username);
		login.enterLanguage(language);
		Select select= new Select(driver.findElement(By.name("languageChoice")));
		select.selectByValue("25");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Message Center']")));
		
		String actualTitle=driver.getTitle();
		Reporter.log(actualTitle);
		Assert.assertEquals(actualTitle,expectedValue);
	}
	
	@DataProvider
	public Object[][] invalidTestData()
	{
		
		Object[][]main= new Object[2][4];
		main[0][0]="john";
		main[0][1]="john123";
		main[0][2]="Frech(standard)";
		main[0][3]="Invalid username or password";
		main[1][0]="paul";
		main[1][1]="paul123";
		main[1][2]="English (Indian)";
		main[1][3]="Invalid username or password";
		
		return main;
		
	}
	
	@Test(priority = 2,dataProvider="invalidTestData")
	public void invalidCredentialTest(String username,String password,String language,String expectedValue)
	{
		
		LoginPage login= new LoginPage(driver);
		login.enterPassWord(password);
		login.enterUserName(username);
		login.enterLanguage(language);
		//driver.findElement(By.id("authUser")).sendKeys("admin123");
		//driver.findElement(By.id("clearPass")).sendKeys("pass");
		
		//Select select= new Select(driver.findElement(By.name("languageChoice")));
		//select.selectByValue("25");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String actualtext=driver.findElement(By.xpath("//div[contains(text(),'Invalid')]")).getText();
		//Assert.assertEquals(actualtext.trim(),"Invalid username or password");
		Assert.assertTrue(actualtext.contains(login.expectedValue(expectedValue)));
		
	}

}
