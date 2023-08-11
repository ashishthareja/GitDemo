#Author : Sneha and Hima
@UserGet
Feature: User GET Requests for valid and invalid scenarios

  @User_Get_01
  Scenario: Check if user able to retrieve all users with valid LMS API
    Given User creates GET Request for the LMS API All User endpoint
    When User sends Https Request
    Then User recieves "200" OK Status

  @User_Get_02
  Scenario Outline: Verify user able to retrieve a record with valid UserID
    Given User send GET Request for the LMS API endpoint with valid User ID
    When User GET request with valid UserID from "<Sheetname>" and "<Testcase>"
    Then User gets "200" Status code in response body.

    Examples: 
      | Sheetname | Testcase |
      | User      | userIdGet|

  @User_Get_03
  Scenario Outline: Check if user able to retrieve a user with invalid User ID
    Given User creates GET Request for the LMS API endpoint with invalid User ID
    When User create GET request with invalid UserID from "<Sheetname>" and "<Testcase>"
    Then User receives "404" Not Found Status with message

    Examples: 
      | Sheetname | Testcase      |
      | User      | invalidUserId |

  @User_Get_04
  Scenario: Check if user able to retrieve staff with valid LMS API
    Given User creates GET Request for the LMS API All Staff endpoint
    When User sends HTTPS Request to get all staff
    Then User recieve "200" OK Status with response body.

  @User_Get_05
  Scenario: Check if user able to retrieve a user with roles valid LMS API
    Given User creates GET Request for the LMS API User Roles endpoint
    When User sends HTTPS Requestto get roles
    Then User receives "200" Status code with response body.

  @User_Get_06
  Scenario: Check if user able to retrieve all users with invalid LMS API
    Given User creates GET Request for the LMS API All User endpoint with invalid endpoint
    When User sends get Https Request
    Then User recieves "404" status code with not found error message

  @User_Get_07
  Scenario: Check if user able to retrieve a user with roles invalid LMS API
    Given User creates GET Request for the LMS API User Roles with invalid endpoint
    When User sends HTTPS Request to get roles using invalid endpont
    Then User receives "404" Status code with not found message in response body.