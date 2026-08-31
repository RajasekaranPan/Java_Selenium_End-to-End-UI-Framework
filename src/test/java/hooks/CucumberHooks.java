package hooks;

import com.aventstack.extentreports.ExtentTest;

import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reporting.ExtentReportManager;
import utils.ScreenshotUtils;

public class CucumberHooks {
	private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

	@Before
	public void setUp(Scenario scenario) {
		DriverManager.setDriver(DriverFactory.createDriver());
		ExtentTest test = ExtentReportManager.getExtentReports().createTest(scenario.getName());
		TEST.set(test);
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotPath = ScreenshotUtils.captureScreenshot(scenario.getName());
			TEST.get().fail("Scenario failed").addScreenCaptureFromPath(screenshotPath);
		} else {
			TEST.get().pass("Scenario passed");
		}
		DriverManager.quitDriver();
		TEST.remove();
		ExtentReportManager.getExtentReports().flush();
	}
}