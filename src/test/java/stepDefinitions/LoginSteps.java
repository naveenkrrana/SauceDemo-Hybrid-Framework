package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginSteps extends BaseTest {

    private LoginPage loginPage;

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_the_saucedemo_login_page() {
        // 1. Navigate to the URL
        getDriver().get(ConfigReader.getProperty("url"));
        // 2. Initialize the Page Object
        loginPage = new LoginPage(getDriver());
    }

    // Notice the {string} syntax! Cucumber automatically extracts "standard_user"
    // from your feature file and passes it into the username variable.
    @When("the user enters the username {string} and password {string}")
    public void the_user_enters_the_username_and_password(String username, String password) {
        loginPage.loginToApplication(username, password);
    }

    // We don't have a click step in our Page Object yet, because loginToApplication
    // does it all. So we can just leave this method blank, or add a print statement!
    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        System.out.println("Login button was clicked as part of the previous step.");
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "User was NOT redirected to the inventory page!");
    }
}