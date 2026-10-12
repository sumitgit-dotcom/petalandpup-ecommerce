Feature: Open Petal & Pup Website

  @smoke
  Scenario: Open the homepage and verify it loads
    Given I launch the browser
    When I navigate to the Petal & Pup website
    Then the page title should contain "Petal"
    And the current URL should contain "petalandpup.com"
    And I close the browser