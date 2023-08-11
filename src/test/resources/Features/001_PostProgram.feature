@POSTDATADRIVEN
Feature: Creating the User Using BDD 
 
   Scenario Outline: Creation of a program with valid endpoint and request body using Data Driven
    Given User sends request for Program module with valid request body
    When User sends POST request data from "<SheetName>" and "<Testcase>"
    Then User sucessfully receives status code "201" with reponse body
    
    	 Examples:
       | SheetName | Testcase			|
       | Program | ProgramModuleData | 
       
  @POST400ERROR
  Scenario Outline: User able to create valid endpoint and request body with existing values
  	Given User create post request with valid endpoint
  	When User send the existing data for post request from "<SheetName>" and "<Testcase>"
  	Then User should receives status code "400" bad request
  	
  	 Examples:
       | SheetName | Testcase			|
       | Program | ProgramModulesameData |
  	
  	
   @POST500INTERNALERROR
  Scenario Outline: User able to create valid endpoint and request body with missing values
  	Given User create post request with valid LMS endpoint
  	When User send the missing data for post request from "<SheetName>" and "<Testcase>"
  	Then User should receives status code "500" internal server error
  	
  	 Examples:
       | SheetName | Testcase			|
       | Program | ProgramModulemissingData |