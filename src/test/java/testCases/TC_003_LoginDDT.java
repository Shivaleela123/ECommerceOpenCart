package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "DPloginData", dataProviderClass = DataProviders.class)
	public void verifyLoginDDT(String email, String pwd, String exp) {
		logger.info("TC_003_LoginDDT ---- Exection started");

		try {
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickLogin();

			LoginPage loginpage = new LoginPage(driver);
			loginpage.setEmail(email);
			loginpage.setPassword(pwd);
			loginpage.clickLogin();

			MyAccountPage myaccount = new MyAccountPage(driver);

			/*
			 * Data is valid - login success - test pass - logout 
			 * 				   login failed - test fail
			 * 
			 * Data is invalid - login success -test fail - logout 
			 * 				login failed - test pass
			 */
			boolean targetPage = myaccount.isMyAccountPageExists();
			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					myaccount.logout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} else if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					myaccount.logout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("TC_003_LoginDDT ---- Exection completed");

	}
}
