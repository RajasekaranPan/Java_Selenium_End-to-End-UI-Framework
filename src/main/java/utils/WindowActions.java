package utils;

import driver.DriverManager;
import java.util.Set;


/**
 * Usage:
 * String originalWindow =
        WindowActions.getCurrentWindow();

	ElementActions.click(newWindowLink);
	
	WindowActions.switchToNewWindow(
        originalWindow
);
 */
public final class WindowActions {
	private WindowActions() {
	}

	public static String getCurrentWindow() {
		return DriverManager.getDriver().getWindowHandle();
	}

	public static Set<String> getAllWindows() {
		return DriverManager.getDriver().getWindowHandles();
	}

	public static void switchToWindow(String windowHandle) {
		DriverManager.getDriver().switchTo().window(windowHandle);
	}

	public static void closeCurrentWindow() {
		DriverManager.getDriver().close();
	}

	public static void switchToNewWindow(String originalWindow) {
		Set<String> windows = getAllWindows();
		for (String window : windows) {
			if (!window.equals(originalWindow)) {
				switchToWindow(window);
				return;
			}
		}
		throw new IllegalStateException("No new window was found.");
	}
}