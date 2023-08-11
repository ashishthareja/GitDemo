#Authur : Anandhi
@tag
Feature: Submission PUT Module
  

 Scenario Outline: Check if user able to update a Submission with valid submission id and mandatory request body
    Given User sets request for submission module with valid submission id and valid request body
    When User sends POST request with data from "<SheetName>" and "<Testcase>" with valid submission id 
    Then Request should be successfull with status code "200" for PUT request valid submission id 

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |
     
Scenario Outline: Check if user able to update a Submission with invalid submission id and mandatory request body
    Given User sets request for submission module with invalid submission id and valid request body
    When User sends request with data from "<SheetName>" and "<Testcase>" with invalid submission id 
    Then Request should be successfull with status code "400" for PUT request invalid submission id 

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |
   
Scenario Outline: Check if user able to update a Submission with valid submission id and missing mandatory request body
    Given User sets request for submission module with valid submission id and missing madatory request body
    When User sends request with data from "<SheetName>" and "<Testcase>" with valid submission id and missing mandatory request body
    Then Request should be successfull with status code "400" for PUT request valid submission id and missing mandatory request body

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |
    
      

Scenario Outline: Check if user able to update grades Submission with invalid submission id and mandatory request body
    Given User sets request for grade submission module with invalid submission id and valid request body
    When User sends PUT request with data from "<SheetName>" and "<Testcase>" with invalid submission id for grade update
    Then Request should be successfull with status code "400" for grade submission invalid id

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |
      
Scenario Outline: Check if user able to update grade Submission with valid submission id and missing mandatory request body
    Given User sets request for grade submission module with valid submission id and missing madatory request body
    When User sends PUT request with data from "<SheetName>" and "<Testcase>" with valid submission id and missing mandatory request body for grade update
    Then Request should be successfull with status code "400" for PUT request grade submission id

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |
      
Scenario Outline: Check if user able to update grades Submission with valid submission id and mandatory request body
    Given Validate User sets request for grade submission module with valid submission id and valid request body
    When Validate User sends PUT request with data from "<SheetName>" and "<Testcase>" with valid submission id for grade update
    Then Validate Request should be successfull with status code "200" for grade submission

    Examples: 
      | SheetName | Testcase |
      | SubmitTest| Testcase |    