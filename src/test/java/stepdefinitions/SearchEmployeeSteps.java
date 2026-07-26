package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.EmployeeListPage;


public class SearchEmployeeSteps {
	

	private DashboardPage dashboardPage;
	private EmployeeListPage employeeListPage;
	
	
	
	@When("User navigates to the Employee List page")
	public void userNavigatesToTheEmployeeListPage() {

		dashboardPage = new DashboardPage();

		employeeListPage = dashboardPage.openEmployeeList();

	}

	@When("User searches employee {string}")
	public void userSearchesEmployee(String employeeName) {

		employeeListPage.searchEmployee(employeeName);

	}

	@Then("Employee should be displayed in the search result")
	public void employeeShouldBeDisplayedInTheSearchResult() {

		Assert.assertTrue(employeeListPage.isEmployeePresent(),"Employee was not found.");

	}

}