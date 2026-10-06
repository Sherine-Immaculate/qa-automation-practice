package com.framework.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilities {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;
	
	public Utilities(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		this.action=new Actions(driver);
	}
	
	public void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public boolean isdisplayed(By locator) {
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.isDisplayed();
	}
	
	public void type(By locator,String data) {
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(data);
	}
	
	public String getText(By locator) {
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.getText();
	}
	
	public  String captureScreenshot(String testName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

	    try {
	        FileUtils.copyFile(source, new File(destPath));
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to save screenshot", e);
	    }
	    return destPath;
	}
	
	public void checkVisibility(By locator) {
		List<WebElement> elements=driver.findElements(locator);
		Assert.assertTrue(elements.isEmpty(), "Error message should not be displayed");
	}
}
