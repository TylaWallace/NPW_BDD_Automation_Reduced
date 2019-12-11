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
public class BDDExampleTest extends BaseClass
{

    public TestEntity testData;
    String error = "";
    Narrator narrator;
    String keyword;
    
    public HashMap <String, String> responseValues = new HashMap<>();
    
    boolean reponseFlag = false;

    public BDDExampleTest(TestEntity testData, String keyword)
    {
        this.testData = testData;
        this.keyword = keyword;
        narrator = new Narrator(testData);
    }
    
    public BDDExampleTest()
    {    
        narrator = new Narrator();
        SikuliDriverInstance = new SikuliDriverUtility(System.getProperty("user.dir")+"\\Unit-Tests\\SikuliImages\\");
    }
    
    //BDD EXAMPLE METHODS------------------------------------------------------------------------------------------
    public String GivenIHaveNavigatedToAmazon()
    {                     
        if (!SeleniumDriverInstance.navigateTo(BDDPageObjects.AmazonURL()))
        {
            return "Failed to navigate to url: " + BDDPageObjects.AmazonURL();
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountLists()))
        {
            return "Failed to validate the Amazon Landing Page";
        }
        
        narrator.stepPassedWithScreenShot("Successfully Navigated to Robo Advisor website");
        return null;
    }
    
    public String WhenLandingPageLoads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountLists()))
        {
            return "Failed to validate the Amazon Landing Page";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated Amazon Landing Page");
        
        return null;
    }
    
    public String ThenClickLogin()
    {
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountLists()))
        {
            return "Failed to wait for Amazon Accounts & Lists";
        }
        
        if (!SeleniumDriverInstance.clickElementbyXpath(BDDPageObjects.AmazonAccountLists()))
        {
            return "Failed to click Amazon Accounts & Lists";
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(BDDPageObjects.AmazonAccountEmail()))
        {
            return "Failed to wait for Amazon Email Text Input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully clicked Accounts & Lists");
        
        return null;
    }
    
    public String WhenAmazonAsksForDetails()
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

