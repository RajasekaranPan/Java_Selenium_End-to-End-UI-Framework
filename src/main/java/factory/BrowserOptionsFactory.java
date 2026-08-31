package factory;

import config.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserOptionsFactory {

    private BrowserOptionsFactory() {
    }

    public static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {

        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("-headless");
        }

        return options;
    }

    public static EdgeOptions getEdgeOptions() {

        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-notifications");

        return options;
    }
}