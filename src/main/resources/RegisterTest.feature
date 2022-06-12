Feature: Testing Register Functionality

  Scenario Outline: Periksa fitur register akun
    Given browser dibuka
    And user berada di halaman register
    When user memasukan "<email>" dan "<password>"
    And user menekan "<tombol>"
    Then tampilkan "<page>"
    Examples:
      | email | password | tombol | page |
      | example@email.com | password | submit | log in |
      | example@email.com | password | reset | create |


