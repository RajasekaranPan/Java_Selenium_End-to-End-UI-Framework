package utils;

import config.ConfigReader;
import driver.DriverManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

	// Synchronizations

	private WaitUtils() {
	}

	private static WebDriverWait getWait() {

		WebDriver driver = DriverManager.getDriver();

		return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
	}

	// Prefer By as the parameter instead of WebElement
	// WaitUtils.waitForVisible(driver.findElement(username));
	// Because Selenium can repeatedly locate the element while waiting.
	// This is especially useful for dynamic applications where the DOM changes.
	public static WebElement waitForVisible(By locator) {

		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForClickable(By locator) {

		return getWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement waitForPresence(By locator) {

		return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static boolean waitForInvisible(By locator) {

		return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static boolean waitForElementToDisappear(By locator) {

		return waitForInvisible(locator);
	}

	public static boolean waitForUrlContains(String urlFragment) {

		return getWait().until(ExpectedConditions.urlContains(urlFragment));
	}

	public static boolean waitForTitleContains(String titleFragment) {

		return getWait().until(ExpectedConditions.titleContains(titleFragment));
	}

	public static Alert waitForAlert() {
		return getWait().until(ExpectedConditions.alertIsPresent());
	}
}