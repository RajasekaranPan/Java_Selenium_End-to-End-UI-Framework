package utils;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ScreenshotUtils {

	//We are capturing screenshot in the listener
    private ScreenshotUtils() {
    }

    public static String captureScreenshot(String screenshotName) {

        TakesScreenshot screenshot =
                (TakesScreenshot) DriverManager.getDriver();

        File source =
                screenshot.getScreenshotAs(OutputType.FILE);

        Path destination =
                Path.of(
                        "target",
                        "screenshots",
                        screenshotName + ".png"
                );

        try {

            Files.createDirectories(
                    destination.getParent()
            );

            Files.copy(
                    source.toPath(),
                    destination
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot: "
                            + screenshotName,
                    e
            );
        }

        return destination.toString();
    }
}