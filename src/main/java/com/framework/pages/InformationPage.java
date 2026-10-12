package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.utils.ReportManager;

public class InformationPage extends BasePage{

	public InformationPage(WebDriver driver) {
		super(driver);
	}
	
	private By pageTitle=By.xpath("//span[@class='title' and text()='Checkout: Your Information']");
	
	public void enterUserInformation() {
		Assert.assertTrue(utils.isdisplayed(pageTitle));
		ReportManager.getTest().log(Status.INFO, "Step 8: Landed on Information page successfully");
	}

}
