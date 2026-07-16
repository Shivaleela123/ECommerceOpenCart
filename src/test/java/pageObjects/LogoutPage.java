package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{

	public LogoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath= "//div[@id='content']//h1")
	WebElement msgLogoutHead;
	
	@FindBy(xpath= "//div[@id='content']//p")
	WebElement msgLogoutPar;
	
	
	public String getLogoutMessage() {
		try {
			return( msgLogoutHead.getText());
		} catch (Exception e) {
			return e.getMessage();

		}
		
	}
	
	public String getLogoutMessageHead() {
		try {
			return( msgLogoutPar.getText());
		} catch (Exception e) {
			return e.getMessage();

		}
		
	}
	
}
