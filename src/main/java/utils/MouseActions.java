package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public final class MouseActions {

    private MouseActions() {
    }

    public static void hover(By locator) {

        Actions actions =
                new Actions(DriverManager.getDriver());

        actions.moveToElement(
                WaitUtils.waitForVisible(locator)
        ).perform();
    }

    public static void doubleClick(By locator) {

        Actions actions =
                new Actions(DriverManager.getDriver());

        actions.doubleClick(
                WaitUtils.waitForClickable(locator)
        ).perform();
    }

    public static void rightClick(By locator) {

        Actions actions =
                new Actions(DriverManager.getDriver());

        actions.contextClick(
                WaitUtils.waitForClickable(locator)
        ).perform();
    }
}