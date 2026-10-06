package com.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.framework.utils.Utilities;

public abstract class BasePage {
	
	 protected WebDriver driver;
	 protected Utilities utils;

	 public BasePage(WebDriver driver) {
	        this.driver = driver;
	        this.utils = new Utilities(driver);
	        
	    }

	   

}
