package Com.ComCast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.generic.webdriverutility.JavaUtility;
import Com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.creatingNewOrganizationPage;
import Com.comcast.crm.objectrepositoryutility.organizationPage;
import CreatingNewContactPage.CreatingNewContactPage;
import contactPage.ContactPage;

//Test case : 6
//Navigate to application
//Login to application
//Place the mouse cursor and click on "contact" link
//Navigate to "Create new contact" page by click on "+" image
//Create a contact without mandatory fields
//Click on organization Name lookup image "+"
//Search for organization & select the same
//Click on save
//logout
public class CreateContactWithOrgTest extends BaseClass{
	//public static void main(String[] args) throws Throwable {
	@Test
	public void CreateContact() throws Throwable {
		WebDriver driver = null;
		/*create object*/
//	    FileUtility fLib = new FileUtility();
//		ExcelUtility eLib = new ExcelUtility();
//		JavaUtility jLib = new JavaUtility();
//		WebDriverUtility wLib = new WebDriverUtility();
//		
//		String BROWSER = fLib.getDataFromPropertiesFile("browser");
//		String URL = fLib.getDataFromPropertiesFile("Url");
//		String USERNAME = fLib.getDataFromPropertiesFile("Username");
//		String PASSWORD = fLib.getDataFromPropertiesFile("Password");
		
		//Generate the Random Number
//		Random random = new Random();
//		int randomInt = random.nextInt(40000);
//		//Read testscript data from Excel file
//		FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//		Sheet sh = wb.getSheet("contact");
//		Row row = sh.getRow(7);
//		String orgName = eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
//		String contactLastName = row.getCell(3).getStringCellValue();
//		wb.close();
		
//		WebDriver driver = null;
//		if(BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		}else if(BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		}else if(BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		}else {
//			driver = new ChromeDriver();
//		}
		
		//step 1 : login to app
//		wLib.waitForPageToLoad(driver);
//		driver.get(URL);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
//		//step 2: navigate to organization module
//	 	driver.findElement(By.linkText("Organizations")).click();
//		//step 3: Click on "create organization" button
//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();    
//		//Step 4:Enter all the details & create new organization
//		driver.findElement(By.name("accountname")).sendKeys(orgName);
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		//Verify Header msg Expected Result
//		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerInfo.contains(orgName)) {
//			System.out.println(orgName+" header verified==PASS");
//		}else {
//			System.out.println(orgName+" header is not created==FAIL");
//		}
//		//step 5: navigate to organization module
//     	driver.findElement(By.linkText("Contacts")).click();
//    	//step 6: Click on "create organization" button
//    	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();    
//    	//Step 7:Enter all the details & create new organization
//    	driver.findElement(By.name("lastname")).sendKeys(contactLastName);
//    	driver.findElement(By.xpath("//input[@type='text']/following-sibling::img")).click();
//    	
    	//Switch to child window
//    	wLib.switchTabonURL(driver,"module=Accounts");
//    	
//    	driver.findElement(By.name("search_text")).sendKeys(orgName);
//    	driver.findElement(By.name("search")).click();
//    	driver.findElement(By.xpath("//a[text()'"+orgName+"']")).click();
//    	
//    	//Switch to parent window
//    	wLib.switchTabonURL(driver,"Contacts&action");
//   
//    	
//    	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("contact",7,2)+jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact",7,3)+jLib.getRandomNumber();
		
		//Step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//Step 3 : click on "create organization" button
		organizationPage cnp = new organizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//Step 4: Enter all the details & create new organization
		creatingNewOrganizationPage cnop = new creatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
    	
//		//Verify Header orgName info Expected Result
//         headerInfo =driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerInfo.equals(contactLastName)) {
//			System.out.println(contactLastName+" header verified==PASS");
//		}else {
//			System.out.println(contactLastName+" header is not verified==FAIL");
//		}
		
		//Step 5: navigate to Contact module
		hp.getContactlnk().click();
		
		//Step 6: Click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();
		
		//Step 7 :Enter all the details & create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactwithOrg(contactLastName,orgName);
		
		//Verify Header orgName info Expected Result
        String actOrgName =driver.findElement(By.id("mouseArea-organizationName")).getText();
		if(actOrgName.trim().equals(orgName)) {
			System.out.println(orgName+" information is created==PASS");
		}else {
			System.out.println(orgName+" information is not created==FAIL");
		}
		
//		driver.quit();
	}
}
