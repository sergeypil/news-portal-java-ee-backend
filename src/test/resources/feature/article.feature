Feature: Testing a REST API
  Users should be able to submit GET and POST requests to a web service,
  represented by WireMock

  Scenario: Data Upload to a web service
    When user uploads article
    Then the server should handle it and return a success status

  Scenario: Data retrieval from a web service
    When user wants to get information on article with id 4
    Then the requested data is returned