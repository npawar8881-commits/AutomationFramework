package utilities;

import org.openqa.selenium.chrome.ChromeOptions;

public final class BrowserOptionsManager {

    private BrowserOptionsManager() {

    }
    
      public static ChromeOptions getChromeOptions() {

    	ChromeOptions options = new ChromeOptions();

    	if (ConfigReader.isHeadless()) {

    	    options.addArguments("--headless=new");
    	    options.addArguments("--window-size=1920,1080");

    	} else {

    	    options.addArguments("--start-maximized");

    	}

    	options.addArguments("--disable-notifications");
    	options.addArguments("--disable-popup-blocking");

        return options;

    }

}