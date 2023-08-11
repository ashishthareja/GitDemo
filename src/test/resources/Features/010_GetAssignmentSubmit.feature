Feature: Submission Get Module

@submit_get_02    
 Scenario Outline: Verify user able to retrieve submission with valid AssignmentID
     Given User send GET Request for the LMS API endpoint with valid submission Assignment ID
     When User create GET request with valid submission AssignmentID from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission assignment id
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | assignmentId    | 
       
       
@submit_get_02    
 Scenario Outline: Verify user able to retrieve submission with valid StudentID
     Given User send GET Request for the LMS API endpoint with valid submission Student ID
     When User create GET request with valid submission StudentID from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission student id
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | ValidStudentId    | 
       
@submit_get_02    
 Scenario Outline: Verify user able to retrieve submission with valid BatchID
     Given User send GET Request for the LMS API endpoint with valid submission Batch ID
     When User create GET request with valid submission Batchid from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission batch id
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | ValidBatchId    | 
       
@submit_get_02    
 Scenario Outline: Verify user able to retrieve submission with valid Userid
     Given User send GET Request for the LMS API endpoint with valid submission user ID
     When User create GET request with valid submission userid from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission user id
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | ValidUserId    | 
       
@submit_get_02    
 Scenario Outline: Verify user able to retrieve submission made with valid student BatchID
     Given User send GET Request for the LMS API endpoint with valid submission by student Batch ID
     When User create GET request with valid submission by student Batchid from "<Sheetname>" and "<Testcase>" 
     Then User receives "200" OK Status for valid submission by student batch id
   
	Examples:
       | Sheetname | Testcase			|
       | SubmitTest   | ValidBatchId    | 
       
       
 Scenario: user able to retrieve all submission with valid LMS API endpoint
    Given User creates GET Request for the LMS API endpoint 
    When User sends HTTPS requests
    Then Request should be successfull with status code "200" for GET All submission
    
 Scenario: user able to retrieve all submission with invalid url 
 		Given User creates GET Request for the invalid LMS API endpoint 
    When User sends HTTPS requests invalid url
    Then Request should be successfull with status code "400" for GET All submission invalid url
       

        
Scenario Outline: Verify user able to retrieve a record with invalid AssignmentID
    Given User send GET Request for the LMS API endpoint for submission invalid AssignmentID
    When  User create GET request for invalid AssignmentID from "<Sheetname>" and "<Testcase>"
    Then User "404" Not Found Status for invalid assignment id
    
     Examples:
        | Sheetname  | Testcase           |
        |Submit |InvalidAssignmentID |

Scenario Outline: Verify user able to retrieve a record with null AssignmentID
    Given User send GET Request for the LMS API endpoint for submission null AssignmentID
    When  User create GET request for null AssignmentID from "<Sheetname>" and "<Testcase>"
    Then User "405" Bad Request Status for null assignment id
    
     Examples:
        | Sheetname  | Testcase           |
        |Submit |NullAssignmentID |
        
       
Scenario Outline: Verify user able to retrieve a record with invalid StudentID
    Given User send GET Request for the LMS API endpoint for submission invalid StudentID
    When  User create GET request for invalid StudentID from "<Sheetname>" and "<Testcase>"
    Then User "404" Not Found Status for invalid student id
    
     Examples:
        | Sheetname  | Testcase           |
        |Submit |InvalidStudentID |
        
Scenario Outline: Verify user able to retrieve a record with null StudentID
    Given User send GET Request for the LMS API endpoint for submission null StudentID
    When  User create GET request for null StudentID from "<Sheetname>" and "<Testcase>"
    Then User "405" Not Found Status for null student id
    
     Examples:
        | Sheetname  | Testcase           |
        |Submit |InvalidStudentID |   


Scenario Outline: Verify user able to retrieve a record with invalid submission batchID 
		Given User creates GET Request for the LMS API endpoint with invalid submission BatchId
		When User create GET request with invalid submission batch ID from "<Sheetname>" and "<Testcase>"
		Then User get status "400" for invalid submission batch id
    
     Examples: 
      | Sheetname | Testcase |
      |    Submit  | InvalidBatchID  |     
      
Scenario Outline: Verify user able to retrieve a record with null submission batchID 
		Given User creates GET Request for the LMS API endpoint with null submission BatchId
		When User create GET request with null submission batch ID from "<Sheetname>" and "<Testcase>"
		Then User get status "405" for null submission batch id
    
     Examples: 
      | Sheetname | Testcase |
      |  Submit  | NullBatchID  |     
      
Scenario Outline: Verify user able to retrieve a record with invalid get submission by batchID 
		Given User creates GET Request for the LMS API endpoint with invalid get submission by BatchId
		When User create GET request with invalid get submission by batch ID from "<Sheetname>" and "<Testcase>"
		Then User get status "400" bad request for invalid get submission by batch id
    
     Examples: 
      | Sheetname | Testcase |
      |    Submit  | InvalidBatchID  |    
      
Scenario Outline: Verify user able to retrieve a record with null submission by batchID 
		Given User creates GET Request for the LMS API endpoint with null submission by BatchId
		When User create GET request with null submission by batch ID from "<Sheetname>" and "<Testcase>"
		Then User get status "405" bad requesr for null submission by batch id
    
     Examples: 
      | Sheetname | Testcase |
      |  Submit  | NullBatchID  |    

Scenario Outline: Verify user able to retrieve a record with invalid get submission by userID 
		Given User creates GET Request for the LMS API endpoint with invalid get submission by userID
		When User create GET request with invalid get submission by userID from "<Sheetname>" and "<Testcase>"
		Then User get status "400" bad request for invalid get submission by userID
    
     Examples: 
      | Sheetname | Testcase |
      |    Submit  | InvalidUserID  |    
      
Scenario Outline: Verify user able to retrieve a record with null submission by UserID 
		Given User creates GET Request for the LMS API endpoint with null submission by UserID
		When User create GET request with null submission by  UserID from "<Sheetname>" and "<Testcase>"
		Then User get status "405" bad requesr for null submission by  userid
    
     Examples: 
      | Sheetname | Testcase |
      |  Submit  | NullUserID  |   
      



 