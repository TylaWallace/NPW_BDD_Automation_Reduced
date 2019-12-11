/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVT_BDD;

import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.currentBrowser;
import static KeywordDrivenTestFramework.Core.BaseClass.currentEnvironment;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberMobileTesting;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberTesting;
import static KeywordDrivenTestFramework.Core.BaseClass.reportDirectory;
import KeywordDrivenTestFramework.Entities.Enums;
import static KeywordDrivenTestFramework.Entities.Enums.Environment.QA;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import KeywordDrivenTestFramework.Utilities.SeleniumDriverUtility;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 *
 * @author fnell
 */

@RunWith(Cucumber.class)
@CucumberOptions
(
        plugin = {"usage","json:target/cucumber.json","html:target/cucumber", "com.cucumber.listener.ExtentCucumberFormatter:"},
        glue ={"Nedbank_RoboAdvisor.Nedbank_Step_Definitions"},
        features ={"Unit-Tests\\Nedbank_RoboAdvisor\\Features\\Jira\\Needs Analysis - Invest.feature"},
        tags = {"@SmokeTest"},
        dryRun = false, 
        monochrome = false, 
        strict = true  
)
    
public class SampleRunner 
{
    public static boolean isDeviceTesting = false;

    @BeforeClass
    public static void setup() 
    { 
        
        isCucumberTesting = true;
        
        currentBrowser = Enums.BrowserType.Chrome;

        isCucumberMobileTesting = true;

        currentEnvironment = QA;
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
      
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        
        reportDirectory = ApplicationConfig.ReportFileDirectory() + "Nedbank_RoboAdvisor_" + SeleniumDriverInstance.generateDateTimeString();
        extentProperties.setReportPath(reportDirectory + "//ExtentReport.html");
        
    }
    
    @AfterClass
    public static void teardown() {
        
        Reporter.getExtentReport().setReportUsesManualConfiguration(true);
        Reporter.loadXMLConfig(new File("extent-config.xml"));
        Reporter.setSystemInfo("User", System.getProperty("user.name"));
        Reporter.setSystemInfo("Environment", currentEnvironment.name());
        Reporter.setSystemInfo("Browser", currentBrowser.name());
    }
}