@PROGRAMPUT

Feature: Program PUT request

	Scenario Outline: User able to update program with valid ProgramID and request body
	Given User create PUT request for program module with valid endpoint and programID
	When User sends PUT http request and request body from "<Sheetname>" and "<Testcase>"
	Then User sucessfully receives status code "200" OK with valid reponse body 
	
	 Examples:
       | Sheetname | Testcase			|
       | Program   | PUTProgramID | 
       
  Scenario Outline: User able to update program with invalid programID and request body
       Given User create PUT request for program module with valid endpoint and Invalid programID
       When User sends PUT http request and request body from "<Sheetname>" and "<Testcase>" for invalid programID
       Then User receives status code "404" not found for invalid programID
       
	 Examples:
       | Sheetname | Testcase			|
       | Program   | PUTInvalidProgramID | 
  
  Scenario Outline: User able to update program with missing mandetory fields   
 			 Given User create PUT request for program module with valid endpoint and valid programID
       When User sends PUT http request and request body from "<Sheetname>" and "<Testcase>" for missing fields
       Then User receives status code "400" Bad request for missing fileds 
       	
        Examples:
       | Sheetname | Testcase			|
       | Program   | PUTBadrequest | 
      

Scenario Outline: User able to update program with valid ProgramName and request body
	Given User create PUT request for program module with valid endpoint and ProgramName
	When User sends PUT http request and request body from "<Sheetname>" and "<Testcase>" for ProgramName
	Then User sucessfully receives status code "200" OK with valid reponse body for ProgramName
	
	 Examples:
       | Sheetname | Testcase			|
       | Program   | PUTProgramName | 
       
  Scenario Outline: User able to update program with invalid ProgramName and request body
       Given User create PUT request for program module with valid endpoint and Invalid ProgramName
       When User sends PUT http request and request body from "<Sheetname>" and "<Testcase>" for invalid ProgramName
       Then User receives status code "404" not found for invalid ProgramName for ProgramName
       
	 Examples:
       | Sheetname | Testcase			|
       | Program   | PUTInvalidProgramName | 
  
  Scenario: User able to update program with missing mandetory fields   
 			 Given User create PUT request for program module with valid Missing ProgramName
       When User sends PUT http request and request body for ProgramName missing fields
       Then User receives status code "400" Bad request for missing fileds for ProgramName
