package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlnk;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement adminImg1;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	public WebElement getAdminImg1() {
		return adminImg1;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}

	public WebElement getCampaignlnk() {
		return campaignlnk;
	}
	
	public void navigateToCampaginPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignlnk.click();
	}
	public void logout(WebDriver driver) throws Throwable {
		Thread.sleep(2000);
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.waitForElementPresent(driver, adminImg);
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutLnk.click();
		
	}

	
	
}
