package api.utillities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		repName = "Test-Report-"+timestamp+".html";
		
		String reportPath = System.getProperty("user.dir") + "/reports/" + repName;
        new File(System.getProperty("user.dir") + "/reports").mkdirs();
		sparkReporter = new ExtentSparkReporter(reportPath);
		
		sparkReporter.config().setDocumentTitle("ResrAssuredAutomationFramework");
		sparkReporter.config().setReportName("Pet Store User API");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("operating system", System.getProperty("os.name"));
		extent.setSystemInfo("user", "pankaj");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user Name", System.getProperty("user.name"));	
		
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Passed");
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
		
		
	}
	
	 public void onTestSkipped(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	        test.skip("Test skipped: " + result.getThrowable());
	    }
	 
	 public void onFinish(ITestContext testContext) {
	        extent.flush();  // Write the report to the file
	    }
	 
	 public void onTestStart(ITestResult result) {
	        // This method is called when a test starts
	        // We can use it to initialize custom logs or actions
	        test = extent.createTest(result.getMethod().getMethodName());
	    }
	

}
