#Author: Diksha

@assignmentPUT
Feature: Assignment PUT Method
  
  @Assignment_PUT_001
   Scenario: User PUT Request for the LMS API endpoint and Valid Assignment Id
    Given User set PUT Request for the LMS API endpoint and Valid Assignment Id
    When User sends HTTPS Request and request Body  
    Then User receives "200" OK Status with updated value in response body
    
   @Assignment_PUT_002   
   Scenario: Check if user able to update a record with invalid Assignment ID
    Given User creates PUT Request for the LMS API endpoint and invalid Assignment ID
    When User sends HTTPS Request and fields
    Then User receives "404" Not Found Status
 
        
  @Assignment_PUT_003
  Scenario: Check if user able to update a record with valid AssignmentID and missing mandatory fields
    Given User PUT Request with Valid Assignment Id
    When User sends HTTPS Request with missing mandatory fields 
    Then User  "400" Bad Request Status 

    