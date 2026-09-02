package pages.abstractPages;

import components.Header;
import components.SideNavigation;
import components.UserMenu;

// Dashboard, PIM, Admin, Leave, Buzz etc.
// They should also extend AuthenticatedBasePage.
// all authenticated pages automatically have access to getSideNavigation()
public abstract class AuthenticatedBasePage extends BasePage {

    protected final SideNavigation sideNavigation;
    protected final Header header;
    protected final UserMenu userMenu;

    //These are common UI component after login into application that appears in every pages.
    protected AuthenticatedBasePage() {
        super();
        this.sideNavigation = new SideNavigation();
		//Below are added for reference
        this.header = new Header();
		this.userMenu = new UserMenu();
    }

    public SideNavigation getSideNavigation() {
        return sideNavigation;
    }
}