package tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import pages.CommonMenuPage;
import pages.LoginPage;

public class ParabankTest extends BaseClass {

	private final Logger log = LogManager.getLogger(getClass());
	
	@Test(enabled = false, dataProvider = "loginData", dataProviderClass = utils.DataProviderUtil.class)
	public void testLogin(String uid, String pwd) {
		log.info("Starting execution of testLogin");
		LoginPage login = new LoginPage(getDriver());
		CommonMenuPage commonMenu = new CommonMenuPage(getDriver());

		login.enterUsername(uid);
		login.enterPassword(pwd);
		login.clickLogin();

		SoftAssert soft = new SoftAssert();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Account Services']")));

		String expectedTitle = "ParaBank | Accounts Overview";
		String actualTitle = getDriver().getTitle();
		soft.assertEquals(actualTitle, expectedTitle, "Page title does not match after login.");

		boolean boolLogOut = commonMenu.isLogoutLinkEnabled();
		soft.assertTrue(boolLogOut, "Log Out link is not enabled after login.");
		soft.assertAll();
		log.info("Completed the execution of testLogin");
	}

	@Test(dataProvider = "invalidLoginData", dataProviderClass = utils.DataProviderUtil.class)
	public void testInvalidLogin(String uid, String pwd) {
		log.info("Started the execution of testInvalidLogin");
		LoginPage login = new LoginPage(getDriver());

		login.enterUsername(uid);
		login.enterPassword(pwd);
		login.clickLogin();

		SoftAssert soft = new SoftAssert();

		String expectedTitle = "ParaBank | Error1";
		String actualTitle = getDriver().getTitle();
		soft.assertEquals(actualTitle, expectedTitle, "Page title does not match after invalid login.");

		soft.assertAll();
		log.info("Completed the execution of testInvalidLogin");
	}
}