package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import tests.abstractClasses.BaseTest;
import utils.TestDataReader;

public class DashboardPageHelpOpensInNewTabAndSearch extends BaseTest{
	@Test
	public void verifyHelpOpensInNewTabAndSearch() {

		LoginPage loginPage = new LoginPage();
		
		loginPage.navigateToBaseUrl();
		
	    DashboardPage dashboardPage =loginPage.login(
	                TestDataReader.get("valid.username"),
	                TestDataReader.get("valid.password")
	        );
	      
		dashboardPage
	            .switchToHelpAndSwitchBackToOriginalTab("OrangeHRM");

        Assert.assertTrue(dashboardPage.getCurrentUrl() 
        		.contains("/dashboard"), 
        		"User should be redirected to the dashboard after successful login." );
       
	}
	
}
