Feature: As a shopper i want to be able to return to the shop after I make my purchase so that I can keep shopping
  Background: Logged in starting from the confirmation page
    Given I am logged in
    And I am on the cart page
    And I am on the checkout step 1 page
    And I am on the checkout step 2 page
    And I am on the confirmation page

  Scenario: Going to the inventory page from the confirmation page
    When I click on back home
    Then I will be on the inventory page