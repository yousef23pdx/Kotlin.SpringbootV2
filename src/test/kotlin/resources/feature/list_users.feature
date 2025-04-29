Feature: List Users with Authentication

  Scenario: Successfully list users using Global JWT Token
    When I send an authenticated GET request to "/users/v1/list"
    Then I should receive a 200 response for listing users