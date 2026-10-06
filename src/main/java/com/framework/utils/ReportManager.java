package com.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReportInstance() {
    	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/reports/TestReport"+"_"+timestamp+".html");
            
            sparkReporter.config().setReportName("Automation Test Report");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
        }
		return extent;
    
    }
    
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
