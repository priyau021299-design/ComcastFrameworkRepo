package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPageDemo {
	
    public loginPageDemo(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
   @FindBy(id="Email")
   private WebElement email;
   
   @FindBy(id="Password")
   private WebElement password;
   
   @FindBy(id="RememberMe")
   private WebElement rememberMeCheckbox;
   
   @FindBy(xpath="//a[text()='Forgot password?']")
   private WebElement forgotPasswordLink;
   
   @FindBy(xpath="//input[@class='button-1 login-button']")
   private WebElement loginBtn;
   
   @FindBy(xpath="//input[@value='Register']")
   private WebElement registerBtn;
   
   public void loginToApplication(String mail, String pass) {

	   email.sendKeys(mail);
	   password.sendKeys(pass);
//	   loginBtn.click();
   }
   public void clickLoginbtn() {
	   loginBtn.click();
   }
   public void clickRememberMe() {
       rememberMeCheckbox.click();
   }

   public void clickForgotPassword() {
       forgotPasswordLink.click();
   }

   public void clickRegisterButton() {
	   registerBtn.click();
   }
}
