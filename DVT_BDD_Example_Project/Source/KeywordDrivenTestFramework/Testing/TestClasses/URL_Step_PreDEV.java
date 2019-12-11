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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Key;

/**
 *
 * @author XY59239
 */
public class URL_Step_PreDEV extends BaseClass
{
    public TestEntity testData;
    String error = "";
    Narrator narrator;
    String keyword;
    
    public HashMap <String, String> responseValues = new HashMap<>();
    List <WebElement> links = new ArrayList();
    boolean reponseFlag = false;

    public URL_Step_PreDEV(TestEntity testData, String keyword)
    {
        this.testData = testData;
        this.keyword = keyword;
        narrator = new Narrator(testData);
    }
    
    public URL_Step_PreDEV()
    {    
        narrator = new Narrator();
        //SikuliDriverInstance = new SikuliDriverUtility(System.getProperty("user.dir")+"\\Unit-Tests\\SikuliImages\\");
    }
    
    public String GivenIHaveNavigatedToURL(String arg1){
         if (!SeleniumDriverInstance.navigateTo(arg1))
        {
            return "Failed to navigate to url: " + arg1;
        }
         narrator.stepPassedWithScreenShot("Successfully Navigated to the URL.");
        return null;
    }
    
    public String Then_get_the_list_of_links(){
        links = SeleniumDriverInstance.Driver.findElements(By.tagName("a"));
        narrator.stepPassedWithScreenShot("Successfully fetched the links present in the  URL.");
        return null;
    }
    
    public String then_click_on_each_link_and_verify_the_landing_page(){
        for (int i = 1; i<=links.size(); i=i+1)
  {links.get(i).click();
 SeleniumDriverInstance.Driver.navigate().back();
   }
      narrator.stepPassedWithScreenShot("Successfully clicked on the links present in the  URL.");  
        return null;
    }
    
    
}
