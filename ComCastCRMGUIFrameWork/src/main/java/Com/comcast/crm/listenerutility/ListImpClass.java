package Com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener,ISuiteListener{
//	public ExtentSparkReporter spark;
//	public static ExtentReports report;
	public ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		String time =  new Date().toString().replace(" ", "_").replace(":", "_");
		
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite REsults");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
								
		//add Env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backUP");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("=== ===>"+result.getMethod().getMethodName()+">======START========");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED <===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=== ===>"+result.getMethod().getMethodName()+">=======END=======");
		test.log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED <===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		
        TakesScreenshot tdriver =(TakesScreenshot) BaseClass.sdriver;
		String src = tdriver.getScreenshotAs(OutputType.BASE64);
		String time =  new Date().toString().replace(" ", "_").replace(":", "_");
//		try {
//			File dest = new File("./screenshot/"+testName+"+"+time+".png");
//			
//			FileUtils.copyFile(src, dest);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		test.addScreenCaptureFromBase64String(src, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED <===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
