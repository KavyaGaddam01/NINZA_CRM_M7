package contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutility.BaseClass;
import objectrepository.CampaignsPage;
import objectrepository.ContactsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.SelectCampaignPage;

public class CreateContactTest extends BaseClass {

	@Test(groups = { "smoke","regression"})
	public void createContactWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException {
		// Reading data from excel file
				String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contact", 1, 2);
				String TARGET_SIZE = eLib.readDataFromExcelFile("Contact", 1, 3);
				String ORGANIZATION_NAME = eLib.readDataFromExcelFile("Contact", 1, 4);
				String TITLE = eLib.readDataFromExcelFile("Contact", 1, 5);
				String CONTACT_NAME = eLib.readDataFromExcelFile("Contact", 1, 6);

				// Create Campaign with Mandatory Fields
				CampaignsPage cp = new CampaignsPage(driver);
				cp.getAddCreateCampaignBtn().click();
	
				CreateCampaignPage ccp = new CreateCampaignPage(driver);
				ccp.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
				ccp.getTargetSizeTF().clear();
				ccp.getTargetSizeTF().sendKeys(TARGET_SIZE);
				ccp.getCreateCampaignBtn().click();

				HomePage hp = new HomePage(driver);
				WebElement toastMsg = hp.getToastMsg();
				wLib.waitUntilElementToBeVisible(driver, toastMsg);
				hp.getCloseToastMsg().click();
				
				//CreateContact
				hp.getContactsLink().click();
				ContactsPage contactPage=new ContactsPage(driver);
				contactPage.getAddCreateContactBtn().click();
				CreateContactPage createContactPage=new CreateContactPage(driver);
				createContactPage.getOrganizationNameTF().sendKeys(ORGANIZATION_NAME);
				createContactPage.getTitleTF().sendKeys(TITLE);
				createContactPage.getContactNameTF().sendKeys(CONTACT_NAME);
				createContactPage.getMobileTF().sendKeys("8862736255");
				String parentId = driver.getWindowHandle();
				createContactPage.getPlusBtn().click();
				
				//switch the driver control to select campaign page
				wLib.switchToWindowOnTitle(driver, "Select Campaign");
				SelectCampaignPage scp=new SelectCampaignPage(driver);
				wLib.select(scp.getCampaignDD(), "campaignName");
				scp.getSearchBar().sendKeys(CAMPAIGN_NAME);
				wLib.waitUntilElementToBeVisible(driver, scp.getSelectBtn());
				scp.getSelectBtn().click();
				
				//switch back driver control to parent page
				wLib.switchBackToParentId(driver,parentId);
				createContactPage.getCreateContactSubmitBtn().click();
				
				//validation
				WebElement toastMsg1 = hp.getToastMsg();
				wLib.waitUntilElementToBeVisible(driver, toastMsg1);
				Assert.assertTrue(toastMsg1.getText().contains(CONTACT_NAME));
				hp.getCloseToastMsg().click();
				
					
	}
}
