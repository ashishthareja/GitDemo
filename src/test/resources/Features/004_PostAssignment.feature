#Author: Diksha

@assignmentPOST
Feature: Assignment POST METHOD
 
  @assignment_POST_01
 Scenario Outline: Verify POST request to post data into branch with valid base URL and valid data
    Given User sets request for assignment module with valid base URL and valid request body
    When User sends POST request for assignment with valid URL and data from "<SheetName>"
    Then Request should be successfull with status code "201" Post request valid URL
    
    Examples: 
      | SheetName    | 
      |	Assignment	 |
      
   @assignment_POST_02
  Scenario Outline: Verify POST request to post data into branch with valid base URL and valid data(existing value)
    Given User set POST Request for the LMS API endpoint
    When User sends POST request for assignment with valid URL and existing data from "<SheetName>"
    Then User receives "400" Bad Request Status with message and boolean success details

    Examples: 
      | SheetName    | 
      |	Assignment	 |
      
    @assignment_POST_03  
   Scenario Outline: Check if user able to add a record missing mandatory fields in request body
    Given User creates POST Request for the LMS API endpoint
    When User sends POST request for assignment with valid URL and some data missing from "<SheetName>"
    Then User receives "400" Bad Request Status
    
    Examples: 
      | SheetName    | 
      |	Assignment   |
     
