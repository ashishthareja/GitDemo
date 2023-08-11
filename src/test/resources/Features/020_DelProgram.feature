@DELETEPROGRAMMODULE

Feature: Program DELETE request

@DeleteByProgramID
Scenario: Verify User able to delete program with valid program ID
	Given  User create delete request for program module with valid endpoint and valid program ID
	When User sends DELETE Http request from valid program ID 
	Then User sucessfully receives status code "200" with valid reponse body for delete request

@DeleteByProgramID404
Scenario: Verify User able to delete the program with valid program ID
	Given User able to delete request for program module with valid endpoint and valid program ID
	When User sends DELETE http request data from valid program ID 
	Then User sucessfully receives status code "404" with valid reponse body for delete
	
	
@DeleteByProgramname
Scenario Outline: Verify User able to delete program with valid program name
	Given  User create delete request for program module with valid endpoint and valid program name
	When User sends DELETE Http request data from valid program ID for from "<SheetName>" and "<Testcase>"
	Then User sucessfully receives status code "200" with valid reponse for delete request
	
	Examples:
       | SheetName | Testcase			|
       | Program | DELETEBYNAME | 
       
       
       
  @DELETE404
  Scenario Outline: Verify User able to delete program with valid endpoint and invalid program name
  	Given User create delete request for program module with valid endpoint and invalid program name
  	When User sends DELETE Http request from "<SheetName>" and "<Testcase>"
  	Then User should receives status code "404" Not found request
  	
  	 Examples:
       | SheetName | Testcase			|
       | Program | DELETEBYNAME |
