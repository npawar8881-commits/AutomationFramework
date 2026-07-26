package base;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import utilities.WaitUtility;

/* * Parent class for all Page Objects.
 * 1. Initialize PageFactory.
 * 2. Provide common page actions.
 * 3. Avoid duplicate Selenium code across pages.*/

public class BasePage {

	protected final WaitUtility wait;

	// Executes whenever child page object is created. Responsibilities: 1. Get
	// WebDriver instance. 2. Initialize PageFactory.
	protected BasePage() {

		wait = new WaitUtility();
		PageFactory.initElements(DriverFactory.getDriver(), this);

	}
	protected String getPageTitle() {

	    return DriverFactory.getDriver().getTitle();

	}
	
	protected String getCurrentUrl() {

	    return DriverFactory.getDriver().getCurrentUrl();

	}
	// Click on element.
	protected void click(WebElement element) {

		wait.waitForClickability(element).click();

	}

	// Enter text into element.
	protected void type(WebElement element, String text) {

	    WebElement webElement = wait.waitForVisibility(element);

	    webElement.clear();

	    webElement.sendKeys(text);

	}
	// Returns true if element is displayed.
	protected boolean isDisplayed(WebElement element) {

		return wait.waitForVisibility(element).isDisplayed();

	}

	protected String getElementText(WebElement element) {

		return wait.waitForVisibility(element).getText();

	}
	
	

}
