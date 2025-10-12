package genericutility;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectrepository.HomePage;
import objectrepository.LoginPage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {

	public WebDriver driver = null;
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public JavaUtility jLib = new JavaUtility();
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "smoke", "regression" })
	public void beforeSuite() {
		System.out.println("Connect to the database");
	}

	@AfterSuite(groups = { "smoke", "regression" })
	public void afterSuite() {
		System.out.println("Disconnect from database");
	}

	@BeforeTest(groups = { "smoke", "regression" })
	public void beforeTest() {
		System.out.println("Pre conditions for parallel executions");
	}

	@AfterTest(groups = { "smoke", "regression" })
	public void afterTest() {
		System.out.println("Post conditions for parallel executions");
	}

//	@Parameters("Browser")
	@BeforeClass(groups = { "smoke", "regression" })
	public void beforeClass() throws IOException {
//		public void beforeClass(String BROWSER) throws IOException {
		System.out.println("Launch the browser");
		// Launch the browser
//		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String BROWSER=System.getProperty("Browser");
		
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		if (BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		
		sdriver=driver;

		driver.manage().window().maximize();
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.implicitWait(driver);
	}

	@AfterClass(groups = { "smoke", "regression" })
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}

	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeMethod() throws IOException {
		System.out.println("Login");
		// Login to Ninza_CRM
//		String URL = pLib.readDataFromPropertyFile("URL");
//		String USERNAME = pLib.readDataFromPropertyFile("Username");
//		String PASSWORD = pLib.readDataFromPropertyFile("Password");
		
		String URL = System.getProperty("URL");
		String USERNAME = System.getProperty("Username");
		String PASSWORD = System.getProperty("Password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD, URL);
	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void afterMethod() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

}
