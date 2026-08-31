package tests;

import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {
	//cd C:\Users\Rajasekaran P\eclipse-workspace\selenium-java-framework
	//mvn clean test -Denv=qa
    @Test
    public void verifyConfiguration() {

        System.out.println("Environment: "
                + ConfigReader.getEnvironment());

        System.out.println("Browser: "
                + ConfigReader.getBrowser());

        System.out.println("Headless: "
                + ConfigReader.isHeadless());

        System.out.println("Base URL: "
                + ConfigReader.getBaseUrl());

        System.out.println("Explicit Wait: "
                + ConfigReader.getExplicitWait());

        Assert.assertEquals(
                ConfigReader.getEnvironment(),
                ConfigReader.getEnvironment()
        );
    }
}