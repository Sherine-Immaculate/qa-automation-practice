package com.framework.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.framework.utils.ConfigReader;

public class BaseTest {
	
	 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) {
		String driverPath; 
		
		WebDriver localDriver;

	    switch (browser.toLowerCase()) {
	        case "firefox":
	            localDriver = new FirefoxDriver();
	            break;
	        case "edge":
	        	driverPath=System.getProperty("user.dir")+"/src/test/resources/drivers/msedgedriver.exe";
	    		System.setProperty("webdriver.edge.driver", driverPath);
	            localDriver = new EdgeDriver();
	            break;
	        default:
	            localDriver = new ChromeDriver();
	    }
		
		localDriver.manage().window().maximize();
		driver.set(localDriver);
		driver.get().get(ConfigReader.get("URL"));
	}
	
	 public WebDriver getDriver() {
	        return driver.get();
	    }
	 
	@AfterMethod
	public void teardown(ITestResult result) {
		 if (result.getStatus() == ITestResult.FAILURE) {
		        try {
		            TakesScreenshot ts = (TakesScreenshot) driver.get();
		            File temp = ts.getScreenshotAs(OutputType.FILE);
		            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		            File saved = new File("screenshots/" + result.getName() + "_" + timestamp + ".png");
		            saved.getParentFile().mkdirs();
		            FileUtils.copyFile(temp, saved);
		            System.out.println("Screenshot saved: " + saved.getAbsolutePath());
		        } catch (IOException e) {
		            System.out.println("Screenshot failed: " + e.getMessage());
		        }
		    } else {
		        System.out.println(result.getName() + " passed, no screenshot needed");
		    }

		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
