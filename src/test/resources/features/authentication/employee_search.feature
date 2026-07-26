@login
Feature: Employee Search

  Scenario: Verify employee can be searched successfully
   
    Given User is on Login page
    When User logs in using "ValidLogin"
    And User navigates to the Employee List page
    And User searches employee "Paul Collings"
    Then Employee should be displayed in the search result
    
    