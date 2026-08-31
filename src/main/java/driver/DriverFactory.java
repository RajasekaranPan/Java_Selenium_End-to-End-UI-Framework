package driver;

import config.ConfigReader;
import factory.BrowserOptionsFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

public final class DriverFactory {

	//Browser creation
	
	private static final Logger logger = LoggerFactory.
			getLogger(DriverFactory.class);
	
    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigReader.getBrowser().toLowerCase();
        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty("headless", "false")
                );

        logger.info( "Creating browser: {} | headless: {}", browser, headless );
        
        WebDriver driver = switch (browser) {

            case "chrome" ->
                    new ChromeDriver(
                            BrowserOptionsFactory.getChromeOptions()
                    );

            case "firefox" ->
                    new FirefoxDriver(
                            BrowserOptionsFactory.getFirefoxOptions()
                    );

            case "edge" ->
                    new EdgeDriver(
                            BrowserOptionsFactory.getEdgeOptions()
                    );

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        };

        configureDriver(driver);

        return driver;
    }

    private static void configureDriver(WebDriver driver) {

        if (ConfigReader.isWindowMaximize()) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(
                java.time.Duration.ofSeconds(
                        ConfigReader.getImplicitWait()
                )
        );

        driver.manage().timeouts().pageLoadTimeout(
                java.time.Duration.ofSeconds(
                        ConfigReader.getPageLoadTimeout()
                )
        );
    }
}