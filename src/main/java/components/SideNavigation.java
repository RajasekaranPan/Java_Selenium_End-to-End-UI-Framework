package components;

import org.openqa.selenium.By;

import pages.BuzzPage;
import utils.ElementActions;
import utils.WaitUtils;

public class SideNavigation {

	private final By buzzMenu =
            By.cssSelector("a[href*='/viewBuzz']");

    
    public BuzzPage clickBuzz() {

        ElementActions.click(buzzMenu);

        WaitUtils.waitForPageToLoad();

        return new BuzzPage();
    }
 
	/*
    private final By adminMenu =
            By.cssSelector("a[href*='/viewAdminModule']");

    private final By pimMenu =
            By.cssSelector("a[href*='/viewPimModule']");

    private final By leaveMenu =
            By.cssSelector("a[href*='/viewLeaveModule']");

  	public AdminPage clickAdmin() {

    ElementActions.click(adminMenu);

    WaitUtils.waitForPageToLoad();

    return new AdminPage();
}

public PIMPage clickPIM() {

    ElementActions.click(pimMenu);

    WaitUtils.waitForPageToLoad();

    return new PIMPage();
}

public LeavePage clickLeave() {

    ElementActions.click(leaveMenu);

    WaitUtils.waitForPageToLoad();

    return new LeavePage();
}

 */
   
}