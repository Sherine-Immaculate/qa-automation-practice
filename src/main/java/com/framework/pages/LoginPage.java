package com.framework.pages;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.utils.ReportManager;

public class LoginPage extends BasePage {
	


	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private static final Logger log=LogManager.getLogger(LoginPage.class);
	
	private By text_header=By.xpath("//div[text()='Swag Labs']");
	private By textbox_username=By.id("user-name");
	private By textbox_password=By.id("password");
	private By button_login=By.id("login-button");
	private By text_errormessage=By.xpath("//h3[@role='alert']");
	
	public void userSignUp(String username, String password) {
		log.info("Home page is displayed");
		Assert.assertTrue(utils.isdisplayed(text_header));
		ReportManager.getTest().log(Status.INFO, "Home page is displayed successfully");
		utils.type(textbox_username, username);
		ReportManager.getTest().log(Status.INFO, "Step 1: Entering username on the Login page");
		utils.type(textbox_password, password);
		ReportManager.getTest().log(Status.INFO, "Step 2: Entering password on the Login page");
		utils.click(button_login);
		ReportManager.getTest().log(Status.INFO, "Step 3: Clicking on the submit button");
		utils.checkVisibility(text_errormessage);
		
	}

}
