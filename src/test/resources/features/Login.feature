Feature: User Authentication
  As a customer of SauceDemo,
  I want to log into the application
  So that I can purchase merchandise.

  # Changing "Scenario" to "Scenario Outline" tells Cucumber to expect a data table
  Scenario Outline: Successful Login with Valid Credentials
    Given the user is on the SauceDemo login page
    # Notice we replaced the hardcoded strings with <variable_names>
    When the user enters the username "<username>" and password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the inventory page

    # This table replaces your TestNG @DataProvider
    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |