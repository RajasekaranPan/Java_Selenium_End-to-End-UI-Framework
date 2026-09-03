package reporting;

import com.aventstack.extentreports.ExtentTest;
import listerners.ExtentTestListener;

public final class ReportUtils {

    private ReportUtils() {
    }

    public static void step(String message) {

        ExtentTest test = ExtentTestListener.getTest();

        if (test != null) {
            test.info(message);
        }
    }

    public static void pass(String message) {

        ExtentTest test = ExtentTestListener.getTest();

        if (test != null) {
            test.pass(message);
        }
    }

    public static void fail(String message) {

        ExtentTest test = ExtentTestListener.getTest();

        if (test != null) {
            test.fail(message);
        }
    }
}