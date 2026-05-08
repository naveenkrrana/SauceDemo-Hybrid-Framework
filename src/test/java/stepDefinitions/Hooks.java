package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

// By extending BaseTest, we get access to your setUp() and tearDown() methods!
public class Hooks extends BaseTest {

    // This is a Cucumber annotation. It runs before every Scenario in your feature files.
    @Before
    public void startBrowserEngine() {
        // We manually trigger your existing TestNG setup logic!
        setUp();
    }

    // This runs after every Scenario.
    @After
    public void closeBrowserEngine() {
        tearDown();
    }
}