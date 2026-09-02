package runners;

import org.testng.annotations.DataProvider;

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

// mvn clean test -Dbrowser=chrome -Dexecution=local -Denv=qa 
// -Dcucumber.filter.tags="@negative" -Dtest.suite.file=cucumber.xml
public class CucumberTestRunner
        extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(
            parallel = true
    )
    public Object[][] scenarios() {

        return super.scenarios();
    }
}
