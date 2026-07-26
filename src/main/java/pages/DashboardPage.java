package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

/**
 * ============================================================================
 * Class Name : DashboardPage
 *
 * Purpose: Represents the Dashboard page displayed after successful login.
 *
 * Responsibilities: 1. Store Dashboard page locators. 2. Provide Dashboard page
 * actions. 3. Verify Dashboard page elements.
 * ============================================================================
 */
public class DashboardPage extends BasePage {
	private static final Logger logger = LogManager.getLogger(DashboardPage.class);
	/**
	 * Dashboard heading displayed after successful login.
	 */
	@FindBy(xpath = "//h6[text()='Dashboard']")
	private WebElement dashboardHeading;

	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement pimMenu;
	
	
	/**
	 * Constructor
	 *
	 * Initializes Dashboard page web elements.
	 */
	public DashboardPage() {

		super();

	}

	/**
	 * Purpose: Verify Dashboard heading is displayed.
	 *
	 * @return true if Dashboard heading is visible.
	 */
	/*
	 * public boolean isDashboardDisplayed() {
	 * logger.info("Verifying Dashboard page is displayed dashboardHeading."); 
	 * return isDisplayed(dashboardHeading);
	 * 
	 * }
	 */

	public String getDashboardHeading() {

		logger.info("Reading Dashboard heading.");

		return getElementText(dashboardHeading);

	}
	
	/**
	 * Opens the Employee List page from the Dashboard.
	 *
	 * Need:
	 * Navigates to the PIM module where Employee List is available.
	 *
	 * Without:
	 * Test cannot proceed to Employee search or other PIM operations.
	 *
	 * @return EmployeeListPage
	 */
	public EmployeeListPage openEmployeeList() {

	    logger.info("Opening Employee List page.");

	    click(pimMenu);

	    return new EmployeeListPage();

	}

}