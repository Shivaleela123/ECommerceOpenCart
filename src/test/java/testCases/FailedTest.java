package testCases;

import java.time.Duration;
import org.testng.annotations.Test;

import utilities.MyRetry;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FailedTest {
WebDriver driver;

 @Test(retryAnalyzer = MyRetry.class)
public void fail1() {
	
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://www.google.com/");
	driver.manage().window().maximize();
	Assert.assertEquals(driver.getTitle(), "google");
}


 @Test(retryAnalyzer = MyRetry.class)
public void fail2() {
	
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://www.google.com/");
	driver.manage().window().maximize();
	Assert.assertEquals(driver.getTitle(), "gogle");
}

 @Test(retryAnalyzer = MyRetry.class)
public void fail3() {
	
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://www.google.com/");
	driver.manage().window().maximize();
	Assert.assertEquals(driver.getTitle(), "Google");
}


}

