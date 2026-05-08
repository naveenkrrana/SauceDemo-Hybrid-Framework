package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    // 1. The Data Provider
    // This method returns a 2D Array of Objects. Think of it like an Excel table with rows and columns.
    @DataProvider(name = "sauceUsers", parallel = true)
    public Object[][] getLoginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    // 2. The Test
    // We link the test to the data provider by name.
    // The test MUST have parameters that match the columns in our data provider!
    @Test(dataProvider = "sauceUsers")
    public void testMultipleLogins(String username, String password) {
        System.out.println("Attempting login with user: " + username);

        // USE getDriver() HERE
        getDriver().get(ConfigReader.getProperty("url"));

        // USE getDriver() HERE
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.loginToApplication(username, password);

        // USE getDriver() HERE
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed for user: " + username);
    }
}