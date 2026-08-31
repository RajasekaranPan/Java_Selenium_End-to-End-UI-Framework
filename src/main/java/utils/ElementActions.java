package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class ElementActions {

//	Element interactions
//	├── click
//	├── type
//	├── getText
//	├── getAttribute
//	└── state checks

	private ElementActions() {
	}

	public static void click(By locator) {

		WaitUtils.waitForClickable(locator).click();
	}

	public static void type(By locator, String text) {

		WebElement element = WaitUtils.waitForVisible(locator);

		element.clear();
		element.sendKeys(text);
	}

	public static String getText(By locator) {

		return WaitUtils.waitForVisible(locator).getText();
	}

	public static String getAttribute(By locator, String attribute) {

		return WaitUtils.waitForPresence(locator).getAttribute(attribute);
	}

	//This means isDisplayed() can potentially wait the entire explicit timeout before returning false.
//	For an assertion/state-check method, that's not always desirable.
//	For now, let's leave it because we'll revisit state checks vs waits when we build our assertion utilities.
//	Don't over-engineer it yet.
	public static boolean isDisplayed(By locator) {

		try {
			return WaitUtils.waitForVisible(locator).isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEnabled(By locator) {

		return WaitUtils.waitForPresence(locator).isEnabled();
	}

	public static boolean isSelected(By locator) {

		return WaitUtils.waitForPresence(locator).isSelected();
	}

	
	//Checkbox
	public static void check(By locator) {
		WebElement element = WaitUtils.waitForVisible(locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public static void uncheck(By locator) {
		WebElement element = WaitUtils.waitForVisible(locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	//Radio button
	public static void selectRadio(By locator) {
		WebElement element = WaitUtils.waitForVisible(locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

}