package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;


public class ExtentReportManager implements ITestListener{

		public ExtentSparkReporter sparkReport;
		public ExtentReports extentReport;
		public ExtentTest extentTest;
		
		String repName;
		
		@BeforeSuite
		public void onStart(ITestContext context) {
			
		/*	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS");
			Date date= new Date();
			String currentdatetimestamp=dateformat.format(date);
		*/
			String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());
			repName= "Test-Report-" + timeStamp +".html";
			
			sparkReport= new ExtentSparkReporter(".\\reports\\" + repName);
			sparkReport.config().setDocumentTitle("Ecommerce Automation Report");
			sparkReport.config().setReportName("Functional Testing");
			sparkReport.config().setTheme(Theme.STANDARD);
			
			extentReport = new ExtentReports();
			extentReport.attachReporter(sparkReport);
			
			extentReport.setSystemInfo("Application","open cart");
			extentReport.setSystemInfo("Module", "Admin");
			extentReport.setSystemInfo("Sub Module", "Customers");
			extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
			extentReport.setSystemInfo("Environmet", "Production Environment");

			
			String os=context.getCurrentXmlTest().getParameter("OS");
			extentReport.setSystemInfo("Operating System", os);
			
			String browser=context.getCurrentXmlTest().getParameter("browser");
			extentReport.setSystemInfo("Browser Name", browser);

			List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
				extentReport.setSystemInfo("Groups", includedGroups.toString());
			}
			
		}

		/**
		 * Invoked each time a test succeeds.
		 */
		public void onTestSuccess(ITestResult result) {
			extentTest= extentReport.createTest(result.getTestClass().getName());
			extentTest.assignCategory(result.getMethod().getGroups());//display groups in report
			extentTest.log(Status.PASS,result.getName()+ "got successfully executed");
		}

		/**
		 * Invoked each time a test fails.
		 */

		public void onTestFailure(ITestResult result) {
			extentTest= extentReport.createTest(result.getTestClass().getName());
			extentTest.assignCategory(result.getMethod().getGroups());//display groups in report
			
			extentTest.log(Status.FAIL,result.getName()+ "got failed");
		    extentTest.log(Status.INFO, result.getThrowable().getMessage());
		
		    try {
		    	String impPath = new BaseClass().capturescreenshot(result.getName());
		    	extentTest.addScreenCaptureFromPath(impPath);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		   
		}

		/**
		 * Invoked each time a test is skipped.
		 */
		public void onTestSkipped(ITestResult result) {
			
			extentTest= extentReport.createTest(result.getTestClass().getName());
			extentTest.assignCategory(result.getMethod().getGroups());//display groups in report

			extentTest.log(Status.SKIP,result.getName()+ "got skipped");
		    extentTest.log(Status.INFO, result.getThrowable().getMessage());
		}

		@AfterSuite
		public void onFinish(ITestContext context) {
			if(extentReport !=null) {
	        extentReport.flush();
		    }
			
			String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports\\"+repName;
			File report = new File(pathOfExtentReport);
		
			try {
				Desktop.getDesktop().browse(report.toURI());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			//add apache common email dependency and add the code to send report in email
		}

	}

