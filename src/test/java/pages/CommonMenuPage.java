package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonMenuPage {
	private final Logger log = LogManager.getLogger(getClass());
	WebDriver driver;
	
	public CommonMenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Log Out")
	WebElement lnkLogout;
	
	public void clickLogout() {
		log.info("Clicking on the logout link");
		lnkLogout.click();
	}
	
	public boolean isLogoutLinkEnabled() {
		log.info("Checking if the logout link is enabled");
		return lnkLogout.isEnabled();
	}
}
