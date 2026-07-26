Feature: Employee Search

  Scenario: Verify employee can be searched successfully

    Given User logs into the application
    When User navigates to the Employee List page
    And User searches employee "Paul Collings"
    Then Employee should be displayed in the search result