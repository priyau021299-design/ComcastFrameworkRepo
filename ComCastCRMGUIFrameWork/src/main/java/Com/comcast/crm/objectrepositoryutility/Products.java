package Com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {
     
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
<<<<<<< HEAD
	@FindBy(name="submit")
	private WebElement ele3;
=======
	@FindBy(name="search_text")
	private WebElement ele2;
>>>>>>> branch 'master' of https://github.com/priyau021299-design/ComcastFrameworkRepo.git
}
