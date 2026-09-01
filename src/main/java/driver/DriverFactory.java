package driver;

import config.ConfigReader;
import factory.BrowserOptionsFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverFactory.class);

    private static final String GRID_URL =
            "http://localhost:4444";

    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigReader.getBrowser().toLowerCase();
        String execution = ConfigReader.getExecution().toLowerCase();

        logger.info(
                "Creating browser: {} | execution: {}",
                browser,
                execution
        );

        WebDriver driver;

        switch (browser) {

            case "chrome":

                if (execution.equals("remote")) {

                    driver = createRemoteDriver(
                            BrowserOptionsFactory.getChromeOptions()
                    );

                } else {

                    driver = new ChromeDriver(
                            BrowserOptionsFactory.getChromeOptions()
                    );
                }

                break;

            case "firefox":

                if (execution.equals("remote")) {

                    driver = createRemoteDriver(
                            BrowserOptionsFactory.getFirefoxOptions()
                    );

                } else {

                    driver = new FirefoxDriver(
                            BrowserOptionsFactory.getFirefoxOptions()
                    );
                }

                break;

            case "edge":

                if (execution.equals("remote")) {

                    driver = createRemoteDriver(
                            BrowserOptionsFactory.getEdgeOptions()
                    );

                } else {

                    driver = new EdgeDriver(
                            BrowserOptionsFactory.getEdgeOptions()
                    );
                }

                break;

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }

        configureDriver(driver);

        return driver;
    }

    private static WebDriver createRemoteDriver(
            Capabilities options) {

        try {

            logger.info(
                    "Connecting to Selenium Grid: {}",
                    GRID_URL
            );

            return new RemoteWebDriver(
                    new URL(GRID_URL),
                    options
            );

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Invalid Selenium Grid URL: " + GRID_URL,
                    e
            );
        }
    }

    private static void configureDriver(WebDriver driver) {

        if (ConfigReader.isWindowMaximize()) {

            driver.manage()
                    .window()
                    .maximize();
        }

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        java.time.Duration.ofSeconds(
                                ConfigReader.getImplicitWait()
                        )
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        java.time.Duration.ofSeconds(
                                ConfigReader.getPageLoadTimeout()
                        )
                );
    }
}