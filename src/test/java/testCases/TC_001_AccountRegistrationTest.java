/**
 * 
 */
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

/**
 * 
 */
public class TC_001_AccountRegistrationTest extends BaseClass{
//	ChromeOptions options = new ChromeOptions();
//	options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
	
	
	@Test(groups = {"sanity", "Master"})
	public void AccountRegistrationTest() {
	
		try {
	  HomePage homepage = new HomePage(driver);
		logger.info("TC001_AccountRegistrationTest: Test case started..");

		homepage.clickMyAccount();
		homepage.clickRegister();
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		//used RandomStringutils to generate random string and number
		regpage.SetFirstname(randomString().toUpperCase());
		regpage.SetLastName(randomString());
		regpage.SetEmail(randomString()+"@gmail.com");
		regpage.SetTelephoneNum(randomNumber());
		String password = randomPassword();
		regpage.SetPassword(password);
		regpage.SetConfirmPassword(password);
		regpage.ClickPolicy();
		regpage.ClickContinue();
		
		String congmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(congmsg, "Your Account Has Been Created!");
		
		} catch (Exception e) {
			logger.info("test failed");
			logger.debug("Debug log");
		}
	
    	logger.info("TC001_AccountRegistrationTest: Test case Finished..");
	}
	
	
}
