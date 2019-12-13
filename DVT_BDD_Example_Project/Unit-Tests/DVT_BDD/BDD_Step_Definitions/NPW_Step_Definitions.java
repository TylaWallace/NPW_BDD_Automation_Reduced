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
import KeywordDrivenTestFramework.Testing.TestClasses.NPW_CMBTest;
import KeywordDrivenTestFramework.Testing.TestClasses.NPW_EasiPlus_QuoteTest;
import KeywordDrivenTestFramework.Testing.TestClasses.NPW_EasiPlus_QuoteTest_PreDEV;
import java.io.IOException;
import static org.junit.Assert.fail;
/**
 *
 * @author twallace
 */
@StepDefAnnotation
public class NPW_Step_Definitions extends BaseClass 
{
    
    NPW_EasiPlus_QuoteTest NPW_EasiPlus_MethodClass;
    NPW_EasiPlus_QuoteTest_PreDEV NPW_EasiPlus_MethodClass_PreDEV;
    NPW_CMBTest NPW_CMB_MethodClass;

    public NPW_Step_Definitions()
    {
        //add test classes to be used in scenario
        NPW_EasiPlus_MethodClass = new NPW_EasiPlus_QuoteTest();
        NPW_EasiPlus_MethodClass_PreDEV = new NPW_EasiPlus_QuoteTest_PreDEV();
        NPW_CMB_MethodClass = new NPW_CMBTest();
        
        
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
    

//    @Then("^enter \"([^\"]*)\" and (\\d+)$")
//    public void enter_and(String arg1, int arg2)
//    {
//        this.checkTestResult(BDDMethodClass.ThenEnterAnd(arg1, arg2));
//    }
    
    //NPW EASIPLUS STEPS--------------------------------------
    
    @Given("^I have navigated to EasiPlus Quote PreDEV$")
    public void i_have_navigated_to_EasiPlus_Quote_PreDEV()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.GivenIHaveNavigatedToEaiPlusQuotePreDEV());
    }

    @When("^EasiPlus Quote PreDEV Page loads$")
    public void easiplus_Quote_PreDEV_Page_loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenEasiPlusQuotePageLoadsPreDEV());
    }

    @Then("^click Select Cover Me and My Direct Family$")
    public void click_Select_Cover_Me_and_My_Direct_Family()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.ThenClickSelectCoverMeAndMyDirectFamily());
    }

    @When("^Who do you want to cover Header Loads$")
    public void who_do_you_want_to_cover_Header_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenWhoDoYouWantToCoverHeaderLoads());
    }

    @Then("^select Myself from the Select Who You Want to Cover dropdown$")
    public void select_Myself_from_the_Select_Who_You_Want_to_Cover_dropdown()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.ThenSelect_Myself_from_the_Select_Who_You_Want_to_Cover_dropdown());
    }

    @When("^entering an \"([^\"]*)\"$")
    public void entering_an(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenEnteringAnInvalidAge(arg1));
    }

    @Then("^the Premium amount remains \"([^\"]*)\" and an Error message is displayed$")
    public void the_Premium_amount_remains_and_an_Error_message_is_displayed(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.ThenThe_Premium_amount_remains_and_an_Error_message_is_displayed(arg1));
    }

    @When("^entering a \"([^\"]*)\" and  selecting the \"([^\"]*)\" from the slider$")
    public void entering_a_and_selecting_the_from_the_slider(String arg1, String arg2)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenEntering_a_and_selecting_the_from_the_slider(arg1,arg2));
    }

    @Then("^\"([^\"]*)\" amount is calculated$")
    public void amount_is_calculated(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.ThenAmount_is_calculated(arg1));
    }

    @When("^clicking Add To Cover$")
    public void clicking_Add_To_Cover()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenClicking_Add_To_Cover());
    }

    @Then("^\"([^\"]*)\" is added to Quote Summary$")
    public void is_added_to_Quote_Summary(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.ThenIs_added_to_Quote_Summary(arg1));
    }

    @When("^clicking the Dropdown icon$")
    public void clicking_the_Dropdown_icon()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.WhenClicking_the_Dropdown_icon());
    }

    @Then("^the Quote Summary is minimized or maximized$")
    public void the_Quote_Summary_is_minimized_or_maximized()
    {
        // Write code here that turns the phrase above into concrete actions
    }
    
    @When("^clicking the Buy Online button$")
    public void clicking_the_Buy_Online_button()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.clicking_the_Buy_Online_button());
    }

    @Then("^the User is redirected to the EasiPlus Application$")
    public void the_User_is_redirected_to_the_EasiPlus_Application()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.the_User_is_redirected_to_the_EasiPlus_Application());
    }
    
    @Then("^click continue button$")
    public void click_continue_button()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.click_continue_button());
    }
    
    @When("^EPA Personal Details Page Loads$")
    public void epa_Personal_Details_Page_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.epa_Personal_Details_Page_Loads());
    }

    @Then("^Select a Title \"([^\"]*)\" for Personal Details$")
    public void select_a_Title_for_Personal_Details(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.select_a_Title_for_Personal_Details(arg1));
    }

    @Then("^enter FirstName \"([^\"]*)\" for Personal Details$")
    public void enter_FirstName_for_Personal_Details(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_FirstName_for_Personal_Detail(arg1));
    }

    @Then("^enter Surname \"([^\"]*)\" for Personal Details$")
    public void enter_Surname_for_Personal_Details(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_Surname_for_Personal_Detail(arg1));
    }

    @Then("^enter ID \"([^\"]*)\" for Personal Details$")
    public void enter_ID_for_Personal_Details(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_ID_for_Personal_Detail(arg1));
    }

    @Then("^enter ContactNo \"([^\"]*)\" for Personal Details$")
    public void enter_ContactNo_for_Personal_Details(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_ContactNo_for_Personal_Detail(arg1));
    }

    @Then("^enter Email \"([^\"]*)\" for Personal Details$")
    public void enter_Email_for_Personal_Details(String arg1)
    {
       this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_Email_for_Personal_Detail(arg1));
    }
    
    @When("^EPA Residential Address Page Loads$")
    public void epa_Residential_Address_Page_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.epa_Residential_Address_Page_Loads());
    }

    @Then("^enter Street Number \"([^\"]*)\" for Residential Address$")
    public void enter_Street_Number_for_Residential_Address(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_StreetNo_for_Residential_Address(arg1));
    }

    @Then("^enter Street Name \"([^\"]*)\" for Residential Address$")
    public void enter_Street_Name_for_Residential_Address(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_StreetName_for_Residential_Address(arg1));
    }

    @Then("^enter Suburb \"([^\"]*)\" for Residential Address$")
    public void enter_Suburb_for_Residential_Address(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_Suburb_for_Residential_Address(arg1));
    }

    @Then("^enter City \"([^\"]*)\" for Residential Address$")
    public void enter_City_for_Residential_Address(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_City_for_Residential_Address(arg1));
    }

    @Then("^enter PostalCode \"([^\"]*)\" for Residential Address$")
    public void enter_PostalCode_for_Residential_Address(String arg1)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.enter_PostalCode_for_Residential_Address(arg1));
    }
    
    @When("^Family Member Details Page Load$")
    public void family_Member_Details_Page_Load()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.family_Member_Details_Page_Load());
    }

    @Then("^Add a Parent Dependant \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_a_Parent_Dependant(String arg1, String arg2, String arg3, String arg4, String arg5)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.add_a_Parent_Dependant(arg1, arg2, arg3, arg4, arg5));
    }
    
    @When("^EPA Beneficiary Details Page Loads$")
    public void epa_Beneficiary_Details_Page_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.epa_Beneficiary_Details_Page_Loads());
    }

    @Then("^Add a Beneficiary \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_a_Beneficiary(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.add_a_Beneficiary(arg1, arg2, arg3, arg4, arg5, arg6, arg7));
    }
    
    @When("^Beneficiary Split Page Loads$")
    public void beneficiary_Split_Page_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.beneficiary_Split_Page_Loads());
    }
    
    @When("^Payment Details Page Loads$")
    public void payment_Details_Page_Loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.payment_Details_Page_Loads());
    }

    @Then("^Add Payment Details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_Payment_Details(String arg1, String arg2, String arg3, String arg4, String arg5)
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.add_Payment_Details(arg1, arg2, arg3, arg4, arg5));
    }
    
    @Then("^remove My details$")
    public void remove_My_details() 
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass_PreDEV.remove_My_details());
    }
    
    //-------------------------------------------------------
    
    @Given("^I have navigated to EasiPlus Quote$")
    public void i_have_navigated_to_EasiPlus_Quote()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass.GivenIHaveNavigatedToEaiPlusQuote());
    }

    @When("^EasiPlus Quote Page loads$")
    public void easiplus_Quote_Page_loads()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass.WhenEasiPlusQuotePageLoads());
    }

    @Then("^click Get Quote$")
    public void click_Get_Quote()
    {
        this.checkTestResult(NPW_EasiPlus_MethodClass.ThenClickGetQuote());
    }
    
    //---------------------Call Me Back--------------------------
    @Given("^I have navigated to CMB Option$")
    public void i_have_navigated_to_CMB_Option() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.i_have_navigated_to_CMB_Option());
    }

    @When("^CMB Option Name field loads$")
    public void cmb_Option_Name_field_loads() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.cmb_Option_Name_field_loads());
    }

    @Then("^enter \"([^\"]*)\" at CMB Name field$")
    public void enter_at_CMB_Name_field(String arg1) 
    {
        this.checkTestResult(NPW_CMB_MethodClass.enter_at_CMB_Name_field(arg1));
    }

    @When("^CMB Option Surname field loads$")
    public void cmb_Option_Surname_field_loads() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.cmb_Option_Surname_field_loads());
    }

    @Then("^enter \"([^\"]*)\" at CMB Surname field$")
    public void enter_at_CMB_Surname_field(String arg1) 
    {
        this.checkTestResult(NPW_CMB_MethodClass.enter_at_CMB_Surname_field(arg1));
    }

    @When("^CMB Option Contact Number Field loads$")
    public void cmb_Option_Contact_Number_Field_loads() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.cmb_Option_Contact_Number_Field_loads());
    }

    @Then("^enter \"([^\"]*)\" at CMB Contact Number field$")
    public void enter_at_CMB_Contact_Number_field(String arg1) 
    {
        this.checkTestResult(NPW_CMB_MethodClass.enter_at_CMB_Contact_Number_field(arg1));
    }

    @Then("^CMB button is active$")
    public void cmb_button_is_active() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.cmb_button_is_active());
    }

    @When("^clicking CMB Button$")
    public void clicking_CMB_Button() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.clicking_CMB_Button());
    }

    @Then("^CMB Thank You message is displayed$")
    public void cmb_Thank_You_message_is_displayed() 
    {
        this.checkTestResult(NPW_CMB_MethodClass.cmb_Thank_You_message_is_displayed());
    }
    //-----------------------------------------------------------

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
