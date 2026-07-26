package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverFactory;

/**
 * Purpose: Centralizes all Explicit Wait operations. Responsibilities: 1. Wait
 * until element is visible. 2. Wait until element is clickable. 3. Wait until
 * element becomes invisible. Benefit: WebDriverWait object is created only once
 * per WaitUtility object.
 */

public final class WaitUtility {

	// Logger instance for WaitUtility it will Records all Explicit Wait operations performed by the framework.
	private static final Logger logger = LogManager.getLogger(WaitUtility.class);

	private final WebDriverWait wait;
	
	public WaitUtility() {

	    wait = new WebDriverWait(
	            DriverFactory.getDriver(),
	            Duration.ofSeconds(ConfigReader.getExplicitWait()));

	}
	
	
	
	public WebElement waitForClickability(WebElement element) {
		logger.debug("Waiting until WebElement becomes clickable.");
		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	// Wait until element becomes invisible.use for Overlay screens,Loading,spinners,Progress bars ,Toast notifications
	
	public boolean waitForInvisibility(WebElement element) {
		logger.debug("Waiting until WebElement becomes invisible.");
		return wait.until(ExpectedConditions.invisibilityOf(element));

	}

	// Wait until element becomes visible.
	public WebElement waitForVisibility(WebElement element) {
		
		logger.debug("Waiting until WebElement becomes visible.");
		
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	// Wait until page title contains expected text.
	public boolean waitForTitleContains(String title) {

		logger.debug("Waiting until page title contains : {}", title);

		return wait.until(ExpectedConditions.titleContains(title));

	}

	// Wait until current URL contains expected text.
	public boolean waitForUrlContains(String url) {

		logger.debug("Waiting until URL contains : {}", url);

		return wait.until(ExpectedConditions.urlContains(url));

	}

	// Wait until page is completely loaded.
	public void waitForPageLoad() {

		logger.info("Waiting until page completely loads.");

		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

	}

	// ======================= NEW =======================

	/*Waits until the expected text appears in the element.
	 * Need: Applications often update only the element's text without refreshing the page.
	 * Difference: Waits for the expected text, not just the element's presence or visibility.
	 * Without: Validation may execute before the text is updated, causing false test failures*/
	public boolean waitForText(WebElement element, String expectedText) {

		logger.debug("Waiting for text '{}' to appear.", expectedText);

		return wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));

	}

	// ======================= NEW =======================

	/*Waits until the expected number of browser windows are opened.
	 * Need: Some user actions open a new browser window or tab asynchronously.
	 * Difference: Waits for the expected window count before switching windows.
	 * Used In: Payment Gateway, OAuth Login, PDF Preview, Report Export, Child Windows.
	  Without: Switching to the new window immediately may fail with NoSuchWindowException or incorrect window switching.*/
	  
	 
	public boolean waitForNumberOfWindows(int expectedWindowCount) {

		logger.debug("Waiting until {} browser window(s) are available.", expectedWindowCount);

		return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));

	}

}
