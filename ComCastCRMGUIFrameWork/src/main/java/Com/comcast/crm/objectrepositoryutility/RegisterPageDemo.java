package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageDemo {
	
	public RegisterPageDemo(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	}
    
	@FindBy(id="gender-female")
	private WebElement femaleRadioButton;
	
	@FindBy(id="FirstName")
	private WebElement firstName;
	
	@FindBy(id="LastName")
	private WebElement lastName;
	
	@FindBy(id="Email")
	private WebElement email;
	
	@FindBy(id="Password")
	private WebElement password;
	
	@FindBy(id="ConfirmPassword")
	private WebElement confirmPassword;
	
	@FindBy(id="register-button")
	private WebElement btnregister;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement successMessage;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	public void registerUser(String fname,String lname,String mail,String pass) {
		femaleRadioButton.click();
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		email.sendKeys(mail);
		password.sendKeys(pass);
		confirmPassword.sendKeys(pass);
		btnregister.click();
	}
	
	public String getSuccessMessage() {
		return successMessage.getText();
	}
	public void clickContinueButton() {
	    continueButton.click();
	}
}
