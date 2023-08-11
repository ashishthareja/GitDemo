#Author: Anandhi


Feature: Submission POST module
         
Scenario Outline: Check if user able to create a Submission with invalid url and valid request body (non existing values)
    Given User sets request for submission module with invalid base URL and valid request body
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module invalid url
    Then Request should be successfull with status code "404" for POST request submission module invalid url

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |
      
      
    Scenario Outline: Check if user able to create a Submission with valid endpoint and request body (non existing values)
    Given User sets request for submission module with valid base URL and valid request body
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module
    Then Request should be successfull with status code "201" for POST request submission module

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |
    
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body (existing values)
    Given User sets request for submission module with valid base URL and valid request body for exisiting value
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module exisiting value
    Then Request should be successfull with status code "400" for POST request submission module existing value

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |
   
  
Scenario: Check if user able to submit with valid endpoint and empty request body 
    Given User sets request for submission with valid url and empty request body 
    When User sends post requests with empty request body
    Then Request should be  with status code "400" for empty request body
    
Scenario Outline: Check if user able to create a Submission with valid endpoint and missing mandatory field (assignment id)
    Given User sets request for submission module with valid base URL and missing mandatory field (assignment id)
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module mandatory field (assignment id)
    Then Request should be successfull with status code "400" for submission module mandatory field (assignment id)

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |

Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with no assignment id
    Given User sets request for submission module with valid base URL and valid request body with no assignment id
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with no assignment id
    Then Request should be successfull with status code "400" for POST request submission module with no assignment id

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with no user id
    Given User sets request for submission module with valid base URL and valid request body with no user id
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with no user id
    Then Request should be successfull with status code "400" for POST request submission module with no user id

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with no submission datetime
    Given User sets request for submission module with valid base URL and valid request body with no submission datetime
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with no submission datetime
    Then Request should be successfull with status code "400" for POST request submission module with no submission datetime

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with no grade
    Given User sets request for submission module with valid base URL and valid request body with no grade
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with no grade
    Then Request should be successfull with status code "400" for POST request submission module with no grade

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with grade greater
    Given User sets request for submission module with valid base URL and valid request body with grade greater
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with grade greater
    Then Request should be successfull with status code "400" for POST request submission module with grade greater

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with diff submission datetime format
    Given User sets request for submission module with valid base URL and valid request body with diff submission datetime format
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with diff submission datetime format
    Then Request should be successfull with status code "400" for POST request submission module with diff submission datetime format

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |  
      
Scenario Outline: Check if user able to create a Submission with valid endpoint and request body with greater than due date
    Given User sets request for submission module with valid base URL and valid request body with greater than due date
    When User sends POST request with data from "<SheetName>" and "<Testcase>" for submission module with greater than due date
    Then Request should be successfull with status code "400" for POST request submission module with greater than due date

    Examples: 
      | SheetName | Testcase        |
      | SubmitTest      | Testcase       |   