package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Responsibilities: 1. Load config.properties only once. 2. Provide
 * configuration values to the entire framework. 3. Centralize configuration
 * Design: Singleton-style utility using a static initializer. Benefit:
 * Configuration file is loaded only once when the class is first used.
 */

// private static final String CONFIG_FILE_PATH="src/main/resources/config.properties";
// Static Block:-->Executes only once when ConfigReader class is loaded.Loads,config.properties into memory.

public final class ConfigReader {

	private static final Properties properties = new Properties();

	static {

		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {

			if (inputStream == null) {
				throw new RuntimeException("config.properties file not found.");
			}

			properties.load(inputStream);

		} catch (IOException e) {

			throw new RuntimeException("Unable to load config.properties", e);

		}

	}

	// Private Prevents object creation,This class is not designed to create objects
	// because it provides only static utility methods
	private ConfigReader() {

	}

	private static String getSystemOrConfigValue(String key) {
		return System.getProperty(key, getValue(key));
	}

	// purpose:-->Read any value from config.properties.

	public static String getValue(String key) {

		return properties.getProperty(key);
	}

	public static String getBrowser() {

		return getSystemOrConfigValue("browser");

	}
	public static String getEnvironment() {
		return getSystemOrConfigValue("environment");
	}
	
	public static boolean isHeadless() {

	    return Boolean.parseBoolean(getSystemOrConfigValue("headless"));
	            

	}
	
	public static String getApplicationUrl() {
		return getValue("applicationUrl");

	}

	public static String getHomePageUrl() {

		return getValue("homePageUrl");

	}

	public static int getExplicitWait() {
		return Integer.parseInt(getValue("explicitWait"));

	}

	

}
