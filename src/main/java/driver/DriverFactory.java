package driver;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public final class DriverFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser =
                ConfigReader.getBrowser().toLowerCase();

        String execution =
                ConfigReader.getExecution().toLowerCase();

        logger.info(
                "Creating browser: {} | execution: {}",
                browser,
                execution
        );

        WebDriver driver = switch (execution) {

            case "local" ->
                    LocalDriverFactory.createDriver(browser);

            case "remote" ->
                    RemoteDriverFactory.createDriver(browser);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported execution mode: " + execution
                    );
        };

        configureDriver(driver);

        return driver;
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
                        Duration.ofSeconds(
                                ConfigReader.getImplicitWait()
                        )
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(
                                ConfigReader.getPageLoadTimeout()
                        )
                );
    }
}