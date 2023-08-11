Feature: User-PUT

  @user_put_01
  Scenario: Check if user able to get a user with valid User Id and request body
    Given User creates PUT Request by valid User ID for the LMS API endpoint
    When User sends PUT request with data from valid payload
    Then User gets successful statuscode "200"

  @user_put_02
  Scenario Outline: Check if user able to update a user with valid User Id and request body
    Given User creates PUT Request with the valid Base URL and valid request body
    When User sends PUT request with with valid User data from "<SheetName>" and "<Testcase>"
    Then Request should be successful with status code "200"

    Examples: 
      | SheetName | Testcase   |
      | UserPUT   | updateUser |

  @user_put_03
  Scenario Outline: Check if user able to update a user with invalid User Id and request body
    Given User creates PUT Request with the valid Base URL and invalid UserId
    When User sends PUT request with with invalid User data from "<SheetName>" and "<Testcase>"
    Then Request should be unsuccessful with status code "404"

    Examples: 
      | SheetName | Testcase    |
      | UserPUT   | InvalidUser |

  @user_put_04
  Scenario Outline: Check if user able to update a user with valid User ID and missing mandatory fields request body
    Given User creates PUT Request with the valid Base URL and valid request body
    When User sends PUT request with with valid User data and missing data fields from "<SheetName>" and "<Testcase>"
    Then Request should be unsuccessful with status code with "400"

    Examples: 
      | SheetName | Testcase      |
      | UserPUT   | MissingFields |

  @user_put_05
  Scenario Outline: Check if user able to update a user with valid User Id and request body(RoleStatus)
    Given User creates PUT Request with the valid Base URL and valid request body for RoleStatus update
    When User sends PUT request with with valid User data from "<SheetName>" and "<Testcase>" for Rolestatus update
    Then Request should be successful with status code "200"

    Examples: 
      | SheetName | Testcase   |
      | UserPUT   | roleStatus |

  @user_put_06
  Scenario Outline: Check if user able to update a user with invalid User Id and request body(RoleStatus)
    Given User creates PUT Request with the valid Base URL and valid request body for RoleStatus update
    When User sends PUT request with invalid User data from "<SheetName>" and "<Testcase>" for Rolestatus update
    Then Request should be unsuccessful with status code "404"

    Examples: 
      | SheetName | Testcase          |
      | UserPUT   | InvalidroleStatus |

  @user_put_07
  Scenario Outline: Check if user able to update a user with valid User Id and request body (Role Status missing field)
    Given User creates PUT Request with the valid Base URL and valid request body for RoleStatus update
    When User sends PUT request with valid User data from "<SheetName>" and "<Testcase>" for Rolestatus update
    Then Request should be unsuccessful with status code "400"

    Examples: 
      | SheetName | Testcase          |
      | UserPUT   | MissingroleStatus |

  @user_put_08
  Scenario Outline: Check if user able to assign user to program / batch with valid User Id and request body
    Given User creates PUT Request with the valid Base URL and valid request body with program/batchID
    When User sends PUT request with valid User data from "<SheetName>" and "<Testcase>" for program and batch
    Then Request should be successful with status code "200"

    Examples: 
      | SheetName     | Testcase        |
      | UPUTprogBatch | updateProgBatch |

  @user_put_09
  Scenario Outline: Check if user able to assign user to program / batch with invalid User Id and request body
    Given User creates PUT Request with the valid Base URL and valid request body with program/batchID
    When User sends PUT request with valid User data from "<SheetName>" and "<Testcase>" for program and batch
    Then Request should be unsuccessful with status code "404"

    Examples: 
      | SheetName     | Testcase      |
      | UPUTprogBatch | invalidUserID |

  @user_put_10
  Scenario Outline: Check if user able to assign user to program / batch with User Id and missing field
    Given User creates PUT Request with the valid Base URL and valid request body with program/batchID
    When User sends PUT request with valid User data from "<SheetName>" and "<Testcase>" with missing fields
    Then Request should be unsuccessful with status code "400"

    Examples: 
      | SheetName     | Testcase     |
      | UPUTprogBatch | missingField |
