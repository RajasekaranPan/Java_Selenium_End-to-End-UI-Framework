package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static ExtentReports getExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            "target/reports/ExtentReport.html"
                    );

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Framework",
                    "Selenium Java TestNG"
            );

            extentReports.setSystemInfo(
                    "Java",
                    System.getProperty("java.version")
            );
        }

        return extentReports;
    }
}
