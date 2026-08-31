package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import driver.DriverManager;
import listerners.RetryAnalyzer;
import pages.LoginPage;
import utils.TestDataReader;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
    		
        LoginPage loginPage = new LoginPage();
        

        //DriverManager.getDriver()
        //        .get(ConfigReader.getBaseUrl());

        loginPage.navigateToBaseUrl();
        
//        loginPage.login(
//                "Admin",
//                "admin123"
//        );
        loginPage.login(TestDataReader.get("valid.username"),
        		TestDataReader.get("valid.password")); 
        
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
       

    }
    
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void invalidLogin() {
    		
        LoginPage loginPage = new LoginPage();
        

        //DriverManager.getDriver()
        //        .get(ConfigReader.getBaseUrl());

        loginPage.navigateToBaseUrl();
        
//        loginPage.login(TestDataReader.get("valid.username"),
//        		TestDataReader.get("valid.password")); 
//        
      loginPage.login(
      "wrongusername",
      "admin123"
);
        
      SoftAssert softAssert = new SoftAssert();
      
        // version 1 - Intentional Failure
      softAssert.assertTrue(
                DriverManager.getDriver()
                        .getCurrentUrl()
                        .contains("/dasboard"),
                "User should be redirected to the dashboard after successful login."
        );
        
        // version 2
        //Since LoginPage inherits the functionality from BasePage.
        Assert.assertTrue( loginPage.getCurrentUrl() 
        		.contains("/dashboard"), 
        		"User should be redirected to the dashboard after successful login." );
       
        softAssert.assertAll();

    }
}
