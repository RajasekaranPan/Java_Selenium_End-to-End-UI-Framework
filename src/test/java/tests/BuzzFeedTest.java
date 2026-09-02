package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.BuzzFeedDataProvider;
import pages.BuzzPage;
import pages.DashboardPage;
import testdata.FeedTestData;
import tests.abstractClasses.BaseTest;
import config.ConfigReader;
import pages.LoginPage;
import utils.TestDataReader;

public class BuzzFeedTest extends BaseTest {

    @Test(
            dataProvider = "feedData",
            dataProviderClass = BuzzFeedDataProvider.class,
            description = "Create a new Buzz Feed and validate the latest post"
    )
    public void createAndValidateFeed(FeedTestData data) {

        LoginPage loginPage = new LoginPage();

        System.out.println(ConfigReader.getEnvironment());
        System.out.println(ConfigReader.getExecution());
        /*
         * STEP 1
         * Navigate to OrangeHRM login page
         */
        loginPage.navigateToBaseUrl();

        /*
         * STEP 2
         * Login using Admin credentials
         */
        DashboardPage dashboardPage =loginPage.login(
                TestDataReader.get("valid.username"),
                TestDataReader.get("valid.password")
        );

        /*
         * STEP 3
         * Navigate to Buzz
         */
        //BuzzPage buzzPage = new BuzzPage();
        //buzzPage.clickBuzz();
        
        BuzzPage buzzPage = dashboardPage.getSideNavigation().clickBuzz();
        
        /*
         * STEP 4
         * Create new Feed
         */
        buzzPage.createFeed(
                data.getContent()
        );

        /*
         * STEP 5
         * Validate latest/top Feed
         */
        String actualFeed =
                buzzPage.getTopFeedContent();

        Assert.assertTrue(
                actualFeed.contains(data.getContent()),
                data.getTestCaseId()
                        + " - Feed was not created successfully. "
                        + "Expected: "
                        + data.getContent()
                        + " | Actual: "
                        + actualFeed
        );
    }
}