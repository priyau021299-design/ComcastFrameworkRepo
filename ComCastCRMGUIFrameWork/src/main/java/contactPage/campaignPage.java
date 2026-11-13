package contactPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class campaignPage {
	WebDriver driver;
	public campaignPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaignname")
	private WebElement campaignNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="campaigntype")
	private WebElement campaignDD;
	
	public WebElement getCampaignDD() {
		return campaignDD;
	}

	public void getCampaignNameEdt(String name) {
		 campaignNameEdt.sendKeys(name);;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	
}
