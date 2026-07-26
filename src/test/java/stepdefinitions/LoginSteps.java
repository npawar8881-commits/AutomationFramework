package stepdefinitions;

import org.testng.Assert;

import constants.ApplicationConstants;
import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.LoginPage;
import testdata.LoginData;
import testdata.LoginTestData;
import utilities.ConfigReader;

public class LoginSteps {

	private final LoginPage loginPage;

	private DashboardPage dashboardPage;

	public LoginSteps() {

		loginPage = new LoginPage();

	}

	@Given("User is on Login page")
	public void userIsOnLoginPage() {

		DriverFactory.getDriver().get(
				ConfigReader.getApplicationUrl());

	}
	

	// ===================== NEW =====================
	// Reads login credentials from Excel using TestCase
	// and performs login.
	@When("User logs in using {string}")
	public void userLogsInUsing(String crednetials) {

		LoginData loginData =
				LoginTestData.getLoginData(crednetials);

		dashboardPage =
				loginPage.login(loginData);

	}

	@Then("User should be logged in successfully")
	public void userShouldBeLoggedInSuccessfully() {

		Assert.assertEquals(
				dashboardPage.getDashboardHeading(),
				ApplicationConstants.DASHBOARD_TITLE,
				"Dashboard heading is incorrect.");

	}
	@Then("Error message should be displayed")
	public void errorMessageShouldBeDisplayed() {
	    Assert.assertEquals(loginPage.getErrorMessage(),ApplicationConstants.INVALID_LOGIN_MESSAGE);
	            
	            
	}
	
	
	
	
	
	
	
}