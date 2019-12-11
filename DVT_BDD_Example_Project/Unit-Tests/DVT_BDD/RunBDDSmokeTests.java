/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVT_BDD;
import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.currentBrowser;
import static KeywordDrivenTestFramework.Core.BaseClass.currentEnvironment;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberTesting;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberMobileTesting;

import static KeywordDrivenTestFramework.Core.BaseClass.reportDirectory;
import KeywordDrivenTestFramework.Entities.Enums;
import static KeywordDrivenTestFramework.Entities.Enums.Environment.QA;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import KeywordDrivenTestFramework.Utilities.SeleniumDriverUtility;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.File;
import static java.lang.System.out;
import java.util.Properties;
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
        features ={"Unit-Tests\\Nedbank_RoboAdvisor\\Features\\NeedsAnalysis\\"},
        tags = {"@SmokeTest"},
        dryRun = false, 
        monochrome = false, 
        strict = true
)
    
public class RunBDDSmokeTests 
{
    public static boolean isDeviceTesting = false;

    @BeforeClass
    public static void setup() 
    {
        
        
        Properties props = System.getProperties();
        
        String browser = "Chrome";/*props.getProperty("Browser");*/

        String environment = "QA"; /* props.getProperty("Environment");*/
        
        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment);

        ApplicationConfig appConfig = new ApplicationConfig();
        

        currentBrowser = Enums.resolveBrowserType(browser);
        
        currentEnvironment = Enums.Environment.QA;
        //    Enums.resolveTestEnvironment(environment)
        isCucumberTesting = true;

        isCucumberMobileTesting = true;

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
