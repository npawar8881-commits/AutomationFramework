package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;
import testdata.LoginData;

/**
 * Design Principle: Encapsulation Step Definitions should never interact
 * directly with WebElements. They should only call action methods exposed by
 * this page.
 */

public class LoginPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement pass;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
	private WebElement loginErrorMessage;

	public LoginPage() {

		super();

	}

	// NEW:
	// Performs complete login using LoginData.
	// Keeps Step Definition independent of page implementation.
	public DashboardPage login(LoginData loginData) {

		logger.info("Logging into application.");

		type(username, loginData.getUsername());

		type(pass, loginData.getPassword());

		click(loginButton);

		return new DashboardPage();

	}

	public String getErrorMessage() {
		logger.info("Getting login error message.");
		return getElementText(loginErrorMessage);

	}

}