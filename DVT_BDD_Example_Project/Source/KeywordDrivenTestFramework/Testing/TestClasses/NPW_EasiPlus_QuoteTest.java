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
import org.openqa.selenium.Keys;
import org.sikuli.script.Key;

/**
 *
 * @author fnell
 */
public class NPW_EasiPlus_QuoteTest extends BaseClass
{

    public TestEntity testData;
    String error = "";
    Narrator narrator;
    String keyword;
    
    public HashMap <String, String> responseValues = new HashMap<>();
    
    boolean reponseFlag = false;

    public NPW_EasiPlus_QuoteTest(TestEntity testData, String keyword)
    {
        this.testData = testData;
        this.keyword = keyword;
        narrator = new Narrator(testData);
    }
    
    public NPW_EasiPlus_QuoteTest()
    {    
        narrator = new Narrator();
        //SikuliDriverInstance = new SikuliDriverUtility(System.getProperty("user.dir")+"\\Unit-Tests\\SikuliImages\\");
    }
    
    //BDD EXAMPLE METHODS------------------------------------------------------------------------------------------
    public String GivenIHaveNavigatedToEaiPlusQuote()
    {                     
        if (!SeleniumDriverInstance.navigateTo(NPW_EasiPlus_PageObjects.EasiPlusURL()))
        {
            return "Failed to navigate to url: " + NPW_EasiPlus_PageObjects.EasiPlusURL();
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_GetQuote_Button()))
        {
            return "Failed to validate the EasiPlus Quote Landing Page";
        }
        
        narrator.stepPassedWithScreenShot("Successfully Navigated to EasiPlus Quote.");
        return null;
    }
    
    public String WhenEasiPlusQuotePageLoads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_GetQuote_Button()))
        {
            return "Failed to validate the EasiPlus Quote Landing Page";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated EasiPlus Quote Landing Page");
        
        return null;
    }
    
    public String ThenClickGetQuote()
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_GetQuote_Button()))
        {
            return "Failed to wait for EPQ Get Quote Button.";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_EasiPlus_PageObjects.EPQ_GetQuote_Button()))
        {
            return "Failed to click EPQ Get Quote Button.";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_EasiPlus_PageObjects.EPQ_FuneralPlan_Header()))
        {
            return "Failed to wait for EPQ Funeral Plan Header.";
        }
    
        narrator.stepPassedWithScreenShot("Successfully clicked Accounts & Lists");
        
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
    
    public String ThenEnterAnd(String email, int password)
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountEmail()))
        {
            return "Failed to wait for Amazon Email Text Input";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpath(BDDPageObjects.AmazonAccountEmail(), email))
        {
            return "Failed to enter text at Amazon Email Text Input";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountPassword()))
        {
            return "Failed to wait for Amazon Password Text Input";
        }
        if (!SeleniumDriverInstance.enterTextByXpath(BDDPageObjects.AmazonAccountPassword(), ""+password))
        {
            return "Failed to wait for Amazon Password Text Input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered Email and Password");
        
        return null;
    }
    //-----------------------------------------------------------------------------------------------------------
    
    
    
    
}

