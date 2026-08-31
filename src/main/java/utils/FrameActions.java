package utils;

import driver.DriverManager;
import org.openqa.selenium.By;

public final class FrameActions {

    private FrameActions() {
    }

    public static void switchToFrame(By locator) {

        DriverManager.getDriver()
                .switchTo()
                .frame(
                        WaitUtils.waitForPresence(locator)
                );
    }

    public static void switchToFrame(int index) {

        DriverManager.getDriver()
                .switchTo()
                .frame(index);
    }

    public static void switchToDefaultContent() {

        DriverManager.getDriver()
                .switchTo()
                .defaultContent();
    }

    public static void switchToParentFrame() {

        DriverManager.getDriver()
                .switchTo()
                .parentFrame();
    }
}