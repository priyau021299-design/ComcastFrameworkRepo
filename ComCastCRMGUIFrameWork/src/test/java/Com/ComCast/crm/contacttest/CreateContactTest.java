package Com.ComCast.crm.contacttest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.generic.webdriverutility.JavaUtility;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.creatingNewOrganizationPage;
import Com.comcast.crm.objectrepositoryutility.organizationPage;
import CreatingNewContactPage.CreatingNewContactPage;
import contactPage.ContactPage;
import junit.framework.Assert;
/*
 * 
 * @author Sathishkumar
 * 
 */
//Test case : 4
//Navigate to application
//Login to application
//Place the mouse cursor and click on "contact" link
//Navigate to "Create new contact" page by click on "+" image
//Create a contact without mandatory fields and save 
//logout
public class CreateContactTest extends BaseClass{
	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {

//    	//Generate the Random Number
//    	Random random = new Random();
//    	int randomInt = random.nextInt(1000);
//    	//Read testscript data from Excel file
//    	FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
//    	Workbook wb = WorkbookFactory.create(fis1);
//    	Sheet sh = wb.getSheet("contact");
//    	Row row = sh.getRow(1);

		/* Read testscript data from Excel file*/
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		// wb.close();

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// driver.findElement(By.linkText("Contacts")).click();

		// step 3: Click on "create organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		// driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		// Step 4:Enter all the details & create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);
//    	driver.findElement(By.name("lastname")).sendKeys(lastName);
//    	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Header
//		String actHeader = driver.findElement(By.className("dvHeaderText")).getText();
//		if(actHeader.equals(lastName)) {
//			System.out.println(lastName + "Header is verified==PASS");
//		}else {
//			System.out.println(lastName + "Header is not verified==FAIL");
//		}
		//Verify Header phone Number info Expected Result
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		
		

		// Verify lastname info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
//		if (actLastName.equals(lastName)) {
//			System.out.println(lastName + " information is verified==PASS");
//		} else {
//			System.out.println(lastName + " information is not verified==FAIL");
//		}
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName,lastName);
		soft.assertAll();

	}
	
	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
		        //read testscript data from Excel file
				String lastName = eLib.getDataFromExcel("contact",4,2)+ jLib.getRandomNumber();
		    	
				//Step 2: navigate to contact module
				HomePage hp = new HomePage(driver);
				hp.getContactlnk().click();
				
				//Step 3: Click on "create contact" button
				ContactPage cp = new ContactPage(driver);
				cp.getCreateNewOrgBtn().click();
				
				//Step 4 : Enter all the details & create new contact
				String endDate = jLib.getSystemDateYYYYDDMM();
				String startDate = jLib.getRequiredDateYYYYDDMM(30);
				CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
				ccp.createContactWithSupportDate(lastName,startDate,endDate);
				
		    	//Verify Header phone Number info Expected Result
		    	String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		    	if(actStartDate.equals(startDate)) {
		    		System.out.println(startDate+" information is verified==PASS");
		    	}else {
		    		System.out.println(startDate+" information is not verified==FAIL");
		    	}
//				ContactsInfoPage cip = new ContactsInfoPage(driver);
//				String actStartDate = cip.getSupportStartDateInfo().getText();
//				Assert.assertEquals(actStartDate.trim(),startDate);
				
		    	String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		    	if(actEndDate.equals(endDate)) {
		    		System.out.println(endDate+" information is verified==PASS");
		    	}else {
		    		System.out.println(endDate+" information is not verified==FAIL");
		    	}
//				String actendDate = cip.getSupportEndDateInfo().getText();
//				Assert.assertEquals(actendDate.trim(), endDate);
//		    	//Step 5:logout
//		    	driver.quit();
			}
	
	
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		WebDriver driver = null;

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
    	
		//Step 5: navigate to Contact module
		hp.getContactlnk().click();
				
		//Step 6: Click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();
				
		//Step 7 :Enter all the details & create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactwithOrg(contactLastName,orgName);
				
		//Verify Header orgName info Expected Result
//		String actOrgName =driver.findElement(By.id("mouseArea-organizationName")).getText();
//	    if(actOrgName.trim().equals(orgName)) {
//			System.out.println(orgName+" information is created==PASS");
//		}else {
//		    System.out.println(orgName+" information is not created==FAIL");
//		}
		String actOrgName = driver.findElement(By.id("mouseArea-organizationName")).getText();
		//actOrgName = oip.getOrgNameInfo().getText();
		Assert.assertEquals(actOrgName.trim(),orgName);
	}
}
