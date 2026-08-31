package pages;

import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

//	Page-level operations
//	├── navigate
//	├── refresh
//	├── back
//	├── forward
//	├── title
//	└── URL
	
	//Why protected WebDriver driver?
	//Page Objects sometimes need access to the current driver for page-level operations.
    protected final WebDriver driver;

    //We re-initialise driver object for interacting with page
    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {

        driver.get(url);
    }

    public void refreshPage() {

        driver.navigate().refresh();
    }

    public void goBack() {

        driver.navigate().back();
    }

    public void goForward() {

        driver.navigate().forward();
    }

    public void navigateToBaseUrl() {

        driver.get(ConfigReader.getBaseUrl());
    }
}