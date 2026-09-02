package utils;

import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private static final By LOADING_SPINNER =
            By.cssSelector(".oxd-loading-spinner");

    private WaitUtils() {
    }

    private static WebDriverWait getWait() {

        WebDriver driver = DriverManager.getDriver();

        return new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getExplicitWait())
        );
    }

    public static WebElement waitForVisible(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement waitForPresence(By locator) {
        return getWait().until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public static boolean waitForInvisible(By locator) {
        return getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public static boolean waitForElementToDisappear(By locator) {
        return waitForInvisible(locator);
    }

    public static boolean waitForUrlContains(String urlFragment) {
        return getWait().until(
                ExpectedConditions.urlContains(urlFragment)
        );
    }

    public static boolean waitForTitleContains(String titleFragment) {
        return getWait().until(
                ExpectedConditions.titleContains(titleFragment)
        );
    }

    public static org.openqa.selenium.Alert waitForAlert() {
        return getWait().until(
                ExpectedConditions.alertIsPresent()
        );
    }

    /**
     * Wait until browser document loading is completed.
     */
    public static void waitForDocumentReady() {

        getWait().until(driver ->
                "complete".equals(
                        ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState")
                )
        );
    }

    /**
     * Wait until application loading spinner disappears.
     */
    public static void waitForLoadingSpinnerToDisappear() {

        getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(
                        LOADING_SPINNER
                )
        );
    }

    /**
     * Wait until the page/application has reached a stable state.
     *
     * Includes:
     * 1. Browser document completely loaded
     * 2. Application loading spinner disappeared
     */
    public static void waitForPageToLoad() {

        waitForDocumentReady();
        waitForLoadingSpinnerToDisappear();
    }
}