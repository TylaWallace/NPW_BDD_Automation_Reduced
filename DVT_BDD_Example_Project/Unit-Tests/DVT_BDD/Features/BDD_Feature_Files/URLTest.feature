Feature: URL Testing Feature

    Scenario Outline: 1- Navigate to a URL and Validate all the links present
        Given I have navigated to "<URL>"
        Then get the list of the links present on the page.
        Then click on each link and verify the landing page.
        
Examples: 
      | URL                                  |  
      | https://www.toolsqa.com/             | 
      | https://www.phptravels.net/index.php |