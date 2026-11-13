package Task;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Com.comcast.crm.objectrepositoryutility.HomePage;
import Com.comcast.crm.objectrepositoryutility.loginPage;
import contactPage.campaignPage;

public class createcampaign {
     
	@Test
	public void createCampaignTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://49.249.29.4:8888/");
		
		loginPage lp = new loginPage(driver);
		lp.getUsernameEdt().sendKeys("admin");
		lp.getPasswordEdt().sendKeys("admin");
		lp.getLoginBtn().click();
		
		HomePage hp = new HomePage(driver);
		hp.getMoreLink().click();
		hp.navigateToCampaginPage();
		hp.getAdminImg1().click();
		
		campaignPage cp = new campaignPage(driver);
		cp.getCampaignNameEdt("Automation Campaign");
		cp.getSaveBtn().click();
	}
}
