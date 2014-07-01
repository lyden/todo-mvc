Feature: Add Todo
As a user
In order to be reminded of something in the future
I want to set a todo

  Scenario: Successfully adding a todo
    Given I am on the main todos page
    When I enter "Remember this" into the new todo box
    And press enter in the todo box
    Then a new todo is added with "Remember this"