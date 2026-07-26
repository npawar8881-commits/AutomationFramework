# Feature Login=As a valid application user,I want to log into the application,So that I can access the Home Page
# Describes a application business functionality.oneFeature file represent one business functionality

# Scenario Outline-Executes the same scenario multiple times using different test data.
# Data is supplied through the Examples table.
# Given-Describes the initial state (Pre-condition)."What is the starting point?"
# When-Describes an action performed by the user."What action does the user perform?"
# Then-Describes the expected outcome after performing the action."What should happen?"
# Examples-Supplies test data to Scenario Outline.Each row executes the complete scenario once.

# NEW:
# Test data is maintained in LoginTestData.xlsx.
# Feature file contains only business behaviour.

@login
Feature: Login

Scenario: Verify successful login with valid credentials

    Given User is on Login page
    When User logs in using "ValidLogin"
    Then User should be logged in successfully
    
    
    
    
Scenario: Verify login with invalid password

    Given User is on Login page
    When User logs in using "InvalidPassword"
    Then Error message should be displayed
    
    
    
 
Scenario: Verify login with invalid username

    Given User is on Login page
    When User logs in using "InvalidUsername"
    Then Error message should be displayed

  Scenario: Verify login with invalid username and password

    Given User is on Login page
    When User logs in using "InvalidCredentials"
    Then Error message should be displayed
    
    
    