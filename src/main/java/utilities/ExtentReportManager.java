package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FrameworkConstants;

/**
 * ============================================================================
 * Class Name : ExtentReportManager
 *
 * Purpose: Creates and manages the Extent Report instance used across the
 * framework.
 *
 * Responsibilities: 1. Configure Extent HTML report. 2. Provide a single
 * ExtentReports instance. 3. Add execution environment details. 4. Flush report
 * after execution.
 * ============================================================================
 */
public final class ExtentReportManager {

	// Logger instance
	private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);

	// Single ExtentReports instance
	private static ExtentReports extentReports;

	// Prevent object creation
	private ExtentReportManager() {

	}

	/**
	 * Returns configured ExtentReports instance.
	 */
	public static ExtentReports getReport() {

		if (extentReports == null) {

			logger.info("Initializing Extent Report.");

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.EXTENT_REPORT_FILE);

			sparkReporter.config().setDocumentTitle("Banking Automation Report");

			sparkReporter.config().setReportName("Automation Execution Report");

			
			extentReports = new ExtentReports();

			extentReports.attachReporter(sparkReporter);

			
			extentReports.setSystemInfo("Framework", "Selenium + Java + TestNG + Cucumber");

			extentReports.setSystemInfo("Tester", "Nilesh Patil");

			extentReports.setSystemInfo("Environment", ConfigReader.getEnvironment());

			extentReports.setSystemInfo("Browser", ConfigReader.getBrowser());

			extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

		}

		return extentReports;
	}

	//Writes execution details into the HTML report.
	public static void flushReport() {

		if (extentReports != null) {

			logger.info("Flushing Extent Report.");

			extentReports.flush();

		}

	}

}