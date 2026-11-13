package Com.ComCast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
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

import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.generic.webdriverutility.JavaUtility;
import Com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.creatingNewOrganizationPage;
import Com.comcast.crm.objectrepositoryutility.loginPage;
import Com.comcast.crm.objectrepositoryutility.organizationInfoPage;
import Com.comcast.crm.objectrepositoryutility.organizationPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {
		/*create object*/
	    FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("Url");
		String USERNAME = fLib.getDataFromPropertiesFile("Username");
		String PASSWORD = fLib.getDataFromPropertiesFile("Password");
		
		//Generate the Random Number
//		Random random = new Random();
//		int randomInt = random.nextInt(40000);
		//Read testscript data from Excel file
//		FileInputStream fis1 = new FileInputStream("./testdata/testscriptdata.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//		Sheet sh = wb.getSheet("org");
//		Row row = sh.getRow(10);
		String orgName = eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNumber();
		
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		loginPage lp = new loginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD, orgName);

		
		//step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
	 	
		
		//step 3: Click on "create organization" button
		organizationPage cnp = new organizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		
		
		//Step 4:Enter all the details & create new organization
		creatingNewOrganizationPage cnop = new creatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		

		//Verify Header msg Expected Result
		organizationInfoPage oip = new organizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified == PASS");
		}else {
			System.out.println(orgName + " name is not verified == FAIL");
		}
		
		//go back to oranganization page
		hp.getOrgLink().click();
		
		//SEarch for organization
		cnp.getSearchEdt().sendKeys(orgName);
		wLib.select(cnp.getSearchDD(), "Organization Name");
		cnp.getSearchBtn().click();
		
		//In dynamic webtable select & delete org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
		//step 5:logout
		hp.logout(driver);
		
		driver.quit();
	}
}
