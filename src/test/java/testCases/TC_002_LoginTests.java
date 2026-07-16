package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTests extends BaseClass {

	@Test(groups = {"Master"})
	public void TC001_LoginTestWithValidData() {
		
		logger.info("TC001_LoginTestWithValidData --- started");
		try {
		
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setEmail(prop.getProperty("email"));
		loginpage.setPassword(prop.getProperty("password"));
		loginpage.clickLogin();
		
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		boolean targetPage = myaccountpage.isMyAccountPageExists();	
		Assert.assertTrue(targetPage, "Target page matches");
		myaccountpage.logout();
		
		LogoutPage logout= new LogoutPage(driver);
		Assert.assertEquals(logout.getLogoutMessage(), "Account Logout"); //Account Logout
		Assert.assertEquals(logout.getLogoutMessageHead(), "You have been logged off your account. It is now safe to leave the computer.");

		} catch (Exception e) {
			Assert.fail();
		}
		
		
		logger.info("TC001_LoginTestWithValidData --- finished");

	}
}
