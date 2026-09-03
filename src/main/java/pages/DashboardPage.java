package pages;

import driver.DriverManager;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pages.abstractPages.AuthenticatedBasePage;
import utils.ElementActions;
import utils.JavaScriptUtils;
import utils.WaitUtils;

public class DashboardPage extends AuthenticatedBasePage  {

    private final By dashboardHeader =
            By.xpath("//h6[normalize-space()='Dashboard']");

    private final By userDropdown =
            By.cssSelector(".oxd-userdropdown");

    private final By logoutLink =
            By.xpath("//a[normalize-space()='Logout']");
    
    private final By helpButton =
            By.cssSelector("button.oxd-icon-button[title='Help']");

    private final By helpSearchBox =
            By.cssSelector("input[type='search']");

    public DashboardPage() {
        super();
    }

    public boolean isDashboardDisplayed() {
        return WaitUtils
                .waitForVisible(dashboardHeader)
                .isDisplayed();
    }

    public String getDashboardHeader() {
        return WaitUtils
                .waitForVisible(dashboardHeader)
                .getText();
    }

    public DashboardPage openUserDropdown() {
        ElementActions.click(userDropdown);
        return this;
    }

    public LoginPage logout() {

        openUserDropdown();

        ElementActions.click(logoutLink);

        WaitUtils.waitForPageToLoad();

        return new LoginPage();
    }
    

    public DashboardPage clickHelp() {

        WaitUtils.waitForClickable(helpButton);
        JavaScriptUtils.hiddenClick(helpButton);
        
        return this;
    }
    
    
    public DashboardPage switchToHelpAndSwitchBackToOriginalTab(String keyword) {

        String originalWindow = driver.getWindowHandle();
        
        this.clickHelp();
        
        Set<String> allWindows = driver.getWindowHandles();

        WebDriver driver = DriverManager.getDriver();
        for (String window : allWindows) {

            if (!window.equals(originalWindow)) {
            		driver.switchTo().window(window);
                break;
            }
        }

        WaitUtils.waitForPageToLoad();

        WaitUtils.waitForVisible(helpSearchBox);

        ElementActions.type(helpSearchBox, keyword);
        
        driver.switchTo().window(originalWindow);
        
        return this;
    }


}