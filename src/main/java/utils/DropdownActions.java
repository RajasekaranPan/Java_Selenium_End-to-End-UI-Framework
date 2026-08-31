package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

//Usage: 
//DropdownActions.selectByVisibleText(
//countryDropdown,
//"India"
//);
public final class DropdownActions {

    private DropdownActions() {
    }

    private static Select getSelect(By locator) {

        return new Select(
                WaitUtils.waitForVisible(locator)
        );
    }

    public static void selectByVisibleText(
            By locator,
            String text) {

        getSelect(locator)
                .selectByVisibleText(text);
    }

    public static void selectByValue(
            By locator,
            String value) {

        getSelect(locator)
                .selectByValue(value);
    }

    public static void selectByIndex(
            By locator,
            int index) {

        getSelect(locator)
                .selectByIndex(index);
    }

    public static String getSelectedOption(
            By locator) {

        return getSelect(locator)
                .getFirstSelectedOption()
                .getText();
    }
}
