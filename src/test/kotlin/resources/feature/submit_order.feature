Feature: Submit Order

  Scenario: Successfully submit an order with item IDs
    When I submit an order with itemIds to "/orders/v1"
    Then I should receive a 200 response for order submission