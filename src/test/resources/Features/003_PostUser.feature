@tag
Feature: User_Post Request

@User_Post_01
  Scenario Outline: create a User with valid endpoint and request body (Admin UserId)
    Given User sets POST request for User module with valid base URL and valid request
    When User POST request with data from "<SheetName>"
    Then Request is successful with status code "201" for POST request

    Examples: 
      | SheetName |
      | User      |
      
      
   @User_Post_02
  Scenario Outline: Check if user able to create a User with valid endpoint and request body ( Staff UserId)
    Given User sets request for User module with valid base URL and valid request body for staff
    When User sends POST request with data from "<SheetName>" for staff
    Then Request should be successful with status code "201" for POST request for staff

    Examples: 
      | SheetName |
      | User      |
    
    @User_Post_03  
    Scenario Outline: Check if user able to create a User with valid endpoint and request body ( Student UserId)
    Given User sets request for User module with valid base URL and valid request body for student
    When User sends POST request with data from "<SheetName>" for student
    Then Request should be successful with status code "201" for POST request for student

    Examples: 
      | SheetName |
      | User      | 
      
    @User_Post_04 
   Scenario Outline: Create a User with valid endpoint and request body (existing values in phone no)
    Given User sets request for User module with exisitng values
    When User sends POST request with existing phone number
    Then User gets unsuccessful status code "400" for POST request
    
    @User_Post_05
     Scenario Outline: Check if user able to create a User with valid endpoint and request body (missing mandatory fields)
    Given User sets POST request for User module with valid base URL and invalid request body
    When User sends POST request with missing data fields from the "<SheetName>" and "<Testcase>"
    Then Request should be unsuccessful with status code "400" for POST request
    
    Examples: 
      | SheetName      | Testcase |
      | UserPOST1      | missingFields |

      
        
