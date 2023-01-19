Feature: Test Background Feature
  Description: The purpose of this feature is to test the Background keyword

  Background: User is Logged In
    Given User open booking
    When User login with credentials "olya.linchak@mail.ru " and "!Rtygc1234"


  Scenario Outline: Search Hotel for 2 Adults and 1 child
    Given User open booking
    When User enter destination "<city>"
    And User choose check in date
    And User choose checkout date
    And User choose guests
    And User Search for results
    And User add breakfast
    Then Breakfast is included
    Examples:
      | city     |
      | Zakopane |



