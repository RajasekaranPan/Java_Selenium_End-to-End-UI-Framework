package utils;

import driver.DriverManager;
import org.openqa.selenium.Alert;

public final class AlertActions {

	private AlertActions() {
	}

	private static Alert getAlert() {

		return WaitUtils.waitForAlert();
	}

	public static void accept() {

		getAlert().accept();
	}

	public static void dismiss() {

		getAlert().dismiss();
	}

	public static String getText() {

		return getAlert().getText();
	}

	public static void enterText(String text) {

		getAlert().sendKeys(text);
	}
}
