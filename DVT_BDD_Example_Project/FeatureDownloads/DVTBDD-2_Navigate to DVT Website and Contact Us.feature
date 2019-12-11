
Feature: Navigate to DVT Website and Contact Us


    Scenario Outline: to navigate to the DVT website and fill in the Contact Us form
        Given as user on DVT website
        And I navigate to DVT website Contact Us page
        When I fill in the contact us enquiry form : name "<NAME>" surname "<SURNAME>" phone no "<PHONE_NO>"

    Examples:
    |NAME|SURNAME|PHONE_NO|
    |Trent|Richardson|111111111|
    |Milesh|Jivan|222222222|
    |Tyla|Wallace|333333333|