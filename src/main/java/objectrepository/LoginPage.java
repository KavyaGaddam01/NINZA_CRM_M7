package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username") private WebElement usernameTF;
	
	@FindBy(id = "inputPassword") private WebElement passwordTF;
	
	@FindBy(xpath = "//button[text()='Sign In']") private WebElement loginBtn;

	public WebElement getUsernameTF() {
		return usernameTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void loginToApp(String un,String password,String url) {
		driver.get(url);
		usernameTF.sendKeys(un);
		passwordTF.sendKeys(password);
		loginBtn.click();
	}
}
