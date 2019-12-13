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
public class NPW_EasiPlus_PageObjects
{
    
    //BDD EXAMPLE XPATHS--------------------------------------
    public static String EasiPlusURL()
    {
       return currentEnvironment.SiteURL;
    }
    
    public static String EPQ_SelectCover_Button()
    {
        return "//om-button-selector[@value='1']";
//        if(currentBrowser.equals(Enums.BrowserType.IE))
//        {
//            return "//label[@for='1'][contains(text(),'Select cover')]";
//            //return "//input[@id='1']//..";
//            
//        }else
//        {
//            return "//om-button-selector[@value='1']";
//        }
        
        //return "//strong[contains(text(),'Me & My Direct Family')]//..//..//..//..//..//div[@class='om-ez-quote-tab-card-button-wrapper']//span//om-button-selector//span//label[@for='1'][contains(text(),'Select cover')]";
    }
    
    public static String EPQ_WhoDoYouWantToCover_Header()
    {
        return "//strong[contains(text(),'Who')]";
    }
    
    public static String EPQ_WhoDoYouWantToCover_DropDown()
    {
        return "//input[@placeholder='Choose from dropdown']//..";
    }
    
    public static String EPQ_WhoDoYouWantToCover_DropDown_Myself()
    {
        return "//li[contains(text(),'Myself')]";
    }
    
    public static String EPQ_Age_Input()
    {
        return "//input[@placeholder='Age']//..//input";
    }
    
    public static String EPQ_Clear_Button()
    {
        return "//button//span[contains(text(),'CLEAR')]";
    }
    
    public static String EPQ_Premium_Amount()
    {
        return "//h3//strong//span[@class='premium']";
    }
    
    public static String EPQ_Cover_Slider()
    {
        return "//div[@class='slider-container']//input";
    }
    
    public static String EPQ_AddToCover_Button()
    {
        return "//button//span[contains(text(),'ADD TO COVER')]";
    }
    
    public static String EPQ_AddedCoverMyself()
    {
        return "//span[contains(text(),'Myself')]";
    }
    
    public static String EPQ_QuoteSummaryCoverTotal()
    {
        return "//p[contains(text(),'Your Funeral Cover could cost')]//..//p//strong";
    }
    
    public static String EPQ_QuoteSummary_DropdownIcon()
    {
        return "//span[@class='button-icon-wrapper']//om-icon";
    }
    
    public static String EPQ_BuyNow_Button()
    {
        return "//*[@id=\"__layout\"]/om-page-bg-color/div/div[2]/div/om-ez-quote-page/div/div[2]/div[2]/div/om-result-card/div/div[2]/om-toast/div/div[3]/div/om-button[1]/button/span";
    }
    
    public static String EPQ_KeepHandy_Header()
    {
        return "//h4[contains(text(),'Keep the following handy')]";
    }
    
    public static String EPQ_Continue_Button()
    {
        return "//span[@class='om-button-text'][contains(text(),'Continue')]";
    }
    
    public static String EPQ_Peronal_Details_Header()
    {
        return "//strong[contains(text(),\"Let's get to know you a bit better\")]";
    }
    
    public static String EPQ_PersonalDetials_Title_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Title']//div//div//div";
    }
    
    public static String EPQ_PersonalDetails_Title_Option(String arg1)
    {
        return "//li[contains(text(),'"+arg1+"')]";
    }
    
    public static String EPQ_PersonalDetails_FirstName_Input()
    {
        return "//input[@placeholder='First Name(s)']";
    }
    
    public static String EPQ_PersonalDetails_FirstName_Input_Field()
    {
        return "//span[contains(text(),'First Name(s)')]//..//input";
    }
    
    public static String EPQ_PersonalDetails_Surname_Input()
    {
        return "//input[@placeholder='Surname']";
    }
    
    public static String EPQ_PersonalDetails_Surname_Input_Field()
    {
        return "//span[contains(text(),'Surname')]//..//input";
    }
    
    public static String EPQ_PersonalDetails_ID_Input()
    {
        return "//input[@placeholder='RSA ID Number']";
    }
    
    public static String EPQ_PersonalDetails_ID_Input_Field()
    {
        return "//span[contains(text(),'RSA ID Number')]//..//input";
    }
    
    public static String EPQ_PersonalDetails_ContactNo_Input()
    {
        return "//input[@placeholder='Contact number']";
    }
    
    public static String EPQ_PersonalDetails_ContactNo_Input_Field()
    {
        return "//span[contains(text(),'Contact number')]//..//input";
    }
    
    public static String EPQ_PersonalDetails_Email_Input()
    {
        return "//input[@placeholder='Email Address']";
    }
    
    public static String EPQ_PersonalDetails_Email_Input_Field()
    {
        return "//span[contains(text(),'Email Address')]//..//input";
    }
    
    public static String EPQ_Residential_Address_Header()
    {
        return "//strong[contains(text(),'Residential Address')]";
    }
    
    public static String EPQ_ResidentialAddress_StreetNo_Input()
    {
        return "//input[@placeholder='Street Number']";
    }
    
    public static String EPQ_ResidentialAddress_StreetNo_Input_Field()
    {
        return "//span[contains(text(),'Street Number')]//..//input";
    }
    
    public static String EPQ_ResidentialAddress_StreetName_Input()
    {
        return "//input[@placeholder='Street Name']";
    }
    
    public static String EPQ_ResidentialAddress_StreetName_Input_Field()
    {
        return "//span[contains(text(),'Street Name')]//..//input";
    }
    
    public static String EPQ_ResidentialAddress_Suburb_Input()
    {
        return "//input[@placeholder='Suburb']";
    }
    
    public static String EPQ_ResidentialAddress_Suburb_Input_Field()
    {
        return "//span[contains(text(),'Suburb')]//..//input";
    }
    
    public static String EPQ_ResidentialAddress_City_Input()
    {
        return "//input[@placeholder='City']";
    }
    
    public static String EPQ_ResidentialAddress_City_Input_Field()
    {
        return "//span[contains(text(),'City')]//..//input";
    }
    
    public static String EPQ_ResidentialAddress_PostalCode_Input()
    {
        return "//input[@placeholder='Postal Code']";
    }
    
    public static String EPQ_ResidentialAddress_PostalCode_Input_Field()
    {
        return "//span[contains(text(),'Postal Code')]//..//input";
    }
    
    public static String EPQ_FamilyMemberDetails_Header()
    {
        return "//h5[contains(text(),'Family member details')]";
    }
    
    public static String EPQ_MyParentsInlaws_Header()
    {
        return "//div[contains(text(),'My Parents & In-laws')]";
    }
    
    public static String EPQ_MyParentsInlaws_AddDependent_Button()
    {
        return "//div[contains(text(),'My Parents & In-laws')]//..//..//div//div//div//om-button";
    }
    
    public static String EPQ_FamilyDetails_Choose_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Choose from dropdown']//div//div//div";
    }
    
    public static String EPQ_FamilyDetails_Title_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Title']//div//div//div";
    }
    
    public static String EPQ_FamilyDetails_Dropdown_Option(String arg1)
    {
        return "//li[contains(text(),'"+arg1+"')][1]";
    }
    
    public static String EPQ_FamilyDetails_FirstName_Input()
    {
        return "//input[@placeholder='First Name(s)']";
    }
    
    public static String EPQ_FamilyDetails_FirstName_Input_Field()
    {
        return "//span[contains(text(),'First Name(s)')]//..//input";
    }
    
    public static String EPQ_FamilyDetails_Surname_Input()
    {
        return "//input[@placeholder='Surname']";
    }
    
    public static String EPQ_FamilyDetails_Surname_Input_Field()
    {
        return "//span[contains(text(),'Surname')]//..//input";
    }
    
    public static String EPQ_FamilyDetails_DOB_Input()
    {
        return "//input[@placeholder='dd/mm/yyyy']";
    }
    
    public static String EPQ_FamilyDetails_DOB_Input_Field()
    {
        return "//span[contains(text(),'Date of Birth')]//..//input";
    }
    
    public static String EPQ_FamilyDetails_HowMuchCover_Header()
    {
        return "//p[contains(text(),'Who do you want to cover')]";
    }
    
    public static String EPQ_FamilyDetails_Cover_Slider()
    {
        return "//div[@class='slider-wrapper']//div//input";
    }
    
    public static String EPQ_FamilyDetails_SaveDependent_Button()
    {
        return "//button//span[contains(text(),'Save Dependent')]";
    }
    
    public static String EPQ_BeneficiaryDetails_Header()
    {
        return "//strong[contains(text(),'Beneficiary details')]";
    }
    
    public static String EPQ_BeneficiaryDetails_Relationship_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Relationship to you']//div//div//div";
    }
    
    public static String EPQ_BeneficiaryDetails_Save_Button()
    {
        return "//button//span[contains(text(),'Save Beneficiary')]";
    }
    
    public static String EPQ_BeneficiarySplit_Header()
    {
        return "//strong[contains(text(),'Beneficiary split')]";
    }
    
    public static String EPQ_PaymentDetails_Header()
    {
        return "//strong[contains(text(),'Payment details')]";
    }
    
    public static String EPQ_PaymentDetails_DebitOrderDate_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Debit Order Date']//div//div//div";
    }
    
    public static String EPQ_PaymentDetails_BankName_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Bank Name']//div//div//div";
    }
    
    public static String EPQ_PaymentDetails_AccountNo_Input()
    {
        return "//input[@placeholder='Account Number']";
    }
    
    public static String EPQ_PaymentDetails_AccountNo_Input_Field()
    {
        return "//span[contains(text(),'Account Number')]//..//input";
    }
    
    public static String EPQ_PaymentDetails_AccountType_Dropdown()
    {
        return "//om-dropdown-field[@placeholder='Account Type']//div//div//div";
    }
    
    public static String EPQ_PaymentDetails_BranchCode_Input()
    {
        return "//input[@placeholder='Branch Code']";
    }
    
    public static String EPQ_PaymentDetails_BranchCode_Input_Field()
    {
        return "//span[contains(text(),'Branch Code')]//..//input";
    }
    
    public static String EPQ_Family_Details_Delete_Icon()
    {
        return "//om-member-details-accordion[@label='Me']//div//div[@class='right']//span[@slot]//om-overlay-opener//div//om-icon//span";
    }
    
    public static String EPQ_Family_Details_Delete_Confirm_Button()
    {
        return "//button//span[contains(text(),'DELETE')]";
    }
    
    
    
    public static String EPQ_GetQuote_Button()
    {
       return "//span[contains(text(),'Account & Lists')][1]";
    }
    
    public static String EPQ_FuneralPlan_Header()
    {
       return "//input[@name='email']";
    }
    
    public static String AmazonAccountPassword()
    {
        return "//input[@name='password']";
    }
    //-----------------------------------------------------------





















    
   
    

  
}
