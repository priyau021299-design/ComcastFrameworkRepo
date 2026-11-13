package Com.ComCast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.generic.webdriverutility.JavaUtility;
import Com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import Com.comcast.crm.listenerutility.ListImpClass;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.creatingNewOrganizationPage;
import Com.comcast.crm.objectrepositoryutility.loginPage;
import Com.comcast.crm.objectrepositoryutility.organizationInfoPage;
import Com.comcast.crm.objectrepositoryutility.organizationPage;
import CreatingNewContactPage.CreatingNewContactPage;
import contactPage.ContactPage;

//Test case : 1
//Navigate to application
//Login to application
//Navigate to organization
//Navigate to "Create new organization" page by click on "+" image
//Create a organization with mandatory fields
//logout
@Listeners(Com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrganizationTest<eLib> extends BaseClass{
	@Test(groups="smokeTest")
	public void createOrgTest() throws Throwable{
  // public static void main(String[] args) throws Throwable {
//	/*create object*/
//    FileUtility fLib = new FileUtility();
//	ExcelUtility eLib = new ExcelUtility();
//	JavaUtility jLib = new JavaUtility();
//	
//	String BROWSER = fLib.getDataFromPropertiesFile("browser");
//	String URL = fLib.getDataFromPropertiesFile("Url");
//	String USERNAME = fLib.getDataFromPropertiesFile("Username");
//	String PASSWORD = fLib.getDataFromPropertiesFile("Password");
//	
//	//Generate the Random Number
//	Random random = new Random();
//	int randomInt = random.nextInt(40000);
//	//Read testscript data from Excel file
//	FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
//	Workbook wb = WorkbookFactory.create(fis1);
//	Sheet sh = wb.getSheet("org");
//	Row row = sh.getRow(1);
//	String orgName = eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
//	wb.close();
//	
//	WebDriver driver = null;
//	if(BROWSER.equals("chrome")) {
//		driver = new ChromeDriver();
//	}else if(BROWSER.equals("firefox")) {
//		driver = new FirefoxDriver();
//	}else if(BROWSER.equals("edge")) {
//		driver = new EdgeDriver();
//	}else {
//		driver = new ChromeDriver();
//	}
//	
//	//step 1 : login to app
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	driver.get(URL);
//	loginPage lp = new loginPage(driver);
//	lp.loginToapp(USERNAME, PASSWORD);
////	driver.manage().window().maximize();
////	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
////	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
////	driver.findElement(By.id("submitButton")).click();
    UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
    //Read testScript data from Excel file
	String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber(); 
	
	//step 2: navigate to organization module
	UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
	HomePage hp = new HomePage(driver);
	hp.getOrgLink().click();
 	//driver.findElement(By.linkText("Organizations")).click();
	
	//step 3: Click on "create organization" button
	UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
	organizationPage cnp = new organizationPage(driver);
	cnp.getCreateNewOrgBtn().click();
	
	//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();   
	
	
	//Step 4:Enter all the details & create new organization
	UtilityClassObject.getTest().log(Status.INFO, "create a new org");
	creatingNewOrganizationPage cnop = new creatingNewOrganizationPage(driver);
	cnop.createOrg(orgName);
	UtilityClassObject.getTest().log(Status.INFO, orgName +"====>create a new org");
	
//	driver.findElement(By.name("accountname")).sendKeys(orgName);
//	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//Verify Header msg Expected Result
	organizationInfoPage oip = new organizationInfoPage(driver);
	String actOrgName = oip.getHeaderMsg().getText();
	if(actOrgName.contains(orgName)) {
		System.out.println(orgName + " name is verified == PASS");
	}else {
		System.out.println(orgName + " name is not verified == FAIL");
	}
//	String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//	if(headerInfo.contains(orgName)) {
//		System.out.println(orgName+" is created==PASS");
//	}else {
//		System.out.println(orgName+" is not created==FAIL");
//	}
	//Verify Header orgName info Expected Result
//	String actorgName =driver.findElement(By.id("dtlview_Organization Name ")).getText();
//	if(actorgName.equals(orgName)) {
//		System.out.println(orgName+" information is created==PASS");
//	}else {
//		System.out.println(orgName+" information is not created==FAIL");
//	}
	//step 5:logout
//	hp.logout();
//	Actions act = new Actions(driver);
//	Thread.sleep(2000);
//	WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//	Thread.sleep(2000);
//	act.moveToElement(admin).perform();
//			driver.findElement(By.linkText("Sign Out")).click();
//	driver.quit();
}
	@Test(groups="regressionTest")
	public void createContactWithPhoneNumberTest() throws Throwable {
		        //read testscript data from Excel file
				String orgName = eLib.getDataFromExcel("org",7,2)+ jLib.getRandomNumber();
				String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		    	
				//Step 2: navigate to contact module
				HomePage hp = new HomePage(driver);
				hp.getOrgLink().click();
				
				//Step 3: Click on "create organization" button
				organizationPage cnp = new organizationPage(driver);
				cnp.getCreateNewOrgBtn().click();
				
				//Step 4 : Enter all the details & create new contact
//				String endDate = jLib.getSystemDateYYYYDDMM();
//				String startDate = jLib.getRequiredDateYYYYDDMM(30);
//				CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
//				ccp.createContactWithSupportDate(lastName,startDate,endDate);
				creatingNewOrganizationPage cnop = new creatingNewOrganizationPage(driver);
				cnop.createOrgPhoneNumber(orgName,phoneNumber);
				
		    	//Verify Header phone Number info Expected Result
//		    	String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
//		    	if(actStartDate.equals(startDate)) {
//		    		System.out.println(startDate+" information is verified==PASS");
//		    	}else {
//		    		System.out.println(startDate+" information is not verified==FAIL");
//		    	}
//		    	String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
//		    	if(actEndDate.equals(endDate)) {
//		    		System.out.println(endDate+" information is verified==PASS");
//		    	}else {
//		    		System.out.println(endDate+" information is not verified==FAIL");
//		    	}
				//Verify header phone Number info Expected Result
		    	String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		    	if(actPhoneNumber.equals(phoneNumber)) {
		    		System.out.println(phoneNumber+" information is created==PASS");
		    	}else {
		    		System.out.println(phoneNumber+" information is not created==FAIL");
		    	}
//		    	//Step 5:logout
//		    	driver.quit();
			}
	@Test(groups="regressionTest")
	public void createOrgWithIndustriesTest() throws Throwable {
		WebDriver driver = null;

		//Read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org",4,2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org",4,3);
		String type = eLib.getDataFromExcel("org", 4, 4);
		
		//Step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//Step 3 : click on "create organization" button
		organizationPage cnp = new organizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//Step 4: Enter all the details & create new organization
		creatingNewOrganizationPage cnop = new creatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
    	
//		//Step 5: navigate to Contact module
//		hp.getContactlnk().click();
//				
//		//Step 6: Click on "create contact" button
//		ContactPage cp = new ContactPage(driver);
//		cp.getCreateNewOrgBtn().click();
//				
//		//Step 7 :Enter all the details & create new Contact
//		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
//		ccp.createContactwithOrg(contactLastName,orgName);
				
//		//Verify Header orgName info Expected Result
//		String actOrgName =driver.findElement(By.id("mouseArea-organizationName")).getText();
//	    if(actOrgName.trim().equals(orgName)) {
//			System.out.println(orgName+" information is created==PASS");
//		}else {
//		    System.out.println(orgName+" information is not created==FAIL");
//		}
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
	}
}
