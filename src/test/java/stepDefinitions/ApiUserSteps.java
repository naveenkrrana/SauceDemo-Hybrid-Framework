package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.ApiConfig;

public class ApiUserSteps {

    // These variables hold the state of our API transaction between Cucumber steps
    private RequestSpecification request;
    private Response response;
    private String endpoint;

    @Given("the API endpoint for user profile {string} is ready")
    public void the_api_endpoint_for_user_profile_is_ready(String userId) {
        // 1. Grab the base configuration we just built
        request = RestAssured.given().spec(ApiConfig.getBaseSpec());

        // 2. Define the specific route we are targeting
        endpoint = "/users/" + userId;
    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        // 3. Execute the HTTP GET command and store the server's reply in the Response object
        response = request.when().get(endpoint);

        // Optional: Print the raw JSON response to the console so we can see the data
        System.out.println("API Response: " + response.getBody().asPrettyString());
    }

    @Then("the API response status code should be {int}")
    public void the_api_response_status_code_should_be(Integer expectedStatusCode) {
        // 4. Extract the physical HTTP status code from the response and validate it
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode.intValue(), "Status code mismatch!");
    }

    @And("the response should contain the user's name {string}")
    public void the_response_should_contain_the_user_s_name(String expectedName) {
        // Removed "data.first_name" and replaced it with just "name" based on the new API structure
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName, "Name mismatch in database!");
    }
}