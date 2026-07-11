package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.ConfigReader;
import utils.DriverFactoryUtils;
import utils.ScreenshotUtil;

public class TestNGListener implements ITestListener{
	
	ExtentReports extent;
	ExtentTest test;
	
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Automation Test Report");
		reporter.config().setDocumentTitle("Parabank Test Execution");
		
		extent  = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Name", "Rahul");
		extent.setSystemInfo("Environment", ConfigReader.fetchValue("env"));
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String path = "";
		WebDriver driver = DriverFactoryUtils.getDriver();
		extentTest.get().fail(result.getThrowable());
		
		try {
			path = ScreenshotUtil.captureScreenshot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(path);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
