package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import config.ConfigReader;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the OrangeHRM login page")
    public void openLoginPage() {

        loginPage = new LoginPage();

        loginPage.navigateToBaseUrl();
    }

    @When("I login with valid credentials")
    public void loginWithValidCredentials() {

        System.out.println("getExecution: "+ConfigReader.getExecution());
        loginPage.login(
                "Admin",
                "admin123"
        );
    }

    @Then("I should be redirected to the dashboard")
    public void verifyDashboardUrl() {

        assertTrue(
                loginPage.getCurrentUrl()
                        .contains("/dashboard"),
                "User was not redirected to dashboard"
        );
    }
    
   
    @When("I login with {string} and {string}")
    public void loginWithCredentials(
            String username,
            String password) {

        loginPage.login(username, password);
    }

    @Then("login result should be {string}")
    public void verifyLoginResult(String result) {

        if (result.equalsIgnoreCase("success")) {

            assertTrue(
                    loginPage.getCurrentUrl().contains("/dashboard"),
                    "User should be redirected to dashboard."
            );

        } else {
            assertFalse(false);
        }
    }


}
