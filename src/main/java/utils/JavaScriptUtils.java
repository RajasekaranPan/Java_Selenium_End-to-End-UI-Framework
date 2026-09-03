package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import driver.DriverManager;

public final class JavaScriptUtils {

    private JavaScriptUtils() {
        // Utility class
    }

    private static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }

    private static WebElement find(By locator) {
        return DriverManager.getDriver().findElement(locator);
    }

    // --------------------------------------------------
    // Scroll
    // --------------------------------------------------

    public static void scrollIntoView(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                find(locator)
        );
    }

    public static void scrollIntoViewInstantly(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].scrollIntoView({behavior:'instant', block:'center'});",
                find(locator)
        );
    }

    public static void scrollToTop() {
        getJSExecutor().executeScript(
                "window.scrollTo(0, 0);"
        );
    }

    public static void scrollToBottom() {
        getJSExecutor().executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );
    }

    // --------------------------------------------------
    // Click
    // --------------------------------------------------

    public static void click(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].click();",
                find(locator)
        );
    }

    /**
     * Performs JavaScript click even when Selenium's
     * normal clickability checks prevent interaction.
     */
    public static void hiddenClick(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].click();",
                find(locator)
        );
    }

    // --------------------------------------------------
    // Input
    // --------------------------------------------------

    public static void setValue(By locator, String value) {
        getJSExecutor().executeScript(
                "arguments[0].value = arguments[1];",
                find(locator),
                value
        );
    }

    public static void clear(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].value = '';",
                find(locator)
        );
    }

    // --------------------------------------------------
    // Element information
    // --------------------------------------------------

    public static String getAttribute(By locator, String attribute) {
        return (String) getJSExecutor().executeScript(
                "return arguments[0].getAttribute(arguments[1]);",
                find(locator),
                attribute
        );
    }

    public static String getText(By locator) {
        return (String) getJSExecutor().executeScript(
                "return arguments[0].innerText;",
                find(locator)
        );
    }

    // --------------------------------------------------
    // Debugging
    // --------------------------------------------------

    public static void highlight(By locator) {
        getJSExecutor().executeScript(
                "arguments[0].style.border='3px solid red';",
                find(locator)
        );
    }

    // --------------------------------------------------
    // Browser / Page
    // --------------------------------------------------

    public static void refreshPage() {
        getJSExecutor().executeScript("location.reload();");
    }

    public static String getReadyState() {
        return (String) getJSExecutor().executeScript(
                "return document.readyState;"
        );
    }

    public static String getPageTitle() {
        return (String) getJSExecutor().executeScript(
                "return document.title;"
        );
    }

    public static String getCurrentUrl() {
        return (String) getJSExecutor().executeScript(
                "return window.location.href;"
        );
    }

    // --------------------------------------------------
    // Generic JavaScript
    // --------------------------------------------------

    public static Object execute(String script, Object... arguments) {
        return getJSExecutor().executeScript(script, arguments);
    }
}