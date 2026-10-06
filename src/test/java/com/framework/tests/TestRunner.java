package com.framework.tests;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.framework.listeners.TestListener;
import com.framework.pages.LoginPage;
import com.framework.pages.ProductsPage;


@Listeners(TestListener.class)
public class TestRunner extends BaseTest {
	
	@Test(dataProvider ="LoginData")
	public void register_User(String Username,String emailAddress) {
		 System.out.println("Running on thread: " + Thread.currentThread().getId());
		LoginPage lp=new LoginPage(getDriver());
		ProductsPage pp=new ProductsPage(getDriver());
		lp.userSignUp(Username, emailAddress);
		pp.selectProduct_AddtoCart();
	}
	
	@DataProvider(name="LoginData",parallel = false)
	public Object[][] logindatafromExcel() throws IOException{
		 
		List<Object[]> rows=new ArrayList<Object[]>();
		
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir") +"/src/test/resources/TestData.xlsx");
		
		Workbook workbook= new XSSFWorkbook(fis);
		
		 Sheet sheet = workbook.getSheet("login");
		 
		 for (int i = 1; i <= sheet.getLastRowNum(); i++) {   // start at 1 to skip the header row
	            Row row = sheet.getRow(i);

	            String username = row.getCell(0).getStringCellValue();
	            String emailaddress = row.getCell(1).getStringCellValue();

	            rows.add(new Object[] {username,emailaddress});
	        }
	    
		return rows.toArray(new Object[0][]);
		
	}

}
