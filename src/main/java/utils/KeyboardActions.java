package utils;

import driver.DriverManager;
import org.openqa.selenium.Keys;

public final class KeyboardActions {

    private KeyboardActions() {
    }

    public static void press(Keys key) {

        DriverManager.getDriver()
                .switchTo()
                .activeElement()
                .sendKeys(key);
    }

    public static void pressEnter() {

        press(Keys.ENTER);
    }

    public static void pressEscape() {

        press(Keys.ESCAPE);
    }

    public static void pressTab() {

        press(Keys.TAB);
    }
}