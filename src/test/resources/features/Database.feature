Feature: Enterprise Database Integrity
  As a backend data auditor,
  I want to verify database records,
  So that I can ensure data is saved correctly.

  Scenario: Validate an employee's role in the database
    Given the backend database is initialized with employee data
    When I query the database for the employee named "Naveen"
    Then the database should return the role "Senior QA"