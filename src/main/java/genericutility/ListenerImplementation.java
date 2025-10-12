package genericutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	ExtentReports report;
	ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report_" + date + ".html");
		spark.config().setDocumentTitle("NINZA CRM Reports");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Edge");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		test = report.createTest(testcaseName);
		System.out.println(testcaseName + " execution started");
		test.log(Status.INFO, testcaseName + " execution started");
		// System.out.println(testcaseName + " execution started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		test.log(Status.PASS, testcaseName + " execution success");
		// System.out.println(testcaseName + " execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		test.log(Status.FAIL, testcaseName + " execution failed");
		// System.out.println(testcaseName + " execution failed");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		test.log(Status.SKIP, testcaseName + " execution skipped");
		// System.out.println(testcaseName + " execution skipped");
	}

}
