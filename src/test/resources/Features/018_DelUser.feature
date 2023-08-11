@tag
Feature: User - delete request
  
  # UserId
  @User_Delete_01 
  Scenario: Check if user able to delete a user with valid User Id
    Given User creates DELETE Request for the LMS API endpoint  and  valid user ID
    When User sends Delete HTTPS Request
    Then User receives "200" Ok status with message for Delete request with valid userId

# Invalid UserId
   @User_Delete_02 
   Scenario:  Check if user able to delete a user with valid LMS API,invalid user Id
   Given User creates DELETE Request for the LMS API endpoint  and  invalid {user ID}
   When User sends Delete HTTPS Request with invalid userID
   Then User gets "404" Not Found Status with message