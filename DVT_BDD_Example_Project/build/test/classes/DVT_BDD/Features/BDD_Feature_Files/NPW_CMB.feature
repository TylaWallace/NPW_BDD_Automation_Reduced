Feature: CMB

  Scenario Outline: 1- Positive Validate CMB Popup
    Given I have navigated to CMB Option
     When CMB Option Name field loads
     Then enter <Name> at CMB Name field
     When CMB Option Surname field loads
     Then enter <Surname> at CMB Surname field
     When CMB Option Contact Number Field loads
     Then enter <ContactNo> at CMB Contact Number field
     Then CMB button is active
     When clicking CMB Button
     Then CMB Thank You message is displayed
  
    Examples: 
      | Name         | Surname     | ContactNo    |
      | "Testy-Lee!" | "NpwA'uto" | "0711110011" |