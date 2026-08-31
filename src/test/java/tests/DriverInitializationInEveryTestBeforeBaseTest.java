package tests;

import config.ConfigReader;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriverInitializationInEveryTestBeforeBaseTest {

    @BeforeMethod
    public void setUp() {

        WebDriver driver = DriverFactory.createDriver();

        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
    }

    //mvn clean test -Denv=qa -Dbrowser=chrome
    //mvn clean test -Denv=qa -Dbrowser=firefox
    //mvn clean test -Denv=qa -Dbrowser=edge
    //mvn clean test -Denv=qa -Dbrowser=internetexplorer
    @Test
    public void openApp() {

        DriverManager.getDriver()
                .get(ConfigReader.getBaseUrl());

        System.out.println(
                "Page title: "
                        + DriverManager.getDriver().getTitle()
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}