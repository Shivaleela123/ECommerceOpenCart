package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtPhoneNum;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtCofirmPassword;

	@FindBy(xpath = "//input[@value='0']")
	WebElement rdoSubscribe;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContune;
	
	@FindBy(xpath ="//h1[normalize-space() ='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void SetFirstname(String FirstName) {
		txtFirstName.sendKeys(FirstName);
	}
	
	public void SetLastName(String LastName) {
		txtLastName.sendKeys(LastName);
	}
	
	public void SetEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void SetTelephoneNum(String tel) {
		txtPhoneNum.sendKeys(tel);
	}
	
	public void SetPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void SetConfirmPassword(String password) {
		txtCofirmPassword.sendKeys(password);
	}
	
	
	public void ClickPolicy() {
		chkPolicy.click();
	}
	
	public void ClickContinue() {
		btnContune.click();
	}
	
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
}
