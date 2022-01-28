Feature: Testing a REST API

  Scenario: Client makes a call to POST /api/v1/publishData
    When the client calls /api/v1/publishData
    Then the client receives the status code of 201

