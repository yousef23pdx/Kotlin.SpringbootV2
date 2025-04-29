Feature: Get Profile

  Scenario: Successfully retrieve profile
    Given A profile is already created for user "GlobalUser"
    When I send an authenticated GET request to "/profile" to retrieve profile
    Then I should receive a 200 response for retrieving profile