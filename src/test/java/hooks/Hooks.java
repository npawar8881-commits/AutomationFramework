package hooks;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ExtentReportManager;
import utilities.ExtentTestManager;
import utilities.ScreenshotUtility;

//Manages browser lifecycle for every Cucumber Scenario.Cucumber discovers this class by @Before and @After hooks.
// this do= Launch & Close browser before&after each scenario.

public class Hooks {

	// Records Scenario execution lifecycle.
	private static final Logger logger = LogManager.getLogger(Hooks.class);

	// Executes before every Scenario.Creates a new browser instance.
	@Before
	public void setUp(Scenario scenario) {

		logger.info("Thread ID : {}", Thread.currentThread().getId());
		
		DriverFactory.initializeDriver();
		// Initialize Extent Report
				ExtentReportManager.getReport();

				// Create Extent Test for current Scenario
				ExtentTestManager.createTest(scenario.getName());
		
		
	}

	// Executes after every Scenario.
	@After
	public void tearDown(Scenario scenario) throws IOException {

		ExtentTest test = ExtentTestManager.getTest();
		
		if (scenario.isFailed()) {

			logger.error("Scenario Failed : {}", scenario.getName());
			
			

			String screenshotPath = ScreenshotUtility.captureScreenshot(scenario.getName());

			logger.info("Failure screenshot saved at : {}", screenshotPath);

			// Attach screenshot to Cucumber Report
			scenario.attach(ScreenshotUtility.captureScreenshotBytes(), "image/png", "Failure Screenshot");

			logger.info("Failure screenshot attached to Cucumber Report.");
			
			// Update Extent Report
			test.fail("Scenario Failed : " + scenario.getName());

						test.addScreenCaptureFromPath(screenshotPath);
						
						

		} else {

			logger.info("Scenario Passed : {}", scenario.getName());
			// Update Extent Report
			test.pass("Scenario Passed : " + scenario.getName());

		}

		// Remove current thread's ExtentTest
				ExtentTestManager.unload();

				DriverFactory.quitDriver();

	}

	// Executes once after all Scenarios.
		@AfterAll
		public static void tearDownFramework() {

			ExtentReportManager.flushReport();

		}
}
