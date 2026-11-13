package Com.ComCast.crm.orgtest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//Test case : 3
//Navigate to application
//Login to application
//Navigate to organization
//Navigate to "Create new organization" page by click on "+" image
//Create org with orgName name & phoneNumber
//logout
public class CreateOrgWithPhoneNumberTest {
	public static void main(String[] args) throws Throwable {
		//Read the common data from JSON File
    	FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
    	Properties pobj = new Properties();
    	pobj.load(fis);
    	
    	String BROWSER = pobj.getProperty("browser");
    	String URL = pobj.getProperty("Url");
    	String USERNAME = pobj.getProperty("Username");
    	String PASSWORD = pobj.getProperty("Password");
    	
    	//Generate the Random Number
    	Random random = new Random();
    	int randomInt = random.nextInt(5000);
    	//Read testscript data from Excel file
    	FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
    	Workbook wb = WorkbookFactory.create(fis1);
    	Sheet sh = wb.getSheet("org");
    	Row row = sh.getRow(7);
    	String orgName = row.getCell(2).toString()+randomInt;
    	String phoneNumber = row.getCell(3).getStringCellValue();
        wb.close();
    	
    	WebDriver driver = null;
    	if(BROWSER.equals("chrome")) {
    		driver = new ChromeDriver();
    	}else if(BROWSER.equals("firefox")) {
    		driver = new FirefoxDriver();
    	}else if(BROWSER.equals("edge")) {
    		driver = new EdgeDriver();
    	}else {
    		driver = new ChromeDriver();
    	}
    	
    	//step 1 : login to app
    	driver.get(URL);
    	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
    	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
    	driver.findElement(By.id("submitButton")).click();
    	//step 2: navigate to organization module
     	driver.findElement(By.linkText("Organizations")).click();
    	//step 3: Click on "create organization" button
    	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();    
    	//Step 4:Enter all the details & create new organization
    	driver.findElement(By.name("accountname")).sendKeys(orgName);
    	driver.findElement(By.name("phone")).sendKeys(phoneNumber);
    	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
    	
    	//Verify header phone Number info Expected Result
    	String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
    	if(actPhoneNumber.equals(phoneNumber)) {
    		System.out.println(phoneNumber+" information is created==PASS");
    	}else {
    		System.out.println(phoneNumber+" information is not created==FAIL");
    	}
    	
    	//Step 5:logout
    	driver.quit();
	}
}
