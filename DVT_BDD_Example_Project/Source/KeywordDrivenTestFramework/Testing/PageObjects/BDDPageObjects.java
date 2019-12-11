/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Testing.PageObjects;

import static KeywordDrivenTestFramework.Core.BaseClass.currentEnvironment;
import KeywordDrivenTestFramework.Entities.Enums;

/**
 *
 * @author mjivan
 */
public class BDDPageObjects
{
    
    //BDD EXAMPLE XPATHS--------------------------------------
    public static String AmazonURL()
    {
       return currentEnvironment.SiteURL;
    }
    
    public static String AmazonAccountLists()
    {
       return "//span[contains(text(),'Account & Lists')][1]";
    }
    
    public static String AmazonAccountEmail()
    {
       return "//input[@name='email']";
    }
    
    public static String AmazonAccountPassword()
    {
        return "//input[@name='password']";
    }
    //-----------------------------------------------------------





















    
   
    

  
}
