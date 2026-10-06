package com.framework.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.base.BaseTest;
import com.framework.utils.ReportManager;
import com.framework.utils.Utilities;

public class TestListener implements ITestListener {
	

	
	 @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = ReportManager.getReportInstance()
	                .createTest(result.getMethod().getMethodName());
	        ReportManager.setTest(extentTest);
	    }
	 
	 @Override
	    public void onTestSuccess(ITestResult result) {
	        ReportManager.getTest().log(Status.PASS, "Test passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
	    	Utilities utils = new Utilities(driver);
	    	String path = utils.captureScreenshot(result.getMethod().getMethodName());
	      
	        ReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
	        ReportManager.getTest().addScreenCaptureFromPath(path);
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        ReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
	    }

	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	        ReportManager.getReportInstance().flush();   // writes the HTML file — runs once, after all tests in this run
	    }

}
