/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Testing.TestClasses;

import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.SikuliDriverInstance;
import KeywordDrivenTestFramework.Entities.Enums;
import static KeywordDrivenTestFramework.Entities.Enums.Environment.QA;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.PageObjects.BDDPageObjects;
import KeywordDrivenTestFramework.Testing.PageObjects.NPW_EasiPlus_PageObjects;
import KeywordDrivenTestFramework.Utilities.SikuliDriverUtility;
import cucumber.api.Scenario;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Key;

/**
 *
 * @author fnell
 */
public class NPW_EasiPlus_QuoteTest_PreDEV extends BaseClass
{

    public TestEntity testData;
    String error = "";
    Narrator narrator;
    String keyword;
    
    public HashMap <String, String> responseValues = new HashMap<>();
    
    boolean reponseFlag = false;

    public NPW_EasiPlus_QuoteTest_PreDEV(TestEntity testData, String keyword)
    {
        this.testData = testData;
        this.keyword = keyword;
        narrator = new Narrator(testData);
    }
    
    public NPW_EasiPlus_QuoteTest_PreDEV()
    {    
        narrator = new Narrator();
        //SikuliDriverInstance = new SikuliDriverUtility(System.getProperty("user.dir")+"\\Unit-Tests\\SikuliImages\\");
    }
    
    //BDD EXAMPLE METHODS------------------------------------------------------------------------------------------
    public String GivenIHaveNavigatedToEaiPlusQuotePreDEV()
    {                     
        if (!SeleniumDriverInstance.navigateTo(NPW_EasiPlus_PageObjects.EasiPlusURL()))
        {
            return "Failed to navigate to url: " + NPW_EasiPlus_PageObjects.EasiPlusURL();
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_SelectCover_Button()))
        {
            return "Failed to wait for Select Cover Button 'Me & My Direct Family";
        }
        
        narrator.stepPassedWithScreenShot("Successfully Navigated to EasiPlus Quote.");
        return null;
    }
    
    public String WhenEasiPlusQuotePageLoadsPreDEV()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_SelectCover_Button()))
        {
            return "Failed to wait for Select Cover Button 'Me & My Direct Family";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated EasiPlus Quote PreDEV Landing Page");
        
        return null;
    }
    
    public String ThenClickSelectCoverMeAndMyDirectFamily()
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_SelectCover_Button()))
        {
            return "Failed to wait for Select Cover Button 'Me & My Direct Family";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_SelectCover_Button()))
        {
            return "Failed to click Select Cover Button 'Me & My Direct Family";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_Header()))
        {
            return "Failed to wait for EPQ 'Who do you want to cover?' Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully clicked Select Cover 'Me & My Direct Family'");
        
        return null;
    }
    
    public String WhenWhoDoYouWantToCoverHeaderLoads()
    {   
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_Header()))
        {
            return "Failed to wait for EPQ 'Who do you want to cover?' Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Validated 'Who Do You Want To Cover' Header.");
        
        return null;
    }
    
    public String ThenSelect_Myself_from_the_Select_Who_You_Want_to_Cover_dropdown()
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_DropDown()))
        {
            return "Failed to wait for Who Do You Want To Cover DropDown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_DropDown()))
        {
            return "Failed to click Who Do You Want To Cover DropDown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_DropDown_Myself()))
        {
            return "Failed to wait for Who Do You Want To Cover Myself DropDown Option.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_WhoDoYouWantToCover_DropDown_Myself()))
        {
            return "Failed to click Who Do You Want To Cover Myself DropDown Option.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Selected Myself From DropDown");
        
        return null;
    }
    
    public String WhenEnteringAnInvalidAge(String arg1)
    {   
        
        if (SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_Clear_Button()))
        {
            return "Failed to scroll to Clear Button.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Age_Input()))
        {
            return "Failed to wait for EPQ Age Input.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_Age_Input(),arg1))
        {
            return "Failed to enter text at EPQ Age Input.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Entered Invalid Age.");
        
        return null;
    }
    
    public String ThenThe_Premium_amount_remains_and_an_Error_message_is_displayed(String arg1)
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Premium_Amount()))
        {
            return "Failed to wait for Premium Amount.";
        }
        
        String Premium = SeleniumDriverInstance.retrieveTextByXpath(NPW_EasiPlus_PageObjects.EPQ_Premium_Amount());
        if(!Premium.contains(arg1))
        {
            return "Failed to Validate Premium Amount: "+arg1;
        }
    
        narrator.stepPassedWithScreenShot("Successfully Validated Premium Amount.");
        
        return null;
    }
    
    public String WhenEntering_a_and_selecting_the_from_the_slider(String arg1, String arg2)
    {   
        
        if (SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_Clear_Button()))
        {
            return "Failed to scroll to Clear Button.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Age_Input()))
        {
            return "Failed to wait for EPQ Age Input.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_Age_Input(),arg1))
        {
            return "Failed to enter text at EPQ Age Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Cover_Slider()))
        {
            return "Failed to wait for EPQ Cover Slider.";
        }
        
//        if (!SeleniumDriverInstance.clickElementbyXpathUsingActionsOffset(NPW_EasiPlus_PageObjects.EPQ_Cover_Slider()))
//        {
//            return "Failed to click EPQ Cover Slider.";
//        }
//        
//        
        if (!SeleniumDriverInstance.clickElementbyXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_Cover_Slider()))
        {
            return "Failed to click EPQ Cover Slider.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Entered Age and Selected Cover Slider.");
        
        return null;
    }
    
    public String ThenAmount_is_calculated(String arg1)
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Premium_Amount()))
        {
            return "Failed to wait for Premium Amount 2.";
        }
        
        pause(1000);
        String Premium = SeleniumDriverInstance.retrieveTextByXpath(NPW_EasiPlus_PageObjects.EPQ_Premium_Amount());
        if(!Premium.contains(arg1))
        {
            return "Failed to Validate Premium Amount: "+arg1;
        }
    
        narrator.stepPassedWithScreenShot("Successfully Validated Premium Amount.");
        
        return null;
    }
    
    public String WhenClicking_Add_To_Cover()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_AddToCover_Button()))
        {
            return "Failed to wait for ADD TO COVER Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_AddToCover_Button()))
        {
            return "Failed to click ADD TO COVER Button.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_AddedCoverMyself()))
        {
            return "Failed to wait for Added To Cover Myself.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Clicked ADD TO COVER Button.");
        
        return null;
    
    }
    
    public String ThenIs_added_to_Quote_Summary(String arg1)
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_AddedCoverMyself()))
        {
            return "Failed to wait for Added To Cover Myself.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_QuoteSummaryCoverTotal()))
        {
            return "Failed to wait for Quote Summary Cover Total.";
        }
        
        pause(1000);
        String CoverTotal = SeleniumDriverInstance.retrieveTextByXpath(NPW_EasiPlus_PageObjects.EPQ_QuoteSummaryCoverTotal());
        if(!CoverTotal.contains(arg1))
        {
            return "Failed to Validate Quote Summary Cover Total Amount: "+arg1;
        }
        
        
        narrator.stepPassedWithScreenShot("Successfully Validated Quote Summary Cover Total Amount.");
        
        return null;
    }
    
    public String WhenClicking_the_Dropdown_icon()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_QuoteSummary_DropdownIcon()))
        {
            return "Failed to wait for Quote Summary Dropdown Icon.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_QuoteSummary_DropdownIcon()))
        {
            return "Failed to click Quote Summary Dropdown Icon.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Clicked Quote Aummary Dropdown Icon.");
        
        return null;
    
    }
    
    public String WhenNaviagtedToPackagesSection()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountEmail()))
        {
            return "Failed to wait for Amazon Email Text Input";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountPassword()))
        {
            return "Failed to wait for Amazon Password Text Input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated Amazon Login Page");
        
        return null;
    
    }
    
    public String clicking_the_Buy_Online_button()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_BuyNow_Button()))
        {
            return "Failed to wait for EPQ Buy Now Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_BuyNow_Button()))
        {
            return "Failed to click EPQ Buy Now Button.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Clicked EPQ Buy Now Button.");
        
        return null;
    
    }
    
    public String the_User_is_redirected_to_the_EasiPlus_Application()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_KeepHandy_Header()))
        {
            return "Failed to wait for EPQ Keep the following Handy Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully Navigated to EPQ App.");
        
        return null;
    
    }
    
    public String click_continue_button()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Continue_Button()))
        {
            return "Failed to wait for EPQ Continue Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_Continue_Button()))
        {
            return "Failed to click EPQ Continue Button.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully clicked EPQ Continue Button.");
        
        return null;
    
    }
    
    public String epa_Personal_Details_Page_Loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Peronal_Details_Header()))
        {
            return "Failed to wait for EPQ Personal Details Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Personal Details.");
        
        return null;
    
    }
    
    public String select_a_Title_for_Personal_Details(String arg1)
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetials_Title_Dropdown()))
        {
            return "Failed to wait for Title DropDown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetials_Title_Dropdown()))
        {
            return "Failed to click Title Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Title_Option(arg1)))
        {
            return "Failed to wait for Title DropDown Option "+arg1;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Title_Option(arg1)))
        {
            return "Failed to click Title DropDown Option "+arg1;
        }
    
        narrator.stepPassedWithScreenShot("Successfully Selected "+arg1+" From DropDown");
        
        return null;
    }
    
    public String enter_FirstName_for_Personal_Detail(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_FirstName_Input()))
        {
            return "Failed to wait for EPQ Personal Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_FirstName_Input()))
        {
            return "Failed to click EPQ Personal Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_FirstName_Input_Field()))
        {
            return "Failed to wait for EPQ Personal Details First Name Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_FirstName_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Personal Details First Name Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Personal Details First Name Input Field.");
        
        return null;
    
    }
    
    public String enter_Surname_for_Personal_Detail(String arg1)
    {
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Surname_Input());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Surname_Input()))
        {
            return "Failed to wait for EPQ Personal Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Surname_Input()))
        {
            return "Failed to click EPQ Personal Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Surname_Input_Field()))
        {
            return "Failed to wait for EPQ Personal Details Surname Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Surname_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Personal Details Surname Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Personal Details Surname Input Field.");
        
        return null;
    
    }
    
    public String enter_ID_for_Personal_Detail(String arg1)
    {
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ID_Input()))
        {
            return "Failed to wait for EPQ Personal Details ID Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ID_Input()))
        {
            return "Failed to click EPQ Personal Details ID Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ID_Input_Field()))
        {
            return "Failed to wait for EPQ Personal Details ID Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ID_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Personal Details ID Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Personal Details ID Input Field.");
        
        return null;
    
    }
    
    public String enter_ContactNo_for_Personal_Detail(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input()))
        {
            return "Failed to wait for EPQ Personal Details ContactNo Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input()))
        {
            return "Failed to click EPQ Personal Details ContactNo Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input_Field()))
        {
            return "Failed to wait for EPQ Personal Details ContactNo Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Personal Details ContactNo Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Personal Details ContactNo Input Field.");
        
        return null;
    
    }
    
    public String enter_Email_for_Personal_Detail(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input()))
        {
            return "Failed to wait for EPQ Personal Details Email Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input()))
        {
            return "Failed to click EPQ Personal Details Email Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input_Field()))
        {
            return "Failed to wait for EPQ Personal Details Email Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Personal Details Email Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Personal Details Email Input Field.");
        
        return null;
    
    }
    
    public String epa_Residential_Address_Page_Loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_Residential_Address_Header()))
        {
            return "Failed to wait for EPQ Residential Address Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Residential Address.");
        
        return null;
    
    }
    
    public String enter_StreetNo_for_Residential_Address(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetNo_Input()))
        {
            return "Failed to wait for EPQ Residential Address StreetNo Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetNo_Input()))
        {
            return "Failed to click EPQ Residential Address StreetNo Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetNo_Input_Field()))
        {
            return "Failed to wait for EPQ Residential Address StreetNo Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetNo_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Residential Address StreetNo Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Residential Address StreetNo Input Field.");
        
        return null;
    
    }
    
    public String enter_StreetName_for_Residential_Address(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetName_Input()))
        {
            return "Failed to wait for EPQ Residential Address StreetName Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetName_Input()))
        {
            return "Failed to click EPQ Residential Address StreetName Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetName_Input_Field()))
        {
            return "Failed to wait for EPQ Residential Address StreetName Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_StreetName_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Residential Address StreetName Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Residential Address StreetName Input Field.");
        
        return null;
    
    }
    
    public String enter_Suburb_for_Residential_Address(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_Suburb_Input()))
        {
            return "Failed to wait for EPQ Residential Address Suburb Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_Suburb_Input()))
        {
            return "Failed to click EPQ Residential Address Suburb Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_Suburb_Input_Field()))
        {
            return "Failed to wait for EPQ Residential Address Suburb Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_Suburb_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Residential Address Suburb Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Residential Address Suburb Input Field.");
        
        return null;
    
    }
    
    public String enter_City_for_Residential_Address(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_City_Input()))
        {
            return "Failed to wait for EPQ Residential Address City Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_City_Input()))
        {
            return "Failed to click EPQ Residential Address City Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_City_Input_Field()))
        {
            return "Failed to wait for EPQ Residential Address City Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_City_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Residential Address City Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Residential Address City Input Field.");
        
        return null;
    
    }
    
    public String enter_PostalCode_for_Residential_Address(String arg1)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_PostalCode_Input()))
        {
            return "Failed to wait for EPQ Residential Address PostalCode Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_PostalCode_Input()))
        {
            return "Failed to click EPQ Residential Address PostalCode Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_PostalCode_Input_Field()))
        {
            return "Failed to wait for EPQ Residential Address PostalCode Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_ResidentialAddress_PostalCode_Input_Field(), arg1 ))
        {
            return "Failed to enter text at EPQ Residential Address PostalCode Input Field.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at EPQ Residential Address PostalCode Input Field.");
        
        return null;
    
    }
    
    public String family_Member_Details_Page_Load()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyMemberDetails_Header()))
        {
            return "Failed to wait for EPQ Family Member Details.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Family Member Details.");
        
        return null;
    
    }
    
    public String add_a_Parent_Dependant(String ParentDependent, String Title, String DependentFirstName, String Surname, String DependentDOB)
    {
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_MyParentsInlaws_Header());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_MyParentsInlaws_AddDependent_Button()))
        {
            return "Failed to wait for EPQ My Parents & In-laws Add Dependent Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_MyParentsInlaws_AddDependent_Button()))
        {
            return "Failed to click EPQ My Parents & In-laws Add Dependent Button.";
        }
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_MyParentsInlaws_Header());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Choose_Dropdown()))
        {
            return "Failed to wait for Parent/In-law DropDown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Choose_Dropdown()))
        {
            return "Failed to click Parent/In-law Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(ParentDependent)))
        {
            return "Failed to wait for DropDown Option "+ParentDependent;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(ParentDependent)))
        {
            return "Failed to click DropDown Option "+ParentDependent;
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Title_Dropdown()))
        {
            return "Failed to wait for Title DropDown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Title_Dropdown()))
        {
            return "Failed to click Title Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(Title)))
        {
            return "Failed to wait for DropDown Option "+Title;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(Title)))
        {
            return "Failed to click DropDown Option "+Title;
        }
        
         if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input()))
        {
            return "Failed to wait for EPQ Family Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input()))
        {
            return "Failed to click EPQ Family Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input_Field()))
        {
            return "Failed to wait for EPQ Family Details First Name Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input_Field(), DependentFirstName ))
        {
            return "Failed to enter text at EPQ Family Details First Name Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input()))
        {
            return "Failed to wait for EPQ Family Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input()))
        {
            return "Failed to click EPQ Family Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input_Field()))
        {
            return "Failed to wait for EPQ Family Details Surname Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input_Field(), Surname ))
        {
            return "Failed to enter text at EPQ Family Details Surname Input Field.";
        }
        
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input()))
        {
            return "Failed to wait for EPQ Family Details Date Of Birth Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input()))
        {
            return "Failed to click EPQ Family Details Date Of Birth Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input_Field()))
        {
            return "Failed to wait for EPQ Family Details Date Of Birth Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input_Field(), DependentDOB ))
        {
            return "Failed to enter text at EPQ Family Details Date Of Birth Input Field.";
        }
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_HowMuchCover_Header());
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Cover_Slider()))
        {
            return "Failed to click EPQ Family Cover Slider.";
        }
        
        narrator.stepPassedWithScreenShot("Successfully entered Details for EPQ Parent/In-law Dependent.");
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Cover_Slider());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_SaveDependent_Button()))
        {
            return "Failed to wait for EPQ Family Details Save Dependent Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_SaveDependent_Button()))
        {
            return "Failed to click EPQ Family Details Save Dependent Button.";
        }
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_FamilyMemberDetails_Header());
    
        narrator.stepPassedWithScreenShot("Successfully added a EPQ Parent/In-law Dependent.");
        
        return null;
    
    }
    
    public String epa_Beneficiary_Details_Page_Loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Header()))
        {
            return "Failed to wait for EPQ Beneficiary Details.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Beneficiary Details.");
        
        return null;
    
    }
    
    public String add_a_Beneficiary(String BeneficiaryTitle, String BeneficiaryName, String Surname, String BeneficiaryDOB, String BeneficiaryContactNo, String BeneficiaryEmail, String BeneficiaryRelationship)
    {
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Header());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetials_Title_Dropdown()))
        {
            return "Failed to wait for EPQ Beneficiary Details Title Dropdown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetials_Title_Dropdown()))
        {
            return "Failed to click EPQ Beneficiary Details Title Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BeneficiaryTitle)))
        {
            return "Failed to wait for DropDown Option "+BeneficiaryTitle;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BeneficiaryTitle)))
        {
            return "Failed to click DropDown Option "+BeneficiaryTitle;
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input()))
        {
            return "Failed to wait for EPQ Beneficiary Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input()))
        {
            return "Failed to click EPQ Beneficiary Details First Name Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input_Field()))
        {
            return "Failed to wait for EPQ Beneficiary Details First Name Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_FirstName_Input_Field(), BeneficiaryName ))
        {
            return "Failed to enter text at EPQ Beneficiary Details First Name Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input()))
        {
            return "Failed to wait for EPQ Beneficiary Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input()))
        {
            return "Failed to click EPQ Beneficiary Details Surname Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input_Field()))
        {
            return "Failed to wait for EPQ Beneficiary Details Surname Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Surname_Input_Field(), Surname ))
        {
            return "Failed to enter text at EPQ Beneficiary Details Surname Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input()))
        {
            return "Failed to wait for EPQ EPQ Beneficiary Details Date Of Birth Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input()))
        {
            return "Failed to click EPQ EPQ Beneficiary Details Date Of Birth Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input_Field()))
        {
            return "Failed to wait for EPQ EPQ Beneficiary Details Date Of Birth Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input_Field(), BeneficiaryDOB ))
        {
            return "Failed to enter text at EPQ EPQ Beneficiary Details Date Of Birth Input Field.";
        }
        
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_DOB_Input_Field());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input()))
        {
            return "Failed to wait for EPQ Beneficiary Details ContactNo Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input()))
        {
            return "Failed to click EPQ Beneficiary Details ContactNo Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input_Field()))
        {
            return "Failed to wait for EPQ Beneficiary Details ContactNo Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_ContactNo_Input_Field(), BeneficiaryContactNo ))
        {
            return "Failed to enter text at EPQ Beneficiary Details ContactNo Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input()))
        {
            return "Failed to wait for EPQ Beneficiary Details Email Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input()))
        {
            return "Failed to click EPQ Beneficiary Details Email Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input_Field()))
        {
            return "Failed to wait for EPQ Beneficiary Details Email Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PersonalDetails_Email_Input_Field(), BeneficiaryEmail ))
        {
            return "Failed to enter text at EPQ Beneficiary Details Email Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Relationship_Dropdown()))
        {
            return "Failed to wait for EPQ Beneficiary Details Relationship Dropdown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Relationship_Dropdown()))
        {
            return "Failed to click EPQ Beneficiary Details Relationship Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BeneficiaryRelationship)))
        {
            return "Failed to wait for DropDown Option "+BeneficiaryRelationship;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BeneficiaryRelationship)))
        {
            return "Failed to click DropDown Option "+BeneficiaryRelationship;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Save_Button()))
        {
            return "Failed to click Beneficiary Details Save Button.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiaryDetails_Header()))
        {
            return "Failed to wait for EPQ Beneficiary Details after save.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully add a Beneficiary.");
        
        return null;
    
    }
    
    public String beneficiary_Split_Page_Loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_BeneficiarySplit_Header()))
        {
            return "Failed to wait for EPQ Beneficiary Split Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Beneficiary Split.");
        
        return null;
    
    }
    
    public String payment_Details_Page_Loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_Header()))
        {
            return "Failed to wait for EPQ Payment Details Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully navigated to EPQ Payment Details.");
        
        return null;
    
    }
    
    public String add_Payment_Details(String DebitOrderDate, String BankName, String AccountNumber, String AccountType, String BranchCode)
    {
        SeleniumDriverInstance.scrollToElement(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_Header());
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_DebitOrderDate_Dropdown()))
        {
            return "Failed to wait for EPQ Payment Details DebitOrderDate Dropdown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_DebitOrderDate_Dropdown()))
        {
            return "Failed to click EPQ Payment Details DebitOrderDate Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(DebitOrderDate)))
        {
            return "Failed to wait for DropDown Option "+DebitOrderDate;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(DebitOrderDate)))
        {
            return "Failed to click DropDown Option "+DebitOrderDate;
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BankName_Dropdown()))
        {
            return "Failed to wait for EPQ Payment Details Bank Name Dropdown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BankName_Dropdown()))
        {
            return "Failed to click EPQ Payment Details Bank Name Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BankName)))
        {
            return "Failed to wait for DropDown Option "+BankName;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(BankName)))
        {
            return "Failed to click DropDown Option "+BankName;
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountNo_Input()))
        {
            return "Failed to wait for EPQ Payment Details Account Number Input.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountNo_Input()))
        {
            return "Failed to click EPQ Payment Details Account Number Input.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountNo_Input_Field()))
        {
            return "Failed to wait for EPQ Payment Details Account Number Input Field.";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountNo_Input_Field(), AccountNumber ))
        {
            return "Failed to enter text at EPQ Payment Details Account Number Input Field.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountType_Dropdown()))
        {
            return "Failed to wait for EPQ Payment Details AccountType Dropdown.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_AccountType_Dropdown()))
        {
            return "Failed to click EPQ Payment Details AccountType Dropdown.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(AccountType)))
        {
            return "Failed to wait for DropDown Option "+AccountType;
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_FamilyDetails_Dropdown_Option(AccountType)))
        {
            return "Failed to click DropDown Option "+AccountType;
        }
        
//        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BranchCode_Input()))
//        {
//            return "Failed to wait for EPQ Payment Details BranchCode Input.";
//        }
//        
//        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BranchCode_Input()))
//        {
//            return "Failed to click EPQ Payment Details BranchCode Input.";
//        }
//        
//        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BranchCode_Input_Field()))
//        {
//            return "Failed to wait for EPQ Payment Details BranchCode Input Field.";
//        }
//        
//        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_EasiPlus_PageObjects.EPQ_PaymentDetails_BranchCode_Input_Field(), BranchCode ))
//        {
//            return "Failed to enter text at EPQ Payment Details BranchCode Input Field.";
//        }
    
        narrator.stepPassedWithScreenShot("Successfully Added Payment Details.");
        
        return null;
    
    }
    //-----------------------------------------------------------------------------------------------------------
    
    
    
    
}

