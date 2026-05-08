Feature: Backend API User Management
  As a backend system,
  I want to interact with the Users API
  So that I can retrieve and validate database records.

  Scenario: Retrieve an existing user profile via API
    Given the API endpoint for user profile "2" is ready
    When I send a GET request to the endpoint
    Then the API response status code should be 200
    And the response should contain the user's name "Ervin Howell"