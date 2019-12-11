Feature: Online Purchases 

  Scenario Outline: 1- Navigate and Validate Landing Page
    Given I have navigated to Amazon
     When Landing Page loads
     Then click Login
     When Amazon asks for details
     Then enter <Username> and <Password>
  #Then click Submit
    Examples: 
      | Username  | Password | 
      | "AmaUser" | 12345    | 