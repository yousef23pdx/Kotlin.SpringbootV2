Feature: List Items
  Scenario: Successfully retrieve items
    When I send a GET request to "/listItems"
    Then I should receive a 200 response for listing items