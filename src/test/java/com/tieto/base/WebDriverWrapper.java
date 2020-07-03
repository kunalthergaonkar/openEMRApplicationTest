package com.tieto.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tieto.utilities.PropUtilis;

public class WebDriverWrapper {
protected WebDriver driver;
	

	@Parameters({"browser"})
	@BeforeMethod
	public void init(@Optional("ch")String browsername) throws IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
		

		if(browsername.toLowerCase().equals("ch"))
				{
			
					driver= new ChromeDriver();
				}
		else
		{
			driver= new FirefoxDriver();
		}
		String baseUrl=PropUtilis.getValueFromKey("testdata/data.properties","url");
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
	}
		@AfterMethod
	public void end()
	{
	
		driver.quit();
	}
}
