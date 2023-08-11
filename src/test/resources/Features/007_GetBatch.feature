#Author : Vimala

@BatchGet
Feature: Batch module API testing

  @BatchGet_01
  Scenario: Verify GetAll Batches with valid status code and schema
    Given User verify  batch module url is set with valid url
    When User GET All batches request
    Then User  success "200" status code and verify collection schema

  @BatchGet_02
  Scenario Outline: Verify Get Batche by BatchID with valid BatchID
    Given User verify Get Batches by valid BatchID 
    When User set GET request with valid batchID from "<Sheetname>" and "<Testcase>"
    Then User get "200" status code 

    Examples: 
      | Sheetname | Testcase |
      | Batch     | BatchID  |

  @BatchGet_03
  Scenario: Verify Get Batches By BatchID for invalid BatchID
    Given User GET request for Batch module with valid base URL and valid path
    When User GET request with invalid batchID from "<Sheetname>" and "<Testcase>"
    Then Verify batches API returns "404" status code and with message not found
    
  @BatchGet_04
  Scenario Outline: Verify Get Batches by BatchName with valid BatchName
    Given Userverify request for BatchName in Batch module with valid base URL and valid Path
    When User GET request with valid batchName from "<Sheetname>" and "<Testcase>"
    Then Verify batches api returns single batchname success "200" status code

    Examples: 
      | Sheetname | Testcase  |
      | Batch     | BatchName |

  @BatchGet_05
  Scenario: Verify Get Batches By BatchName for invalid BatchName
    Given User with valid base URL and valid path
    When User send GET request with invalid batchName
    Then Verify batches API returns "404" status code 
    

  @BatchGet_06
  Scenario Outline: Verify Get Batches with valid ProgramId
    Given User verify in Batch module wih valid ProgramId 
    When User  set GETrequest with valid ProgramId from "<Sheetname>" and "<Testcase>"
    Then User get single batchname success "200" status code and verify schema

    Examples: 
      | Sheetname | Testcase  |
      | Batch     | ProgramID |
      
  @BatchGet_07
  Scenario: Verify Get Batches for invalid ProgramId
    Given User verify in Batch module wih Invalid ProgramId 
    When User  set GETrequest with invalid ProgramId
    Then User  "404" status code and with message not found    
      