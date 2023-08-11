#Author: your.email@your.domain.com

Feature: Submission DELETE Module

 Scenario Outline: Verify user able to retrieve submission with valid submissionId
     Given User send GET Request for the LMS API endpoint with valid submission submissionId 
     When User create GET request with valid  submissionId from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission submissionId
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | submissionId    | 
       
Scenario Outline: Verify user able to retrieve submission with invalid submissionId 
     Given User send GET Request for the LMS API endpoint with invalid submissionId 
     When User create GET request with invalid  submissionId from "<Sheetname>" and "<Testcase>" 
     Then User receives "400" Status for invalid submission submissionId
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | invalidsubmissionId    |

Scenario Outline: Verify user able to retrieve submission with empty submissionId
     Given User send GET Request for the LMS API endpoint with empty submissionId 
     When User create GET request with empty  submissionId from "<Sheetname>" and "<Testcase>" 
     Then User receives "404" OK Status for empty submission submissionId
     
Scenario Outline: Verify user able to retrieve submission with invalid url and valid submission Id
     Given User send GET Request for the LMS API endpoint with invalid url and valid submissionId 
     When User create GET request with invalid url and valid submissionId from "<Sheetname>" and "<Testcase>" 
     Then User receives "400" Status for invalid url valid submissionId
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | submissionId    | 
  
 