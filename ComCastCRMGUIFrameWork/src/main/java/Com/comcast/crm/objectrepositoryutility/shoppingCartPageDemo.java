package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingCartPageDemo {
	public shoppingCartPageDemo(WebDriver driver) {
       PageFactory.initElements(driver, this);
    }
	@FindBy(id="termsofservice")
	private WebElement termsCheckbox;

	@FindBy(id="checkout")
	private WebElement checkoutButton;

	public void clickTermsAndCheckout() {

	    termsCheckbox.click();

	    checkoutButton.click();
	}
}
