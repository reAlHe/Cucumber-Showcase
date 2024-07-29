Feature: Heroku App

  @browser
  Scenario Outline: Test login functionality on various browsers
    Given I am using the "<browser>" browser
    And I am on the Heroku Form Authentication Page
    When I login with username "<username>" and password "<password>"
    Then I should see the login success message

    @firefox
    Examples: Firefox
      | browser | username | password             |
      | Firefox | tomsmith | SuperSecretPassword! |

    @chrome
    Examples: Chrome
      | browser  | username | password             |
      | Chrome | tomsmith | SuperSecretPassword! |
