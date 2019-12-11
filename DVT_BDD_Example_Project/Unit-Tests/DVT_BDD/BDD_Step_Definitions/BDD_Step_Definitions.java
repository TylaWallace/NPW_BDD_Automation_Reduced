/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVT_BDD.BDD_Step_Definitions;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.TestClasses.BDDExampleTest;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.AppiumDriverUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import DVT_BDD.RunBDDScripts;
import java.io.IOException;
import static org.junit.Assert.fail;
/**
 *
 * @author twallace
 */
@StepDefAnnotation
public class BDD_Step_Definitions extends BaseClass 
{
    
    BDDExampleTest BDDMethodClass;

    public BDD_Step_Definitions()
    {
        //add test classes to be used in scenario
        BDDMethodClass = new BDDExampleTest();
        
    }
    
    @Before
    public void before(Scenario scenario)
    {
        try 
        {
            
            narrator = new Narrator();
            
            //start driver
            if(!SeleniumDriverInstance._isDriverRunning)
                SeleniumDriverInstance.startDriver();
            
            
            String scenarioName = narrator.formatMessage(scenario.getName());

            testCaseId =  scenarioName + "_" + scenarioCount++;
            
        } 
        catch (Exception ioe) 
        {
            System.err.println("[ERROR] - Failed to read/write scenario count setup file - " + ioe.getMessage());
        }
    }
    

    //BDD EXAMPLE STEPS--------------------------------------
    
    @Given("^I have navigated to Amazon$")
    public void i_have_navigated_to_Amazon()
    {
        this.checkTestResult(BDDMethodClass.GivenIHaveNavigatedToAmazon());
    }

    @When("^Landing Page loads$")
    public void landing_Page_loads()
    {
        this.checkTestResult(BDDMethodClass.WhenLandingPageLoads());
    }

    @Then("^click Login$")
    public void click_Login()
    {
        this.checkTestResult(BDDMethodClass.ThenClickLogin());
    }

    @When("^Amazon asks for details$")
    public void amazon_asks_for_details()
    {
        this.checkTestResult(BDDMethodClass.WhenAmazonAsksForDetails());
    }

    @Then("^enter \"([^\"]*)\" and (\\d+)$")
    public void enter_and(String arg1, int arg2)
    {
        this.checkTestResult(BDDMethodClass.ThenEnterAnd(arg1, arg2));
    }
    //------------------------------------------------------

    @After
    public void After()
    {
        SeleniumDriverInstance.shutDown();
    }
    
    public void checkTestResult(String result)
    {
        if(result != null) //failed
        {   //take screenshot, add to report
            narrator.testFailedCucumber(result);
            fail(result);
        }
    }
    
}
