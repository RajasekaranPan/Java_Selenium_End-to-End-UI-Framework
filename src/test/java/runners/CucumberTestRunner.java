package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "stepdefinitions",
                "hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html"
        },
        monochrome = true
)

//mvn clean test -Dcucumber.filter.tags="@smoke or @regression"
public class CucumberTestRunner
        extends AbstractTestNGCucumberTests {

    @Override
    @org.testng.annotations.DataProvider(
            parallel = true
    )
    public Object[][] scenarios() {

        return super.scenarios();
    }
}
