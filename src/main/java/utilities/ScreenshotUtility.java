package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import constants.FrameworkConstants;
import driver.DriverFactory;

/**
 * ============================================================================
 * Class Name : ScreenshotUtility
 *
 * Purpose: Provides utility methods for capturing browser screenshots.
 *
 * Responsibilities: 1. Capture the current browser screenshot. 2. Create the
 * screenshots directory if it does not exist. 3. Generate a unique screenshot
 * filename. 4. Store the screenshot on the local file system. 5. Return the
 * screenshot path for reporting frameworks.
 * ============================================================================
 */

public final class ScreenshotUtility {

	private static final Logger logger = LogManager.getLogger(ScreenshotUtility.class);

	// Private Constructor-Prevents object creation because this class provides only
	// static utility methods.

	private ScreenshotUtility() {

	}

	public static String captureScreenshot(String screenshotName) throws IOException {

		WebDriver driver = DriverFactory.getDriver();

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File screenshotFolder =
				new File(FrameworkConstants.SCREENSHOT_DIRECTORY);

		if (!screenshotFolder.exists() && !screenshotFolder.mkdirs()) {
			throw new IOException("Failed to create screenshots directory.");
		}

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

		String fileName = screenshotName +"_T"
		        + Thread.currentThread().getId()+"_" + timestamp + ".png";

		File destinationFile = new File(screenshotFolder, fileName);

		Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		logger.info("Screenshot saved successfully : {}", destinationFile.getAbsolutePath());

		// Return the absolute screenshot path.frameworks such as Extent Reports

		return destinationFile.getAbsolutePath();

	}

	// Captures the current browser screenshot as a byte array.use for attaching to Cucumber reports.

	public static byte[] captureScreenshotBytes() {

		WebDriver driver = DriverFactory.getDriver();

		logger.info("Capturing screenshot for Cucumber report.");

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

}