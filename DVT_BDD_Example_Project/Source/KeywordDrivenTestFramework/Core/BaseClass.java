/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Core;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.QTest_DAO.Execution;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Reporting.ReportGenerator;
import KeywordDrivenTestFramework.Utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.openqa.selenium.Proxy;

/**
 *
 * @author fnell
 */
// All tests inherit from base class, 
// Base class contains initialisations of all neccessary utilities and 
// entities
public class BaseClass {

    public static List<TestEntity> testDataList;
    public static Enums.BrowserType currentBrowser;
    public static ReportGenerator reportGenerator;
    
    public static SeleniumDriverUtility SeleniumDriverInstance;
    public static SikuliDriverUtility SikuliDriverInstance;
//    public static TestRailUtility testRailInstance;
    public static DataBaseUtility DataBaseInstance;
    public static AutoItDriverUtility AutoItInstance;
    public static OtpSmsUtility otpSms;
    public static ApplicationConfig appConfig = new ApplicationConfig();
    private DateTime startTime, endTime;
    private Duration testDuration;
    public static String testCaseId;
    public static String reportDirectory;
    public static String currentTestDirectory;
    public static Enums.Environment currentEnvironment;

    //Appium-specific config
    
    public static Enums.Database currentDatabase;
    public static Enums.Device currentDevice;
    public static Enums.DeviceConfig currentDeviceConfig;
    public static Enums.MobilePlatform currentPlatform;
    public static AppiumDriverUtility AppiumDriverInstance;
    public static boolean requiresBrowser = true; //For appium set this false in the @Test
    public static boolean _isRemote = false; //For Remote Testing set to True in @Test
    //

    public static String inputFilePath;
    public static String screenshotPath;
    public static String relativeScreenShotPath;
    public TestEntity testData;
    
    public static Narrator narrator;

    // Cucumber
    public static boolean isCucumberTesting = false;
    public static boolean isCucumberMobileTesting = false;

     //Performance monitoring section
    public static PerformanceMonitor performanceMonitor;
    public Proxy seleniumProxy;
    public static boolean EnablePerformanceMonitoring = false; 
    public static BrowserMobProxyServer BMProxy;
    public static Har performanceHar;   
    
    //BDD
    public static Execution currentExecution;
    public static int scenarioCount = 1;

    public static String getRelativeScreenshotPath() {
        return  "./" + relativeScreenShotPath;
    }

    public static void setScreenshotPath(String screenshotPath) {
        BaseClass.screenshotPath = screenshotPath;
    }

    public BaseClass() {
        System.setProperty("java.awt.headless", "false");
    }

    public void setStartTime() {
        this.startTime = new DateTime();
    }

    public long getTotalExecutionTime() {
        this.endTime = new DateTime();
        testDuration = new Duration(this.startTime, this.endTime);
        return testDuration.getStandardSeconds();
    }

    public String resolveScenarioName() {
        String isolatedFileNameString = "";
        String[] splitFileName;
        String[] inputFileNameArray;
        String resolvedScenarioName = "";

        if (isCucumberTesting)
        {
            isolatedFileNameString = inputFilePath;
        }
        else
        {
            // Get file name from inputFilePath (remove file extension)
            inputFileNameArray = inputFilePath.split("\\.");
            isolatedFileNameString = inputFileNameArray[0];
            if (isolatedFileNameString.contains("/"))
            {
                inputFileNameArray = isolatedFileNameString.split("/");
            }
            else if (isolatedFileNameString.contains("\\"))
            {
                inputFileNameArray = isolatedFileNameString.split("\\\\");
            }

            isolatedFileNameString = inputFileNameArray[inputFileNameArray.length - 1];

        }

        splitFileName = isolatedFileNameString.split("(?=\\p{Upper})");
        for (String word : splitFileName) {
            resolvedScenarioName += word + " ";
        }

        return resolvedScenarioName.trim();
    }

    public String retrieveTestParameterUsingTestCaseId(String testCaseId, String parameterName) {
        String defaultReturn = "parameter not found";
        for (TestEntity testEntity : this.testDataList) {
            if (testEntity.TestCaseId.toUpperCase().equals(testCaseId.toUpperCase())) {
                if (testEntity.TestParameters.containsKey(parameterName)) {
                    return testEntity.TestParameters.get(parameterName);
                } else {
                    return defaultReturn;
                }
            }
        }
        return defaultReturn;
    }
    
    public void pause(int milisecondsToWait) {
        try {
            Thread.sleep(milisecondsToWait);
        } catch (Exception e) {

        }
    }
    
    public String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

        return dateFormat.format(dateNow).toString();
    }
    public void generateReportDirectory()
    {

            reportDirectory = ApplicationConfig.ReportFileDirectory();
            

    }

}
