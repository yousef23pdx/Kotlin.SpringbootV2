Feature: Menu Retrieval

  Scenario: Get all menu items
    When I request the menu
    Then I should receive a list of menu items