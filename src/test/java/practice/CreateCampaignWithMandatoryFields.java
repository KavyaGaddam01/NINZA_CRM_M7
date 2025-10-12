package practice;

import java.io.IOException;
import java.time.Duration;
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
import genericutility.PropertyFileUtility;
import genericutility.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException, IOException {

		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String URL = pLib.readDataFromPropertyFile("URL");
		String USERNAME = pLib.readDataFromPropertyFile("Username");
		String PASSWORD = pLib.readDataFromPropertyFile("Password");

		// Reading data from excel file
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 1, 2);
		// String CAMPAIGN_NAME =
		// wb.getSheet("Campaign").getRow(1).getCell(2).getStringCellValue();
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);
		// String TARGET_SIZE =
		// wb.getSheet("Campaign").getRow(1).getCell(3).getStringCellValue();

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

		HomePage hp = new HomePage(driver);

		// Login to Ninza_CRM
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD,URL);

		// Create Campaign with Mandatory Fields
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();
		
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
		ccp.getCreateCampaignBtn().click();
//		WebElement targetSizeTF = driver.findElement(By.name("targetSize"));
//		targetSizeTF.clear();
//		targetSizeTF.sendKeys(TARGET_SIZE);
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Validation
		wLib.waitUntilElementToBeVisible(driver, hp.getToastMsg());
		if (hp.getToastMsg().getText().contains(CAMPAIGN_NAME))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		hp.getCloseToastMsg().click();

		// Logout
		hp.logout();

		// Close the browser
		driver.quit();
	}

}
