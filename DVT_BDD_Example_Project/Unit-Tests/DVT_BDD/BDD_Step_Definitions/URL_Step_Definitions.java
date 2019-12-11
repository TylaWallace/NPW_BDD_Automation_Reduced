/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVT_BDD.BDD_Step_Definitions;


import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Reporting.Narrator;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.runtime.java.StepDefAnnotation;
import KeywordDrivenTestFramework.Testing.TestClasses.URL_Step;
import KeywordDrivenTestFramework.Testing.TestClasses.URL_Step_PreDEV;
import static org.junit.Assert.fail;

/**
 *
 * @author XY59239
 */
@StepDefAnnotation
public class URL_Step_Definitions extends BaseClass 
{
     URL_Step URL_Step_MethodClass;
     URL_Step_PreDEV URL_Step_MethodClass_PreDEV;

public URL_Step_Definitions()
{
    URL_Step_MethodClass = new URL_Step();
    URL_Step_MethodClass_PreDEV = new URL_Step_PreDEV();
    
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
@Given("^I have navigated to \"([^\"]*)\"$")
public void i_have_navigated_to(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
     this.checkTestResult(URL_Step_MethodClass_PreDEV.GivenIHaveNavigatedToURL(arg1));
}


@Then("^get the list of the links present on the page\\.$")
public void  get_the_list_of_the_links_present_on_the_page()  {
    // Write code here that turns the phrase above into concrete actions
    this.checkTestResult(URL_Step_MethodClass_PreDEV.Then_get_the_list_of_links());
}

@Then("^click on each link and verify the landing page\\.$")
public void click_on_each_link_and_verify_the_landing_page() {
    // Write code here that turns the phrase above into concrete actions
    this.checkTestResult(URL_Step_MethodClass_PreDEV.then_click_on_each_link_and_verify_the_landing_page());
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



