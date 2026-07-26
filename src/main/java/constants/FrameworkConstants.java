package constants;

/*
 * Constants are considered framework configuration artifacts,
 * not utilities.
 */

/**
 * ============================================================================
 * Class Name : FrameworkConstants
 *
 * Purpose:
 * Centralized location for framework constant values.
 *
 * Responsibilities:
 * 1. Store framework folder paths.
 * 2. Store report file paths.
 * 3. Store screenshot locations.
 * 4. Store Excel test data locations.
 *
 * NOTE:
 * This class contains only constants.
 * No business logic should be added here.
 * ============================================================================
 */

public final class FrameworkConstants {

	/*
	 * Private Constructor
	 *
	 * Prevents object creation because this class
	 * acts only as a constant holder.
	 */
	private FrameworkConstants() {

	}

	// =========================================================================
	// Project Directories
	// =========================================================================

	public static final String PROJECT_DIRECTORY =
			System.getProperty("user.dir");

	public static final String REPORT_DIRECTORY =
			PROJECT_DIRECTORY + "/reports";

	public static final String SCREENSHOT_DIRECTORY =
			REPORT_DIRECTORY + "/screenshots";

	// =========================================================================
	// Report Files
	// =========================================================================

	public static final String EXTENT_REPORT_FILE =
			REPORT_DIRECTORY + "/ExtentReport.html";

	// =========================================================================
	// Excel Test Data
	// =========================================================================

	public static final String TEST_DATA_DIRECTORY =
			"test_data/";

	public static final String LOGIN_TEST_DATA_FILE =
			"LoginTestData.xlsx";
	public static final String LOGIN_TEST_DATA_SHEET = "Login";
}