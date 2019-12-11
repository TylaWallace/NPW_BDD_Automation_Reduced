
Feature: Choose a Goal


    Scenario Outline: I want to create a new goal from my dashboard
        Given as a "<USERTYPE>" user "<USERNAME>" "<PASSWORD>"
        And I navigate to "DASHBOARD" page
        When I set a "<GOAL>" goal
        Then Robo Advisor Initiates

    Examples:
    |USERTYPE|USERNAME|PASSWORD|GOAL|
    |CUR-NG|MARK|MYPASSWORD|EDUCATION|
    |NGINED|JOHN|MYPASSWORD|PERSONAL|