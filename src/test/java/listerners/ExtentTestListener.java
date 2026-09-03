package listerners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentReportManager;
import utils.ScreenshotUtils;

public class ExtentTestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = ExtentReportManager
                .getExtentReports()
                .createTest(result.getMethod().getMethodName());

        TEST.set(test);
    }

    public static ExtentTest getTest() {
        return TEST.get();
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = TEST.get();

        if (test != null) {
            test.pass("Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = TEST.get();

        if (test != null) {

            String screenshotPath =
                    ScreenshotUtils.captureScreenshot(
                            result.getMethod().getMethodName()
                    );

            test.fail(result.getThrowable());

            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = TEST.get();

        if (test != null) {
            test.skip("Test skipped");
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {

        ExtentReportManager.getExtentReports().flush();

        TEST.remove();
    }
}