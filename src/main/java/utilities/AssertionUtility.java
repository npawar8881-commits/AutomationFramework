package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

/* Purpose: Centralizes all framework assertions using TestNG SoftAssert.
 * Responsibilities: 1. Verify boolean conditions. 2. Verify equality. 3. Verify
 * null and not-null conditions. 4. Verify text contains expected value. 5.
 * Execute all collected assertions. 6. Support parallel execution usingThreadLocal */
 

public final class AssertionUtility {

	private static final Logger logger = LogManager.getLogger(AssertionUtility.class);

	// Thread-safe SoftAssert instance. Each test thread receives its own SoftAssertobject
	
	private static final ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

	private AssertionUtility() {
		// Prevent object creation.
	}

//	public static void verifyTrue(boolean condition, String message) {
//
//		logger.info("Verify True : {}", message);
//
//		softAssert.get().assertTrue(condition, message);
//
//	}
	
public static void veriyfyTrue(boolean condition, String massage) {

	softAssert.get().assertTrue(condition, massage);
}

	public static void verifyFalse(boolean condition, String message) {

		logger.info("Verify False : {}", message);

		softAssert.get().assertFalse(condition, message);

	}

	public	static void verifyEquals(Object actual, Object expected, String message) {

		logger.info("Verify Equals : {}", message);

		softAssert.get().assertEquals(actual, expected, message);

	}

	public static void verifyNotEquals(Object actual, Object expected, String message) {

		logger.info("Verify Not Equals : {}", message);

		softAssert.get().assertNotEquals(actual, expected, message);

	}

	public static void verifyNotNull(Object object, String message) {

		logger.info("Verify Not Null : {}", message);

		softAssert.get().assertNotNull(object, message);

	}

	public static void verifyNull(Object object, String message) {

		logger.info("Verify Null : {}", message);

		softAssert.get().assertNull(object, message);

	}

	public static void verifyContains(String actual, String expected, String message) {

		logger.info("Verify Contains : {}", message);

		softAssert.get().assertTrue(actual.contains(expected), message);

	}

//Executes all collected assertions.Clears the current thread SoftAssert instance afterwards.
	public static void assertAll() {
		logger.info("Executing all collected assertions.");

		try { softAssert.get().assertAll();}
      finally {softAssert.remove();}
     }

}