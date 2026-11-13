package Com.ComCast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

//Test case : 2
//Navigate to application
//Login to application
//Navigate to organization
//Navigate to "Create new organization" page by click on "+" image
//Check while creating New organization "Industry" drop down option is working or not
//logout
public class CreateOrganizationWithIndustriesTest {
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
    	int randomInt = random.nextInt(1000);
    	//Read testscript data from Excel file
    	FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
    	Workbook wb = WorkbookFactory.create(fis1);
    	Sheet sh = wb.getSheet("org");
    	Row row = sh.getRow(4);
    	String orgName = row.getCell(2).toString()+randomInt;
    	String industry = row.getCell(3).toString();
    	String type = row.getCell(4).toString();
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
    	WebElement wbsele1 = driver.findElement(By.name("industry"));
    	Select sel1 = new Select(wbsele1);
    	sel1.selectByVisibleText(industry);
    	WebElement wbsele2 = driver.findElement(By.name("accounttype"));
    	Select sel2 = new Select(wbsele2);
    	sel2.selectByVisibleText(type);
    	
    	//Verify the industries and type info
    	String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
    	if(actIndustries.equals(industry)) {
    		System.out.println(industry+" information is verified==PASS");
    	}else {
    		System.out.println(industry+" information is not verified==FAIL");
    	}
    	String actType = driver.findElement(By.id("dtlview_Type")).getText();
    	if(actType.equals(type)) {
    		System.out.println(type+" information is verified==PASS");
    	}else {
    		System.out.println(type+" information is not verified==FAIL");
    	}
    	//Step 5:logout
    	driver.quit();
	}
}
