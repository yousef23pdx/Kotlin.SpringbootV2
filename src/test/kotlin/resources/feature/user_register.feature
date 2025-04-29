Feature: User Registration

  Scenario: Successful user registration
    Given I have a user with name "Ali", age 23, username "Ali123", and password "password123"
    When I send a POST request to "/users/v1/register"
    Then I should receive a 200 response

#  Scenario: List all users
#    When I send a GET request to "/users/v1/list"
#    Then I should receive a 200 response