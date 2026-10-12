Feature: Wedding navigation and filters on Petal & Pup

  @wedding
  Scenario: Hover Wedding, open For the Bride, apply White filter and view results
    Given I launch the browser
    When I navigate to the Petal & Pup website
    And I hover over the Wedding menu
    And I click For the Bride link
    And I close the popup if it appears
    And I click on Filters
    And I check the White filter checkbox
    And I click on View results
    Then the results page should show white products
    And I close the browser