/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Testing.PageObjects;

import static KeywordDrivenTestFramework.Core.BaseClass.currentBrowser;
import static KeywordDrivenTestFramework.Core.BaseClass.currentEnvironment;
import KeywordDrivenTestFramework.Entities.Enums;

/**
 *
 * @author mjivan
 */
public class NPW_CMB_PageObjects
{
    
    //BDD EXAMPLE XPATHS--------------------------------------
    public static String CMB_URL()
    {
       return currentEnvironment.SiteURL;
    }
    
    public static String CMB_LetsTalk_Header()
    {
        return "//span[contains(text(),\"Let's talk\")]";
    }
    
    public static String CMB_Name_Input()
    {
        return "//input[@placeholder='Your Name']";
    }
    
    public static String CMB_Name_Input_Field()
    {
        return "//span[contains(text(),'Your Name')]//..//input";
    }
    
    public static String CMB_Surname_Input()
    {
        return "//input[@placeholder='Your Surname']";
    }
    
    public static String CMB_Surname_Input_Field()
    {
        return "//span[contains(text(),'Your Surname')]//..//input";
    }
    
    public static String CMB_ContactNumber_Input()
    {
        return "//input[@placeholder='Your Cellphone Number']";
    }
    
    public static String CMB_ContactNumber_Input_Field()
    {
        return "//span[contains(text(),'Your Cellphone Number')]//..//input";
    }
    
    public static String CMB_Submit_Button()
    {
        return "//om-check-box[@is-button-checkbox='true']//button";
    }
    
    public static String CMB_WillNotContact_Message()
    {
        return "//span[contains(text(),'We will not contact you for marketing purposes.')]";
    }
    
    public static String CMB_ThankYou_Message(String arg1)
    {
        return "//h2[contains(text(),'Thank you "+arg1+"')]";
    }
    
    //-----------------------------------------------------------





















    
   
    

  
}
