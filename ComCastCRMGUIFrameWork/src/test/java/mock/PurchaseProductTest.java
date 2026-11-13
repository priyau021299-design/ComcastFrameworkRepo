

package mock;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.comcast.crm.generic.fileutility.ExcelUtility;
import Com.comcast.crm.generic.fileutility.FileUtility;
import Com.comcast.crm.objectrepositoryutility.CheckoutPageDemo;
import Com.comcast.crm.objectrepositoryutility.HomePageDemo;
import Com.comcast.crm.objectrepositoryutility.RegisterPageDemo;
import Com.comcast.crm.objectrepositoryutility.shoppingCartPageDemo;

public class PurchaseProductTest {

    WebDriver driver;
    WebDriverWait wait;

    FileUtility fLib = new FileUtility();
    ExcelUtility eLib = new ExcelUtility();

    @BeforeMethod
    public void configBM() throws Throwable {

        String URL = fLib.getDataFromPropertiesFile("url");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @Test
    public void registerUserTest() throws Throwable {

        // STEP 1: Read data from Excel

        String fname = eLib.getDataFromExcel("RegisterData", 1, 0);
        String lname = eLib.getDataFromExcel("RegisterData", 1, 1);
        String pass = eLib.getDataFromExcel("RegisterData", 1, 3);
        String product = eLib.getDataFromExcel("RegisterData", 1, 4);
        String country = eLib.getDataFromExcel("RegisterData", 1, 5);
        String city = eLib.getDataFromExcel("RegisterData", 1, 6);
        String address = eLib.getDataFromExcel("RegisterData", 1, 7);
        String zip = eLib.getDataFromExcel("RegisterData", 1, 8);
        String phone = eLib.getDataFromExcel("RegisterData", 1, 9);

        // STEP 2: Generate unique email

        Random random = new Random();

        int randomNumber = 100000 + random.nextInt(900000);

        String randomEmail = "test" + randomNumber
                + System.currentTimeMillis() + "@gmail.com";

        System.out.println("Generated Email: " + randomEmail);

        // STEP 3: Register user

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Register"))).click();

        RegisterPageDemo rp = new RegisterPageDemo(driver);

        rp.registerUser(fname, lname, randomEmail, pass);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='result']")));

        String registrationMessage = rp.getSuccessMessage();

        Assert.assertTrue(
                registrationMessage.contains("Your registration completed"),
                "Registration failed");

        System.out.println("Registration successful");

        rp.clickContinueButton();

        // STEP 4: Search product

        HomePageDemo hp = new HomePageDemo(driver);

        hp.searchProduct(product);

        // STEP 5: Add product to cart

        WebElement addCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[contains(@class,'product-box-add-to-cart-button')])[1]")));

        addCart.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("cart-qty"), "(1)"));

        System.out.println("Product added to cart");

        // STEP 6: Shopping cart

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.ico-cart"))).click();

        shoppingCartPageDemo cp = new shoppingCartPageDemo(driver);

        cp.clickTermsAndCheckout();

        // STEP 7: Checkout

        CheckoutPageDemo checkout = new CheckoutPageDemo(driver);

        // STEP 8 :Billing Address

        checkout.enterBillingDetails(
                fname, lname, randomEmail, country,
                city, address, zip, phone);

        // STEP 9 : Verify billing email

        String actualEmail = checkout.getBillingEmail();

        System.out.println("Expected Email: " + randomEmail);
        System.out.println("Actual Email: " + actualEmail);

        Assert.assertEquals(
                actualEmail,
                randomEmail,
                "Billing email is incorrect");

        checkout.clickBillingContinue();

        System.out.println("Billing address completed");

        // STEP 10 : Shipping Address

        checkout.completeShippingAddress(
                fname, lname, randomEmail, country,
                city, address, zip, phone);

        System.out.println("Shipping address completed");

        // STEP 11 : Shipping method

        checkout.selectShippingOption();

        checkout.clickShippingContinue();

        System.out.println("Shipping method completed");

        // STEP 12 : Payment method

        checkout.selectPaymentMethod();

        checkout.clickPaymentContinue();

        System.out.println("Payment method completed");

        // STEP 13 : PAyment Information

        checkout.clickPaymentInfoContinue();

        System.out.println("Payment information completed");

        // STEP 14 : confirm order

        checkout.clickConfirmOrder();

        System.out.println("Confirm order clicked");

        // STEP 15 : Verify order

        String successMessage = checkout.getOrderSuccessMessage();

        Assert.assertTrue(
                successMessage.toLowerCase().contains("successfully"),
                "Order was not completed");

        System.out.println("Order placed successfully");

        System.out.println(successMessage);

        System.out.println("Used Email ID: " + randomEmail);
    }

    @AfterMethod(alwaysRun = true)
    public void configAM() {

        if (driver != null) {
            driver.quit();
        }
    }
}
