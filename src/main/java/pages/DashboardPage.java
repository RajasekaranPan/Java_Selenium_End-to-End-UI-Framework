package pages;

import org.openqa.selenium.By;

import pages.abstractPages.AuthenticatedBasePage;
import utils.ElementActions;
import utils.WaitUtils;

public class DashboardPage extends AuthenticatedBasePage  {

    private final By dashboardHeader =
            By.xpath("//h6[normalize-space()='Dashboard']");

    private final By userDropdown =
            By.cssSelector(".oxd-userdropdown");

    private final By logoutLink =
            By.xpath("//a[normalize-space()='Logout']");

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
}