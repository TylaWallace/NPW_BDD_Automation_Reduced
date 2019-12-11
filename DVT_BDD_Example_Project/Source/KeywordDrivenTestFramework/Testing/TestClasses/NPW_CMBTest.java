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
import KeywordDrivenTestFramework.Testing.PageObjects.NPW_CMB_PageObjects;
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
public class NPW_CMBTest extends BaseClass
{

    public TestEntity testData;
    String error = "";
    Narrator narrator;
    String keyword;
    String Name = "";
    
    public HashMap <String, String> responseValues = new HashMap<>();
    
    boolean reponseFlag = false;

    public NPW_CMBTest(TestEntity testData, String keyword)
    {
        this.testData = testData;
        this.keyword = keyword;
        narrator = new Narrator(testData);
    }
    
    public NPW_CMBTest()
    {    
        narrator = new Narrator();
        //SikuliDriverInstance = new SikuliDriverUtility(System.getProperty("user.dir")+"\\Unit-Tests\\SikuliImages\\");
    }
    
    //CMB METHODS------------------------------------------------------------------------------------------
    public String i_have_navigated_to_CMB_Option()
    {                     
        if (!SeleniumDriverInstance.navigateTo(NPW_CMB_PageObjects.CMB_URL()))
        {
            return "Failed to navigate to url: " + NPW_CMB_PageObjects.CMB_URL();
        }
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_LetsTalk_Header()))
        {
            return "Failed to wait for CMB 'Let's talk' Header";
        }
        
        narrator.stepPassedWithScreenShot("Successfully Navigated to CMB.");
        return null;
    }
    
    public String cmb_Option_Name_field_loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_Name_Input()))
        {
            return "Failed to wait for CMB 'Your Name' input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully waited for CMB Name Field");
        
        return null;
    }
    
    public String enter_at_CMB_Name_field(String arg1)
    {
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_CMB_PageObjects.CMB_Name_Input()))
        {
            return "Failed to click CMB 'Your Name' input";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_CMB_PageObjects.CMB_Name_Input_Field(), arg1))
        {
            return "Failed to enter text at CMB 'Your Name' input field";
        }
        
        Name = Name+arg1;
    
        narrator.stepPassedWithScreenShot("Successfully entered text at CMB Name Field.");
        
        return null;
    }
    
    public String cmb_Option_Surname_field_loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_Surname_Input()))
        {
            return "Failed to wait for CMB 'Your Surname' input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully waited for CMB Surname Field");
        
        return null;
    }
    
    public String enter_at_CMB_Surname_field(String arg1)
    {
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_CMB_PageObjects.CMB_Surname_Input()))
        {
            return "Failed to click CMB 'Your Surname' input";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_CMB_PageObjects.CMB_Surname_Input_Field(), arg1))
        {
            return "Failed to enter text at CMB 'Your Surname' input field";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at CMB Surname Field.");
        
        return null;
    }
    
    public String cmb_Option_Contact_Number_Field_loads()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_ContactNumber_Input()))
        {
            return "Failed to wait for CMB 'Your Cellphone Number' input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully waited for CMB Cellphone Number Field");
        
        return null;
    }
    
    public String enter_at_CMB_Contact_Number_field(String arg1)
    {
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_CMB_PageObjects.CMB_ContactNumber_Input()))
        {
            return "Failed to click CMB 'Cellphone Number' input";
        }
        
        if (!SeleniumDriverInstance.enterTextByXpathUsingActions(NPW_CMB_PageObjects.CMB_ContactNumber_Input_Field(), arg1))
        {
            return "Failed to enter text at CMB 'Cellphone Number' input field";
        }
    
        narrator.stepPassedWithScreenShot("Successfully entered text at CMB Cellphone Number Field.");
        
        return null;
    }
    
    public String cmb_button_is_active()
    {
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_Submit_Button()))
        {
            return "Failed to wait for CMB 'Submit Button' input";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated CMB Submit Button is active.");
        
        return null;
    }
    
    public String clicking_CMB_Button()
    {
        
        if (!SeleniumDriverInstance.clickElementbyXpath(NPW_CMB_PageObjects.CMB_Submit_Button()))
        {
            return "Failed to click CMB 'Submit Button'";
        }
    
        narrator.stepPassedWithScreenShot("Successfully clicked CMB Submit Button.");
        
        return null;
    }
    
    public String cmb_Thank_You_message_is_displayed()
    {
        
        if (!SeleniumDriverInstance.scrollToElement(NPW_CMB_PageObjects.CMB_WillNotContact_Message()))
        {
            SeleniumDriverInstance.scrollToElement(NPW_CMB_PageObjects.CMB_WillNotContact_Message());
        }
        
        
        if (!SeleniumDriverInstance.waitForElementByXpath(NPW_CMB_PageObjects.CMB_ThankYou_Message(Name)))
        {
            return "Failed wait for 'Will Not Contact' Message";
        }
    
        narrator.stepPassedWithScreenShot("Successfully validated Thank You Message.");
        
        return null;
    }
    
    
    //-----------------------------------------------------------------------------------------------------------
    
    
    
    
}

