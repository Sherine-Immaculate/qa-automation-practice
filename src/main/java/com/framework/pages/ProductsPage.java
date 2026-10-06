package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.utils.ReportManager;

public class ProductsPage extends BasePage{

	public ProductsPage(WebDriver driver) {
		super(driver);
		
	}
	
	private By text_title=By.xpath("//span[@class='title']");
	private By button_AddtoCart=By.id("add-to-cart-sauce-labs-backpack");
	private By text_items=By.className("shopping_cart_badge");
	
	public void selectProduct_AddtoCart() {
		Assert.assertEquals(utils.getText(text_title),"Products");
		ReportManager.getTest().log(Status.INFO, "Step 4: Landed on Products page successfully");
		utils.click(button_AddtoCart);
		Assert.assertEquals(utils.getText(text_items), "1");
	}
	
	

}
