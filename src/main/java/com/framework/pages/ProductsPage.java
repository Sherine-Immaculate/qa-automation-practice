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
	private By button_cart=By.className("shopping_cart_link");
	
	public void selectProduct_AddtoCart() {
		Assert.assertEquals(utils.getText(text_title),"Products");
		ReportManager.getTest().log(Status.INFO, "Step 4: Landed on Products page successfully");
		ReportManager.getTest().log(Status.INFO, "Step 5: Product is added to cart");
		utils.click(button_AddtoCart);
		Assert.assertEquals(utils.getText(text_items), "1");
		ReportManager.getTest().log(Status.INFO, "Step 6: Cart is selected to perform checkout");
		utils.click(button_cart);
	}
	
	

}
