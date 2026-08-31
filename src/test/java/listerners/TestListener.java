package listerners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {
	private static final Logger logger =
	        LoggerFactory.getLogger(TestListener.class);
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String screenshotName = testName + "_" + System.currentTimeMillis();
		String screenshotPath = ScreenshotUtils.captureScreenshot(screenshotName);
		System.out.println("Test failed: " + testName);
		System.out.println("Screenshot: " + screenshotPath);
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test started: {}", result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test suite started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test suite finished: " + context.getName());
	}
}