package driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Without our manager, we'd end up doing: - supposed to pass driver object everywhere.
//public void login(WebDriver driver) {
//...
//}
//Instead:
//DriverManager.getDriver() and it internally uses ThreadLocal<WebDriver> 
//which gives us the current driver's instance.

public final class DriverManager {

	private static final Logger logger =
	        LoggerFactory.getLogger(DriverManager.class);
	
	//WebDriver lifecycle/storage
    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setDriver(WebDriver driver) {
    	logger.info("Initializing WebDriver");
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized."
            );
        }
        return driver;
    }

    public static void quitDriver() {
    	logger.info("Closing WebDriver");
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}