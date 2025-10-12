package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutility.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {

	@Test(dataProvider = "loginDetails")
	public void login(String username,String password) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp=new LoginPage(driver);
		String url="http://49.249.28.218:8098/";
		lp.loginToApp(username, password, url);
		HomePage hp=new HomePage(driver);
		hp.logout();
		driver.quit();
	}
	
	@DataProvider
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException {
		
    Object[][] objArr=new Object[6][2];
		
	ExcelFileUtility eLib=new ExcelFileUtility();
	int rowCount = eLib.getRowCount("DataProvider");
	for(int i=1;i<=rowCount;i++) {
		
		objArr[i-1][0]=eLib.readDataFromExcelFile("DataProvider",i, 0);
		objArr[i-1][1]= eLib.readDataFromExcelFile("DataProvider",i, 1);
		
	}
	return objArr;
		
		
	}
}
