package base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.DriverFactoryUtils;

public class BaseClass {
	Logger log;
	
	public WebDriver getDriver() {
		return DriverFactoryUtils.getDriver();
	}

	@BeforeMethod
	public void setup() {

		DriverFactoryUtils.initDriver();
		WebDriver driver = getDriver();
		driver.get(ConfigReader.fetchValue("url"));
		driver.manage().window().maximize();
		
		// implicit wait can be set here if needed
		String wait = ConfigReader.fetchValue("timeout");
		log = LogManager.getLogger(BaseClass.class);
		log.info("Setting implicit wait to " + wait + " seconds");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(wait)));

	}

	@AfterMethod
	public void tearDown() {
		log.info("Closing the browser and quitting the driver");
		DriverFactoryUtils.quitDriver();
	}
}
