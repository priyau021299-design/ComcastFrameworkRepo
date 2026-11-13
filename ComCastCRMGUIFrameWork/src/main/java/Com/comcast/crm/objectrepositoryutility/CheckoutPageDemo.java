
package Com.comcast.crm.objectrepositoryutility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageDemo {

	WebDriver driver;
	WebDriverWait wait;

	public CheckoutPageDemo(WebDriver driver) {

		this.driver = driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	// 1. BILLING ADDRESS

	public void enterBillingDetails(String fname, String lname, String email, String country, String city,
			String address, String zip, String phone) {

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_FirstName")));

		firstName.clear();
		firstName.sendKeys(fname);

		WebElement lastName = driver.findElement(By.id("BillingNewAddress_LastName"));

		lastName.clear();
		lastName.sendKeys(lname);

		// Billing email

		WebElement billingEmail = driver.findElement(By.id("BillingNewAddress_Email"));

		billingEmail.clear();
		billingEmail.sendKeys(email);

		// Country

		new Select(driver.findElement(By.id("BillingNewAddress_CountryId"))).selectByVisibleText(country);

		// City

		WebElement cityField = driver.findElement(By.id("BillingNewAddress_City"));

		cityField.clear();
		cityField.sendKeys(city);

		// Address

		WebElement addressField = driver.findElement(By.id("BillingNewAddress_Address1"));

		addressField.clear();
		addressField.sendKeys(address);

		// Zip code

		WebElement zipField = driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));

		zipField.clear();
		zipField.sendKeys(zip);

		// Phone

		WebElement phoneField = driver.findElement(By.id("BillingNewAddress_PhoneNumber"));

		phoneField.clear();
		phoneField.sendKeys(phone);
	}

	// Verify billing email

	public String getBillingEmail() {

		return driver.findElement(By.id("BillingNewAddress_Email")).getAttribute("value");
	}

	// Billing Continue

	public void clickBillingContinue() {

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#billing-buttons-container .new-address-next-step-button"))).click();
	}

	// 2. SHIPPING ADDRESS

	public void completeShippingAddress(String fname, String lname, String email, String country, String city,
			String address, String zip, String phone) {

		By firstNameLocator = By.id("ShippingNewAddress_FirstName");

		// Check whether shipping address form is displayed

		List<WebElement> elements = driver.findElements(firstNameLocator);

		if (!elements.isEmpty() && elements.get(0).isDisplayed()) {

			WebElement firstName = elements.get(0);
			firstName.clear();
			firstName.sendKeys(fname);

			WebElement lastName = driver.findElement(By.id("ShippingNewAddress_LastName"));

			lastName.clear();
			lastName.sendKeys(lname);

			// Shipping email

			WebElement shippingEmail = driver.findElement(By.id("ShippingNewAddress_Email"));

			shippingEmail.clear();
			shippingEmail.sendKeys(email);

			// Country

			new Select(driver.findElement(By.id("ShippingNewAddress_CountryId"))).selectByVisibleText(country);

			// City

			WebElement cityField = driver.findElement(By.id("ShippingNewAddress_City"));

			cityField.clear();
			cityField.sendKeys(city);

			// Address

			WebElement addressField = driver.findElement(By.id("ShippingNewAddress_Address1"));

			addressField.clear();
			addressField.sendKeys(address);

			// Zip

			WebElement zipField = driver.findElement(By.id("ShippingNewAddress_ZipPostalCode"));

			zipField.clear();
			zipField.sendKeys(zip);

			// Phone

			WebElement phoneField = driver.findElement(By.id("ShippingNewAddress_PhoneNumber"));

			phoneField.clear();
			phoneField.sendKeys(phone);

			// Shipping Continue

			wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#shipping-buttons-container .new-address-next-step-button")))
					.click();
		}
	}

	// 3. SHIPPING METHOD

	public void selectShippingOption() {

		By shippingOption = By.cssSelector("input[name='shippingoption']");

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(shippingOption));

		if (!option.isSelected()) {
			option.click();
		}
	}

	public void clickShippingContinue() {

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping-method-buttons-container .button-1")))
				.click();
	}

	// 4. PAYMENT METHOD

	public void selectPaymentMethod() {

		By paymentOption = By.cssSelector("input[name='paymentmethod']");

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(paymentOption));

		if (!option.isSelected()) {
			option.click();
		}
	}

	public void clickPaymentContinue() {

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-method-buttons-container .button-1")))
				.click();
	}

	// 5. PAYMENT INFORMATION

	public void clickPaymentInfoContinue() {

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-info-buttons-container .button-1")))
				.click();
	}

	// 6. CONFIRM ORDER

	public void clickConfirmOrder() {

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("#confirm-order-buttons-container .button-1")))
				.click();
	}

	// 7. ORDER SUCCESS MESSAGE

	public String getOrderSuccessMessage() {

		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".section.order-completed .title")))
				.getText();
	}
}
