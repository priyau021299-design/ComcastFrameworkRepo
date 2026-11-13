package practice.pom.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class xpath {
   public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.makemytrip.com/flight/search?itinerary=DEL-BOM-16/10/2026&tripType=O&paxType=A-1_C-0_I-0&intl=false&cabinClass=E&lang=eng&cmp=SEM|D|DF|G|Brand|Brand-BrandVariants_DT|B_M_Makemytrip_Variants|Brand-Terms_Exact");
	WebElement price = driver.findElement(By.xpath("//div[text()='Akasa Air']/ancestor::div[@class='flightCard__mainRow']/descendant::span[@class='fareBlock__fareValue']"));
	System.out.println(price);
	
}
}
