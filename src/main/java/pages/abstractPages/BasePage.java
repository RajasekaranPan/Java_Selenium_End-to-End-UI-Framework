package pages.abstractPages;

import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {

        driver.get(url);

        WaitUtils.waitForPageToLoad();
    }

    public void navigateToBaseUrl() {

        navigateTo(ConfigReader.getBaseUrl());
    }

    public void refreshPage() {

        driver.navigate().refresh();

        WaitUtils.waitForPageToLoad();
    }

    public void goBack() {

        driver.navigate().back();

        WaitUtils.waitForPageToLoad();
    }

    public void goForward() {

        driver.navigate().forward();

        WaitUtils.waitForPageToLoad();
    }
}