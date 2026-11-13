package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageDemo {
	public HomePageDemo(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="small-searchterms")
    private WebElement searchTextField;
    
    @FindBy(xpath="//input[@type='submit']")
    private WebElement searchbtn;
    
    public void searchProduct(String product) {
        searchTextField.sendKeys(product);
		searchbtn.click();
    }
}
