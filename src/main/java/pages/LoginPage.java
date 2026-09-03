package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.abstractPages.BasePage;
import utils.ElementActions;
import utils.WaitUtils;

public class LoginPage extends BasePage {

	private static final Logger logger =
	        LoggerFactory.getLogger(LoginPage.class);
	
    private final By usernameInput =
            By.name("username");

    private final By passwordInput =
            By.name("password");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    
    // Notice that LoginPage itself doesn't need to explicitly call super().
    // Java automatically calls the no-argument constructor of BasePage.
    
    public LoginPage enterUsername(String username) {

        ElementActions.type(
                usernameInput,
                username
        );

        return this;
    }

    public LoginPage enterPassword(String password) {

        ElementActions.type(
                passwordInput,
                password
        );

        return this;
    }

    public DashboardPage clickLogin() {

        ElementActions.click(loginButton);
        WaitUtils.waitForPageToLoad();
        return new DashboardPage();
    }

    public DashboardPage login(
            String username,
            String password) {

    	logger.info("Login into application using {} ", username);
        
    	return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }
}
