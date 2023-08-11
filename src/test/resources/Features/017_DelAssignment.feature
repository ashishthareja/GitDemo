#Author: Diksha

@assigmentDELETE
Feature: DELETE REQUEST by Assignment ID
  
  @Assignment_DELETE_01
  Scenario: Check if user able to delete a record with valid Assignment ID
    Given User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id
    When User set HTTPS Request for the LMS API endpoint
    Then User receives "200" Ok status with message
    
    @Assignment_DELETE_02
  Scenario: Check if user able to delete a record with valid LMS API,invalid Assignment Id
    Given User creates DELETE Request for the LMS API endpoint  and  invalid  Assignment Id
    When User sends HTTPS Request  
    Then User receives "404" Not Found Status with message and boolean success details

	