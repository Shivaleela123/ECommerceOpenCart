package testCases;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.TextDataReader;

public class TC_004_TextDataTest {

    String filePath = "src/test/resources/testdata.txt";
    WebDriver driver;
   
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "textDataEngine")
    public void LoginTest(String username, String password) {
        driver.get("https://saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        // Add assertions here
    }
    
    @Test(dataProvider = "textDataEngine")
    public void SearchTest(String item, String category) {
        driver.get("https://example.com");
        driver.findElement(By.id("search-box")).sendKeys(item);
        driver.findElement(By.id("category-dropdown")).sendKeys(category);
        driver.findElement(By.id("search-button")).click();
        // Add assertions here
    }
    
    // Single DataProvider route managing all text file data loops
    @DataProvider(name = "textDataEngine")
    public Object[][] provideData(Method method) {
        // Automatically uses the Test Case method name ("LoginTest" or "SearchTest") to filter rows
        return TextDataReader.getTestData(filePath, method.getName());
    }
    
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
}
