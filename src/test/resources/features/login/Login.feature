Feature: Login functionality

@smoke @positive
  Scenario: Successful login
    Given I am on the OrangeHRM login page
    When I login with valid credentials
    Then I should be redirected to the dashboard

@regression @negative
  Scenario Outline: Login with different credentials
    Given I am on the OrangeHRM login page
    When I login with "<username>" and "<password>"
    Then login result should be "<result>"

    Examples:
      | username      | password   | result  |
      | Admin123         | admin123   | success |
      | wrongusername | wrongpass   | success |