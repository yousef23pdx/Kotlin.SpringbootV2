Feature: Submit Item
  Scenario: Successfully submit a new item
    Given I have an item with name "Burger", quantity 2, note "No onions", and price 3.5
    When I submit an item to endpoint "/submitItems"
    Then I should receive a 200 response for submitting item