package Com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.comcast.crm.generic.databaseutility.DataBaseUtility;
import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.generic.webdriverutility.JavaUtility;
import Com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import Com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.loginPage;

public class BaseClass extends WebDriverUtility{
public DataBaseUtility dbLib = new DataBaseUtility();
public FileUtility fLib = new FileUtility();
public ExcelUtility eLib = new ExcelUtility();
public JavaUtility jLib = new JavaUtility();
public   WebDriver driver = null;
public static WebDriver sdriver = null;

@BeforeSuite
public void configBS() throws Throwable {
	dbLib.getDbConnection();
	System.out.println("==Connect to database, Report config===");
	
}

@BeforeClass
public void configBC() throws Throwable {
	
	String browser = fLib.getDataFromPropertiesFile("browser");
System.out.println(browser);
	if(browser.equals("chrome")){
		driver = new ChromeDriver();	
	}
	else if (browser.equals("edge")) {
		driver = new EdgeDriver();
	}
	else if(browser.equals("firefox")) {
		driver= new FirefoxDriver();
	}
	else {
		driver = new ChromeDriver();
	}
	sdriver = driver;
	UtilityClassObject.setDriver(driver);
	System.out.println("==Launch the Browser==");
}

@BeforeMethod
public void coufigBM() throws Throwable {
	waitForPageToLoad(driver);
	String url = fLib.getDataFromPropertiesFile("url");
	String username = fLib.getDataFromPropertiesFile("username");
	String password = fLib.getDataFromPropertiesFile("password");
	System.out.println(url+username+password);
	loginPage lp = new loginPage(driver);
	lp.loginToapp(url, username, password);
	System.out.println("==login==");
}

@AfterMethod
public void configAM() throws Throwable{
	HomePage hp = new HomePage(driver);
	hp.logout(driver);
	System.out.println("=logout=");
}
@AfterClass
public void configAC() {
	driver.quit();
	System.out.println("==close The BROWSER");
}

@AfterSuite
public void configAS() throws Throwable {
	System.out.println("==close DB , Report backUp==");
	dbLib.closeDbConnection();
	
}
}