package tests;

import driver.DriverFactory;
import driver.DriverManager;
import listerners.ExtentTestListener;
import listerners.TestListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Why abstract?
//We don't want to execute BaseTest itself.
//BaseTest
//↑
//LoginTest
//SearchTest
//CheckoutTest

//Test lifecycle
//├── Create WebDriver
//└── Quit WebDriver

@Listeners({TestListener.class,
	ExtentTestListener.class})
public abstract class BaseTest {

	private static final Logger logger =
	        LoggerFactory.getLogger(BaseTest.class);
	        		
    @BeforeMethod
    public void setUp() {

    	logger.info("Starting browser");
        WebDriver driver = DriverFactory.createDriver();

        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {

    	logger.info("Closing browser");
        DriverManager.quitDriver();
    }
}