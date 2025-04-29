Feature: Submit Profile

  Scenario: Successfully submit a profile
    Given I have a profile with first name "John", last name "Doe", and phone number "12345678"
    When I submit the profile to "/profile"
    Then I should receive a 200 response for submitting profile