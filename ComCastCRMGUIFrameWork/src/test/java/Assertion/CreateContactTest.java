package Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.creatingNewOrganizationPage;
import Com.comcast.crm.objectrepositoryutility.organizationPage;
import CreatingNewContactPage.CreatingNewContactPage;
import contactPage.ContactPage;

public class CreateContactTest<jLib> extends BaseClass{
	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {
		// Read testscript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		// wb.close();

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step 3: Click on "create organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();

		// Step 4:Enter all the details & create new organization
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);   	
		
		//Verify Header
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status,true);

		// Verify lastname info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName,lastName);
		

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
		    	String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		    	if(actEndDate.equals(endDate)) {
		    		System.out.println(endDate+" information is verified==PASS");
		    	}else {
		    		System.out.println(endDate+" information is not verified==FAIL");
		    	}
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
		String actOrgName =driver.findElement(By.id("mouseArea-organizationName")).getText();
	    if(actOrgName.trim().equals(orgName)) {
			System.out.println(orgName+" information is created==PASS");
		}else {
		    System.out.println(orgName+" information is not created==FAIL");
		}
	}
}

