package tests;

import config.ConfigReader;
import driver.DriverManager;
import org.testng.annotations.Test;

//Our previous version had driver lifecycle code inside it.
//After extending BaseTest, its not required
public class BrowserLaunchTest extends BaseTest {

    @Test
    public void openApplication() {

        DriverManager.getDriver()
                .get(ConfigReader.getBaseUrl());

        System.out.println(
                "Page title: "
                        + DriverManager.getDriver().getTitle()
        );
    }
}