package driver;

import config.ConfigReader;
import factory.BrowserOptionsFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalDriverFactory {

    private LocalDriverFactory() {
    }

    public static WebDriver createDriver(String browser) {

        return switch (browser.toLowerCase()) {

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

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported local browser: " + browser
                    );
        };
    }
}