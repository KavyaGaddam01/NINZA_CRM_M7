package practice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import genericutility.ExcelFileUtility;
import genericutility.JavaUtility;
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithExpectedCloseDate {

	public static void main(String[] args) throws InterruptedException, IOException {

		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String URL = pLib.readDataFromPropertyFile("URL");
		String USERNAME = pLib.readDataFromPropertyFile("Username");
		String PASSWORD = pLib.readDataFromPropertyFile("Password");

		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 7, 3);

		// Get Date after 30 days
		JavaUtility jLib = new JavaUtility();
		String expectedCloseDate = jLib.getRequiredDate(100);
		System.out.println(expectedCloseDate);

		// Launch the browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("safari"))
			driver = new SafariDriver();

		driver.manage().window().maximize();
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.implicitWait(driver);
		
		HomePage hp=new HomePage(driver);

		// Login to Ninza_CRM
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD,URL);

		// Create Campaign with Mandatory Fields along with expected close date
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		WebElement targetSizeTF = driver.findElement(By.name("targetSize"));
		targetSizeTF.clear();
		targetSizeTF.sendKeys(TARGET_SIZE);

		driver.findElement(By.name("expectedCloseDate")).sendKeys(jLib.getRequiredDate(30));
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Validation
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		if (toastMsg.getText().contains("test"))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// Logout
		hp.logout();

		// Close the browser
		driver.quit();
	}

}
