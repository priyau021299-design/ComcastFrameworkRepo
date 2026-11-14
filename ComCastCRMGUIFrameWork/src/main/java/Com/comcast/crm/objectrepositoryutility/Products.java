package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {
     
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="submit")
	private WebElement ele3;
}
