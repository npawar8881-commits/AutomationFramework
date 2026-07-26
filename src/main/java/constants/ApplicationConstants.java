package constants;

/**
 * ============================================================================
 * Class Name : ApplicationConstants
 *
 * Purpose:
 * Centralized location for application constant values.
 *
 * Responsibilities:
 * 1. Store application page titles.
 * 2. Store page headings.
 * 3. Store module names.
 * 4. Store reusable application text.
 *
 * NOTE:
 * This class contains only application constants.
 * ============================================================================
 */

public final class ApplicationConstants {

	/*
	 * Private Constructor
	 *
	 * Prevents object creation because this class
	 * acts only as a constant holder.
	 */
	private ApplicationConstants() {

	}

	public static final String INVALID_LOGIN_MESSAGE ="Invalid credentials";
	// =========================================================================
	// Dashboard
	// =========================================================================

	public static final String DASHBOARD_TITLE =
			"Dashboard";

	// =========================================================================
	// Modules
	// =========================================================================

	public static final String PIM_MODULE =
			"PIM";

}