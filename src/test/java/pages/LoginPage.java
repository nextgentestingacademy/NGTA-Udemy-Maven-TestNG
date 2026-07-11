package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private final Logger log = LogManager.getLogger(getClass());
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	WebElement edtUsername;
	
	@FindBy(name = "password")
	WebElement edtPassword;
	
	@FindBy(css = "[value='Log In']")
	WebElement btnLogin;
	
	public void enterUsername(String username) {
		log.info("Entering username: " + username);
		edtUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		log.info("Entering password: " + password);
		edtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		log.info("Clicking on the login button");
		btnLogin.click();
	}
}