package runners;

import org.testng.annotations.DataProvider; // ======================= NEW =======================

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*
 * Class-Entry point for executing Cucumber Feature Files using TestNG.
 * This class tells Cucumber:Which Feature Files to execute.Where Step Definitions are located.
 * Which Hooks to execute.Which reports to generate.
 * Without glue, Cucumber cannot map Feature File steps to Java methods.
 * plugin -pretty-produces readable execution logs in Console.html Generates HTML execution report under:target/cucumber-report.html
 * monochrome-Produces clean console output by removing unnecessary ANSI characters.
 */

@CucumberOptions(

		features = "classpath:features",

		glue = { "stepdefinitions", "hooks" },
		
		tags="@login",

		plugin = {
				"pretty",
				"html:target/cucumber-report.html",

				// ======================= NEW =======================
				// Generates machine-readable execution report.
				// Used by reporting tools like Allure, Jenkins and dashboards.
				"json:target/cucumber.json",

				// ======================= NEW =======================
				// Stores failed scenarios.
				// This file will be used later by FailedRunner
				// to execute only failed scenarios.
				"rerun:target/rerun.txt"
		},

		monochrome = true,

		dryRun = false

)

public class TestRunner extends AbstractTestNGCucumberTests {

	/*
	 * No code is required here.
	 *
	 * AbstractTestNGCucumberTests already provides the implementation required to
	 * execute Cucumber Scenarios using TestNG.
	 */

	// ======================= NEW =======================
	/**
	 * Provides Cucumber scenarios to TestNG.
	 *
	 * Currently parallel execution is disabled.
	 *
	 * In future, simply change:
	 *
	 * parallel = true
	 *
	 * to enable parallel execution without changing
	 * framework architecture.
	 */
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {

		return super.scenarios();

	}

}