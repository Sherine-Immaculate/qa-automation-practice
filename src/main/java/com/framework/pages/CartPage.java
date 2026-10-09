package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.utils.ReportManager;

public class CartPage extends BasePage{

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	private By text_title=By.xpath("//span[@class='title' and text()='Your Cart']");
	private By btn_Checkout=By.id("checkout");
	
	public void yourCartPage() {
		Assert.assertTrue(utils.isdisplayed(text_title));
		ReportManager.getTest().log(Status.INFO, "Step 7: Landed on cart page successfully");
		ReportManager.getTest().log(Status.INFO, "Step 8: Checkout button is selected");
		utils.click(btn_Checkout);
	}

}
