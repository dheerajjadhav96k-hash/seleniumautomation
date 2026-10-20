Feature: Login functionality

  Scenario: Login with valid credentials
    Given the user is on the login page
    When the user enters username "standard_user"
    And the user enters password "secret_sauce"
    And the user clicks the login button
    Then the products page should be displayed