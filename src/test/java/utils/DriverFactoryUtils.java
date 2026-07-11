package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactoryUtils {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initDriver() {

		String browser = ConfigReader.fetchValue("browser");
		boolean headless = Boolean.parseBoolean(ConfigReader.fetchValue("headless"));

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions cOpt = new ChromeOptions();

			if (headless) {
				cOpt.addArguments("--headless=new");
			}
			driver.set(new ChromeDriver(cOpt));
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions eOpt = new EdgeOptions();

			if (headless) {
				eOpt.addArguments("--headless=new");
			}
			driver.set(new EdgeDriver(eOpt));
		} else {
			ChromeOptions cOpt = new ChromeOptions();

			if (headless) {
				cOpt.addArguments("--headless=new");
			}
			driver.set(new ChromeDriver(cOpt));
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
