package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

/**
 * ============================================================================
 * Class Name : ExtentTestManager
 *
 * Purpose:
 * Manages ExtentTest instances for each Cucumber Scenario.
 *
 * Responsibilities:
 * 1. Create one ExtentTest per Scenario.
 * 2. Store ExtentTest using ThreadLocal for parallel execution.
 * 3. Provide current thread's ExtentTest instance.
 * 4. Remove ThreadLocal object after Scenario execution.
 * ============================================================================
 */
public final class ExtentTestManager {

	// Logger instance
	private static final Logger logger = LogManager.getLogger(ExtentTestManager.class);

	// Stores one ExtentTest per execution thread
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	// Prevent object creation
	private ExtentTestManager() {

	}

	/**
	 * Creates a new ExtentTest for the current Scenario.
	 */
	public static void createTest(String scenarioName) {

		logger.info("Creating Extent Test for Scenario : {}", scenarioName);

		ExtentTest test = ExtentReportManager.getReport().createTest(scenarioName);

		extentTest.set(test);

	}

	/**
	 * Returns current thread's ExtentTest.
	 */
	public static ExtentTest getTest() {

		return extentTest.get();

	}

	/**
	 * Removes ExtentTest from current thread after Scenario execution.
	 */
	public static void unload() {

		logger.info("Removing Extent Test from current thread.");

		extentTest.remove();

	}

}