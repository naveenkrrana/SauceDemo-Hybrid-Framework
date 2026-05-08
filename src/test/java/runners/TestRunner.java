package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

// @CucumberOptions acts as the configuration dashboard for your BDD tests
@CucumberOptions(
        features = "src/test/resources/features", // 1. Where are the English files?
        glue = "stepDefinitions",                 // 2. Where is the Java translation code?
        plugin = {                                // 3. What kind of reports do we want?
                "pretty"
                //"html:target/cucumber-reports/cucumber-report.html"
        },
        monochrome = true                         // 4. Cleans up the terminal output
)
// By extending AbstractTestNGCucumberTests, we turn this standard class into a TestNG execution engine
public class TestRunner extends AbstractTestNGCucumberTests {

    // Overriding this method is how we tell Cucumber to use TestNG's parallel execution!
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}