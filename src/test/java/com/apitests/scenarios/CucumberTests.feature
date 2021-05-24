@ApiTests
Feature: Test API with Cucumber

  Scenario: Get some page
    Given page id is 15945
    When user try to get page by id
    Then user receive status code 200
    And response don't equal zero

  Scenario: Get list of users
    Given user try to get list of all users
    When user try to get response
    Then user receive status code 200
    And list of all users not null


  Scenario: Create new post
    Given user try post with next data:
      | userId | title          | body  |
      | 1      | Vary important | Hello |
    When user try to create new post
    Then user receive status code 404
    And response don't equal zero


  Scenario: Login with correct data
    Given user try to login with next email "tsake.love@gmail.com"
    When user try to login
    Then user logged successfully
    And response don't equal zero

  Scenario: Login with incorrect data
    Given user try to login with next email "incorrect.data@gmail.com"
    When user try to login
    Then user logged successfully
    And response equal zero


