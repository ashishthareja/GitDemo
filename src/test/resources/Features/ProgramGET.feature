
@tag
Feature: Program GET request

  @tag1
  Scenario: Check if user able to create a program with valid endpoint and request body (non existing values)
    Given User sets request for Program module with valid base URL
    When User sends GET request 
    Then Request should be successfull with status code "200" for GET All programs

 