package practice.test;

import org.testng.annotations.Test;

import Com.comcast.crm.basetest.BaseClass;
import Com.comcast.crm.objectrepositoryutility.loginPage;

/**
 * test class for contact module
 * @author Sathishkumar
 * 
 */
public class SearchContactTest extends BaseClass{
  /*
   * Scenario : login()==> navigateContact==>createcontact()==verify	
   */
  @Test
  public void searchcontactTest() {
	  loginPage lp = new loginPage(driver);
	  lp.loginToapp("url", "username", "pas");
	  
  }
}
