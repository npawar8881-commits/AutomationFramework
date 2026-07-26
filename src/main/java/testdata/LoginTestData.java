package testdata;

import java.util.Map;

import constants.FrameworkConstants;
import utilities.ExcelUtility;

/*
 * Test Case
 * |
 * |
 * LoginTestData
 * |
 * |
 * LoginData Object
 * |
 * |
 * LoginSteps
 * |
 * |
 * LoginPage
 */

//Reads/builds LoginData from Excel.

//Purpose: Test data factory/provider
/*Responsibility:

Decide which login data scenario is required
Build and return LoginData
Hide data source implementation*/

public final class LoginTestData {

	private static final String FILE_NAME =
			FrameworkConstants.LOGIN_TEST_DATA_FILE;
	private static final String SHEET_NAME = FrameworkConstants.LOGIN_TEST_DATA_SHEET;

	private LoginTestData() {

	}

	// NEW:
	// Returns LoginData object for the requested test case.
	// Excel implementation is hidden from Step Definitions.
	public static LoginData getLoginData(String testcase) {

		Map<String, String> testData = ExcelUtility.getTestData(FILE_NAME,SHEET_NAME,testcase);
	
		return new LoginData(testData.get("Username"),testData.get("Password"));

	}

}










