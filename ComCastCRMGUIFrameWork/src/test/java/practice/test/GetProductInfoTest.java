package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
   @Test(dataProvider = "getData")
   public void getProductInfoTest(String brandName,String productName) {
	   WebDriver driver = new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	   driver.get("https://www.amazon.in/");
	   
	   //Search product
	   driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
	   
	   //capture product info
	   String x = "//span[text()='"+productName+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
	   String price = driver.findElement(By.xpath(x)).getText();
	   System.out.println(price);
	   driver.quit();
	   
   }
   
   @DataProvider
   public Object[][] getData() throws Throwable{
	   ExcelUtility eLib = new ExcelUtility();
	   int rowCount = eLib.getRowCount("product");
	   
	   Object[][] objArr = new Object[rowCount][2];
	   
	   for(int i=0;i<rowCount;i++) {
		  objArr[i][0] = eLib.getDataFromExcel("product", i+1, 0);
		  objArr[i][1] = eLib.getDataFromExcel("product", i+1, 1);
	   }
//	   objArr[0][0] = "iphone";
//	   objArr[0][1] = "iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Teal";
//	 
//	   
//	   objArr[1][0] = "iphone";
//	   objArr[1][1] = "iPhone 17 Pro 256 GB: 15.93 cm (6.3″) Display with Promotion up to 120Hz, A19 Pro Chip, Breakthrough Battery Life, Pro Fusion Camera System with Center Stage Front Camera; Cosmic Orange";
//	  
//	   
//	   objArr[2][0] = "iphone";
//	   objArr[2][1] = "iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; White";
	 
	   
	   return objArr;
   }
}
