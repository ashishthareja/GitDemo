#Author: Monika

@GETREQUEST
Feature: Program GET request

  @GETALL_01
  Scenario: Verifying user able to retrive all program with valid endpoint and request body
    Given User create get request for program module with valid endpoint
    When User sends GET Http request data 
    Then User sucessfully receives status code "200" with valid reponse body
    
  @GETPROGRAMID_02 
  Scenario Outline: user able to retrive a program with valid program ID and endpoint
  Given User set GET request for program module with valid endpoint for program ID
  When User GET Http request by program ID from from "<Sheetname>" and "<Testcase>" 
  Then User sucessfully receives status code "200" for valid program ID
  
   Examples:
       | Sheetname | Testcase			|
       | Program   | ProgramID    | 
    
     @GETPROGRAMID_03
    Scenario Outline: Verifying user able to retrive a program with invalid program ID and endpoint
    Given User Get request for programID  
    When User set GET  request for invalid program ID from "<Sheetname>" and "<Testcase>"
    Then User receives status code "404" not found
    
     Examples:
       | Sheetname | Testcase			|
       | Program   | InvalidProgramID    | 