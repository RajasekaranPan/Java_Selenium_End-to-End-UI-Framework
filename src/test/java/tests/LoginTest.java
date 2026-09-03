package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.ConfigReader;
import driver.DriverManager;
import listerners.RetryAnalyzer;
import pages.DashboardPage;
import pages.LoginPage;
import reporting.ExtentReportManager;
import reporting.ReportUtils;
import tests.abstractClasses.BaseTest;
import utils.TestDataReader;

public class LoginTest extends BaseTest {
	
	@Test(groups = {"smoke", "regression", "loginFunctionalities"})
    public void validLogin() {
    		
        LoginPage loginPage = new LoginPage();
        
        System.out.println(ConfigReader.getEnvironment());
        System.out.println(ConfigReader.getExecution());
        //DriverManager.getDriver()
        //        .get(ConfigReader.getBaseUrl());

        ReportUtils.step("Navigating to login page");
        loginPage.navigateToBaseUrl();
        
//        loginPage.login(
//                "Admin",
//                "admin123"
//        );
        
        DashboardPage dashboardPage = loginPage.login(
                TestDataReader.get("valid.username"),
                TestDataReader.get("valid.password")
        );
       
        ReportUtils.step("Validating successful login");
        // version 1
        Assert.assertTrue(
                DriverManager.getDriver()
                        .getCurrentUrl()
                        .contains("/dashboard"),
                "User should be redirected to the dashboard after successful login."
        );
        
        // version 2
        //Since LoginPage inherits the functionality from BasePage.
        Assert.assertTrue( loginPage.getCurrentUrl() 
        		.contains("/dashboard"), 
        		"User should be redirected to the dashboard after successful login." );
       
		
		loginPage = dashboardPage.logout();
        
        loginPage.getCurrentUrl();
        
        Assert.assertTrue(loginPage.getCurrentUrl().endsWith("/auth/login"), 
        		"Did not back to login screen");
	}
    
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void invalidLogin() {
    		
        LoginPage loginPage = new LoginPage();
       
        //DriverManager.getDriver()
        //        .get(ConfigReader.getBaseUrl());

        ReportUtils.step("Navigating to login page");
        loginPage.navigateToBaseUrl();
        
//        loginPage.login(TestDataReader.get("valid.username"),
//        		TestDataReader.get("valid.password")); 
//        
      DashboardPage dashboardPage = loginPage.login(
      "wrongusername",
      "admin123");
        
      SoftAssert softAssert = new SoftAssert();
      
      ReportUtils.step("Validated failure login");
        // version 1 - Intentional Failure
      softAssert.assertFalse(
                dashboardPage
                        .getCurrentUrl()
                        .contains("/dashboard"),
                "User should be redirected to the dashboard after successful login."
        );
        
        // version 2
        //Since LoginPage inherits the functionality from BasePage.
        Assert.assertFalse(dashboardPage.getCurrentUrl()
        		.contains("/dashboard"), 
        		"User should be redirected to the dashboard after successful login." );
       
        softAssert.assertAll();

    }
}
