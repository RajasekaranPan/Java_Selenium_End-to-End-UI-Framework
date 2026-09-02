package driver;

import factory.BrowserOptionsFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class RemoteDriverFactory {

    private static final String GRID_URL =
            "http://localhost:4444";

    private RemoteDriverFactory() {
    }

    public static WebDriver createDriver(String browser) {

        Capabilities options = switch (browser.toLowerCase()) {

            case "chrome" ->
                    BrowserOptionsFactory.getChromeOptions();

            case "firefox" ->
                    BrowserOptionsFactory.getFirefoxOptions();

            case "edge" ->
                    BrowserOptionsFactory.getEdgeOptions();

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported remote browser: " + browser
                    );
        };

        try {

            return new RemoteWebDriver(
                    new URL(GRID_URL),
                    options
            );

        } catch (MalformedURLException e) {

            throw new IllegalStateException(
                    "Invalid Selenium Grid URL: " + GRID_URL,
                    e
            );
        }
    }
}