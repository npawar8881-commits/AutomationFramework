package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.BrowserOptionsManager;
import utilities.ConfigReader;

/**
 * ============================================================================
 * Class Name : DriverFactory
 *
 * Purpose: Creates, provides and closes WebDriver instances.
 *
 * Responsibilities: 1. Create browser instance. 2. Provide driver to framework.
 * 3. Close browser after execution. 4. Support parallel execution using
 * ThreadLocal.
 * ============================================================================
 */

public final class DriverFactory {

// Records browser initialization, browser configuration,and browser termination activities
	private static final Logger logger = LogManager.getLogger(DriverFactory.class);

	// ThreadLocal ensures each thread gets its own WebDriver instance.
	private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	// Prevent object creation
	private DriverFactory() {

	}

	// Creates browser instance.Browser name is read from config.properties.
	public static void initializeDriver() {

		String browser = ConfigReader.getBrowser().toLowerCase();

		logger.info("Selected Browser : {}", browser);

		switch (browser) {

		case "chrome":

			driverThread.set(
			        new ChromeDriver(BrowserOptionsManager.getChromeOptions()));
	
			logger.info("Successfully launched {} browser.", browser);

			break;

		case "edge":

			WebDriverManager.edgedriver().setup();

			driverThread.set(new EdgeDriver());

			logger.info("Successfully launched {} browser.", browser);

			break;

		case "firefox":

			WebDriverManager.firefoxdriver().setup();

			driverThread.set(new FirefoxDriver());

			logger.info("Successfully launched {} browser.", browser);

			break;

		default:

			logger.error("Invalid browser configured : {}", browser);

			throw new IllegalArgumentException(
				    "Unsupported browser: " + browser + ". Supported browsers: chrome, edge, firefox.");

		}


	}

	public static WebDriver getDriver() {

		WebDriver driver = driverThread.get();

		if (driver == null) {
			throw new IllegalStateException(
					"WebDriver is not initialized. Call initializeDriver() before accessing the driver.");
		}

		return driver;
	}

	// Closes browser and removes WebDriver from current thread.
	public static void quitDriver() {

		WebDriver driver = driverThread.get();

		if (driver != null) {
		    driver.quit();
		    driverThread.remove();
		    logger.info("Browser closed successfully.");
		
		}

	}

}