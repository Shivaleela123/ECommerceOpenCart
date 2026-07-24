package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties prop;


	@BeforeClass(groups = {"sanity","Regression", "Master"})
	@Parameters({"OS", "browser"})
	void setup(String os, String br) throws IOException {
		System.out.println("before each test");
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);
		
		logger = LogManager.getLogger(this.getClass());
	
		//execute scripts on remote- selenium grid
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
//			String huburl = "http://localhost:4444";
			
			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setPlatform(Platform.WIN10);
//			cap.setBrowserName("chrome");
			 
			//setup os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}
			 
			//setup browser
			switch (br.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
				
			default: System.out.println("No matching browser"); return;
			}
			
			driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), cap);	
		}
		
		// to run our scripts on local machine	

		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{		
		switch (br.toLowerCase()) {
		case "chrome": 	driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser"); return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"sanity","Regression", "Master"})
	void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		 String randomString = RandomStringUtils.secure().nextAlphabetic(6);
	     return randomString;
	}
	
	public String randomNumber() {
		 String randomNumber = RandomStringUtils.secure().nextNumeric(10);
	     return randomNumber;
	}
	
	public String randomPassword() {
		 String randomString = RandomStringUtils.secure().nextAlphabetic(6);
		 String randomNumber = RandomStringUtils.secure().nextNumeric(10);
		 return randomString+"."+randomNumber;
	}
	
	public String capturescreenshot(String tname) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takeScreenShot = (TakesScreenshot) driver;		
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String tagetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp +".png";
		File targetFile = new File(tagetFilePath);
		sourceFile.renameTo(targetFile);
		return tagetFilePath;
	}
}
