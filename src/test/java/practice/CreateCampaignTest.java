package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateCampaignTest {

	
	@Test(dependsOnMethods = {"register","openApplication"})
	public void login() {
		System.out.println("login");
	}
	
	@Test(dependsOnMethods = "openApplication")
	public void register() {
		
		System.out.println("register");
	}
	
	@Test
	public void openApplication() {
		System.out.println("openApplication");
	}
	
	
	
}
//@Test(invocationCount = 5,threadPoolSize = 2,enabled = false)
//public void createCampaignWithMandatoryFields() {
//	WebDriver driver=new ChromeDriver();
//
//	System.out.println("createCampaignWithMandatoryFields");
//}
//
//@Test(priority = -2,invocationCount = 1)
//public void createCampaignWithStatus() {
//	System.out.println("createCampaignWithStatus");
//
//}
//
//@Test(priority =-2,invocationCount = 1)
//public void createCampaignWithExpectedCloseDate() {
//	System.out.println("createCampaignWithExpectedCloseDate");
//
//}