package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public static RequestSpecification getBaseSpec() {
        // 1. Read the secure token from the properties file
        String bearerToken = ConfigReader.getProperty("api_token");

        return new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                // 2. Inject the Auth Header. The industry standard format is "Bearer <token>"
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
    }
}