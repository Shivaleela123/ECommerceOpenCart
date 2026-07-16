package helper;

import org.openqa.selenium.WebDriver;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class AccountRegistration {
	
	public void AccountRegistation(WebDriver driver) {
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			// hard coded value
			regpage.SetFirstname("shivaleela");
			regpage.SetLastName("LICHI");
			regpage.SetEmail("text@gmail.com");
			regpage.SetTelephoneNum("1234566");
			regpage.SetPassword("xyz.12345");
			regpage.SetConfirmPassword("xyz.12345");
			regpage.ClickPolicy();
			regpage.ClickContinue();
			 regpage.getConfirmationMsg();

			
	/*		//used RandomStringutils to generate random string and number
	 * 	regpage.SetFirstname(randomString().toUpperCase());
		regpage.SetLastName(randomString());
		regpage.SetEmail(randomString()+"@gmail.com");
		regpage.SetTelephoneNum(randomNumber());
		String password = randomPassword();
		regpage.SetPassword(password);
		regpage.SetConfirmPassword(password);
		regpage.ClickPolicy();
		regpage.ClickContinue();
	    String congmsg = regpage.getConfirmationMsg();
		*/	
		
	}

}
