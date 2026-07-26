package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import driver.DriverFactory;

/**
 * ============================================================================
 * Class Name : WindowUtility
 *
 * Purpose:
 * Provides reusable methods for handling browser windows and tabs.
 *
 * Responsibilities:
 * 1. Switch to browser window by title.
 * 2. Switch to browser window by URL.
 * ============================================================================
 */

public final class WindowUtility {

	// Records all browser window navigation activities.
	private static final Logger logger =
			LogManager.getLogger(WindowUtility.class);

	// Prevent object creation because this class provides only static utility methods.
	private WindowUtility() {

	}

	/**
	 * Switches control to the browser window whose title contains the expected text.
	 *
	 * Need:
	 * Applications may open multiple browser windows during execution.
	 *
	 * Difference:
	 * Identifies the required window by its title instead of relying on browser
	 * window order.
	 *
	 * Used In:
	 * OAuth Login, Payment Gateway, PDF Preview, Report Export, Child Windows.
	 *
	 * Without:
	 * Selenium may switch to the wrong window, causing subsequent actions to fail.
	 */
	public static void switchToWindowByTitle(String expectedTitle) {

		logger.info("Switching to browser window with title containing : {}", expectedTitle);

		WebDriver driver = DriverFactory.getDriver();

		for (String windowHandle : driver.getWindowHandles()) {

			driver.switchTo().window(windowHandle);

			if (driver.getTitle().contains(expectedTitle)) {

				logger.info("Successfully switched to browser window : {}", driver.getTitle());

				return;

			}

		}

		logger.error("No browser window found with title containing : {}", expectedTitle);

		throw new NoSuchWindowException(
				"No browser window found with title containing : " + expectedTitle);

	}

	/**
	 * Switches control to the browser window whose URL contains the expected text.
	 *
	 * Need:
	 * Some applications dynamically change browser titles, making title-based
	 * switching unreliable.
	 *
	 * Difference:
	 * Identifies the required window using its URL, which usually remains more
	 * stable than the browser title.
	 *
	 * Used In:
	 * OAuth Login, SSO Authentication, Payment Gateway, External Applications.
	 *
	 * Without:
	 * Selenium may switch to an incorrect window, causing subsequent actions to fail.
	 */
	public static void switchToWindowByPartialUrl(String expectedUrl) {

		logger.info("Switching to browser window with URL containing : {}", expectedUrl);

		WebDriver driver = DriverFactory.getDriver();

		for (String windowHandle : driver.getWindowHandles()) {

			driver.switchTo().window(windowHandle);

			if (driver.getCurrentUrl().contains(expectedUrl)) {

				logger.info("Successfully switched to browser window : {}", driver.getCurrentUrl());

				return;

			}

		}

		logger.error("No browser window found with URL containing : {}", expectedUrl);

		throw new NoSuchWindowException(
				"No browser window found with URL containing : " + expectedUrl);

	}

}