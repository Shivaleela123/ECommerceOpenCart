package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//span[normalize-space() = 'My Account']
	@FindBy(xpath = "//a[@title = 'My Account']")
	WebElement lnkMyAccount;
	
	//a[normalize-space() = 'Register']
	@FindBy(xpath = "//li[@class='dropdown open']//a[text() ='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	WebElement lnkLogin;
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}
