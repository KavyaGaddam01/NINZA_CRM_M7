package campaigntest;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericutility.BaseClass;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;

@Listeners(genericutility.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass {

	@Test(groups = {"smoke","regression"})
	public void CreateCampaignWithMandatoryFieldsTest() throws InterruptedException, IOException {

		// Reading data from excel file
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);

		// Create Campaign with Mandatory Fields
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
		ccp.getCreateCampaignBtn().click();

		// Validation
		HomePage hp = new HomePage(driver);
		WebElement toastMsg = hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		hp.getCloseToastMsg().click();
		Assert.assertTrue(hp.getToastMsg().getText().contains(CAMPAIGN_NAME));
		System.out.println("From new workspace");

	}

	@Test(groups = "regression")
	public void CreateCampaignWithStatusTest() throws IOException {

		// Reading data from excel file
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 4, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 4, 3);
		String STATUS = eLib.readDataFromExcelFile("Campaign", 4, 4);

		// Create Campaign with Mandatory Fields
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();

		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
		ccp.getCampaignStatusTF().sendKeys(STATUS);
		ccp.getCreateCampaignBtn().click();

		// Validation
		HomePage hp = new HomePage(driver);
		WebElement toastMsg = hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		Assert.assertTrue(toastMsg.getText().contains(CAMPAIGN_NAME));
		hp.getCloseToastMsg().click();
		System.out.println("CreateCampaignWithStatus");

	}

	@Test(groups = "regression")
	public void CreateCampaignWithExpectedCloseDateTest() throws InterruptedException, IOException {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 7, 3);

		// Get Date after 30 days
		String expectedCloseDate = jLib.getRequiredDate(100);
		
		// Create Campaign with Mandatory Fields along with expected close date
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getAddCreateCampaignBtn().click();
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		ccp.getTargetSizeTF().clear();
		ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
		ccp.getExpectedCloseDateTF().sendKeys(expectedCloseDate);
		ccp.getCreateCampaignBtn().click();
		
		// Validation
		HomePage hp = new HomePage(driver);
		WebElement toastMsg = hp.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		Assert.assertTrue(toastMsg.getText().contains(CAMPAIGN_NAME));
		hp.getCloseToastMsg().click();

	}
}
