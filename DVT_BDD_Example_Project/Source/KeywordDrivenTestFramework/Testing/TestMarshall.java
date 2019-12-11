/*
 * Changed made by James Joubert
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Testing;

import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.inputFilePath;
import static KeywordDrivenTestFramework.Core.BaseClass.reportGenerator;
import static KeywordDrivenTestFramework.Core.BaseClass.requiresBrowser;
import static KeywordDrivenTestFramework.Core.BaseClass.testCaseId;
import static KeywordDrivenTestFramework.Core.BaseClass.testDataList;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Reporting.ReportGenerator;
import KeywordDrivenTestFramework.Reporting.TestReportEmailerUtility;
import KeywordDrivenTestFramework.Testing.TestClasses.*;
import KeywordDrivenTestFramework.Utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import java.io.*;
import static java.lang.System.err;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author fnell
 * @editir jjoubert
 */
public class TestMarshall extends BaseClass
{

    // Handles calling test methods based on test parameters , instantiates Selenium Driver object   
    ExtentReports extentReports;
    ExcelReaderUtility excelInputReader;
    CSVReportUtility cSVReportUtility;
    TestReportEmailerUtility reportEmailer;
    //James removed(Moved to Narrator)
//    PrintStream errorOutputStream;
//    PrintStream infoOutputStream;
    private String dateTime;
    private int totalIgnore = 0;

    public TestMarshall()
    {
        inputFilePath = ApplicationConfig.InputFileName();
        testDataList = new ArrayList<>();
        excelInputReader = new ExcelReaderUtility();
        currentBrowser = ApplicationConfig.SelectedBrowser();
        reportGenerator = new ReportGenerator();
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        DataBaseInstance = new DataBaseUtility();
        AutoItInstance = new AutoItDriverUtility();
//        testRailInstance = new TestRailUtility(ApplicationConfig.projectName(), ApplicationConfig.runName());
        performanceMonitor = new PerformanceMonitor();
        this.generateReportDirectory();

    }

    // Implementation  for Appium only
    public TestMarshall(TestEntity testdata, boolean cucumber)
    {

        testData = testdata;
        // For screenshot
        testCaseId = testdata.TestCaseId;
        isCucumberTesting = cucumber;

        reportGenerator = new ReportGenerator();
        this.generateReportDirectory();

        currentBrowser = ApplicationConfig.SelectedBrowser();
            AppiumDriverInstance = new AppiumDriverUtility();

        performanceMonitor = new PerformanceMonitor();
        reportGenerator.creatingNewTextFile();
//        testRailInstance = new TestRailUtility(ApplicationConfig.projectName(), ApplicationConfig.runName());

    }

    public TestMarshall(String inputFilePathIn)
    {
        inputFilePath = inputFilePathIn;
        testDataList = new ArrayList<>();
        excelInputReader = new ExcelReaderUtility();
        cSVReportUtility = new CSVReportUtility(inputFilePath);
        cSVReportUtility.createCSVReportDirectoryAndFile();
        currentBrowser = ApplicationConfig.SelectedBrowser();
        reportGenerator = new ReportGenerator(inputFilePath, ApplicationConfig.ReportFileDirectory());
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        DataBaseInstance = new DataBaseUtility();
        AutoItInstance = new AutoItDriverUtility();
//        testRailInstance = new TestRailUtility(ApplicationConfig.projectName(), ApplicationConfig.runName());
        performanceMonitor = new PerformanceMonitor();
        this.generateReportDirectory();

    }

    /**
     * Constructor for Selenium
     *
     * @param featureName
     * @param browserTypeOverride
     * @param cucumber
     */
    public TestMarshall(String featureName, Enums.BrowserType browserTypeOverride, boolean cucumber)
    {
        inputFilePath = featureName;
        currentBrowser = browserTypeOverride;
        reportGenerator = new ReportGenerator();
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        DataBaseInstance = new DataBaseUtility();
        isCucumberTesting = cucumber;
        performanceMonitor = new PerformanceMonitor();
        this.generateReportDirectory();
        
        // 
//        ensureNewBrowserInstance();

    }

    public TestMarshall(String inputFilePathIn, Enums.BrowserType browserTypeOverride)
    {
        inputFilePath = inputFilePathIn;
        testDataList = new ArrayList<>();
        excelInputReader = new ExcelReaderUtility();
        cSVReportUtility = new CSVReportUtility(inputFilePath);
        cSVReportUtility.createCSVReportDirectoryAndFile();
        currentBrowser = browserTypeOverride;
        reportGenerator = new ReportGenerator(inputFilePath, ApplicationConfig.ReportFileDirectory());
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        DataBaseInstance = new DataBaseUtility();
        performanceMonitor = new PerformanceMonitor();
        this.generateReportDirectory();

    }

    int numberOfCase = 0;
    public void runCucumberDriven(String keyword)
    {

        if (keyword.isEmpty())
        {
            Narrator.logError("Test data object is empty - spreadsheet not found or is empty");
        }
        else
        {
//CREATE NEW REPORT FILE
            reportGenerator.creatingNewTextFile();

            //Stops Running Appuim Server
            stopAppiumServerIfExists();

            testCaseId = keyword;

            Narrator.logDebug("Executing test - " + testData.TestMethod + " | " + numberOfCase + " of " + testDataList.size());

            switch (keyword)
            {
                case "Login":
                    break;

                case "":

                    break;
            }

        }
    }

    public void runKeywordDrivenTests() throws FileNotFoundException
    {
        

        int numberOfTest = 0;

        testDataList = loadTestData(inputFilePath);

        if (testDataList.size() < 1)
        {
            Narrator.logError("Test data object is empty - spreadsheet not found or is empty");
        }
        else
        {
            //CREATE NEW REPORT FILE
            reportGenerator.creatingNewTextFile();
            //James added 
            if (requiresBrowser == true)
            {
                CheckBrowserExists();
            }
            Narrator.takeScreenShot(true, "init");
            // Each case represents a test keyword found in the excel spreadsheet
            for (TestEntity testData : testDataList)
            {
                numberOfTest++;
                testCaseId = testData.TestCaseId;
                // Make sure browser is not null - could have thrown an exception and terminated
                //James added // Steve edited
//                if (requiresBrowser == true)
//                {
//                    CheckBrowserExists();
//                }
                
                //Stops Running Appuim Server 
                 stopAppiumServerIfExists();
                 
                 
                 
                 performanceMonitor.startMonitoring("This Report was generated for the following scenario - " + resolveScenarioName());
                
                // Skip test methods and test case id's starting with ';'
                if (!testData.TestMethod.startsWith(";") && !testData.TestCaseId.startsWith(";"))
                {
                    Narrator.logDebug("Executing test - " + testData.TestMethod + " | " + numberOfTest + " of " + testDataList.size());
                    
                   
                    
                    try
                    {
                        ClassPath.ClassInfo testClassInfo  = getKeywordTestClass(testData.TestMethod);                    
                    
                        Class testClass = testClassInfo.load();
                        
                        Constructor constructor = testClass.getConstructor(TestEntity.class);
                        
                        Object testClassInstance = constructor.newInstance(testData);
                        
                        if(testClassInstance.getClass().getAnnotation(KeywordAnnotation.class).createNewBrowserInstance() && numberOfTest > 1)
                            ensureNewBrowserInstance();
                        
                        
                        // Set up the Performance Monitoring
                        performanceMonitor.newPage(testData.TestCaseId);
                                              
                        Method executeTestMethod = testClassInstance.getClass().getMethod("executeTest");

                        reportGenerator.addResult((TestResult) executeTestMethod.invoke(testClassInstance));    
                        
                        performanceMonitor.endPage();
                       
                    }
                    catch(Exception ex)
                    {
                        err.println("[ERROR] Critical Error during keyword execution - " + testData.TestMethod + " - error: " +ex.getMessage());
                        ex.printStackTrace();
                    }
                        
                    

                    reportTextFile(numberOfTest);
                    Narrator.logDebug("Continuing to next test method");
                }
                else
                {
                    totalIgnore++;
                    reportGenerator.writeToFile4(numberOfTest, testDataList.size(), totalIgnore);

                }
            }
            
            
             //Generate performance log report
             
             performanceMonitor.endMonitoring(); 
            
            if (SeleniumDriverInstance != null && SeleniumDriverInstance.isDriverRunning())
            {
                SeleniumDriverInstance.shutDown();
            }
//            if (AppiumDriverInstance != null && AppiumDriverInstance.isDriverRunning())
//            {
//                AppiumDriverInstance.shutDown();
//            }
            
             //Stops Running Appuim Server 
             stopAppiumServerIfExists();
             
            

            reportGenerator.generateTestReport();
            reportEmailer = new TestReportEmailerUtility(reportGenerator.testResults);
            reportEmailer.SendResultsEmail();

 
        }
    }


    public ClassPath.ClassInfo getKeywordTestClass(String keywordName)
    {
       try
       {
           
            //Get list of all loaded classes for the package - defined at runtime - we need to be able to isolate just the TestClasses
            // in order to extract the one matching the keyword to be executed
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());                  
            
            //Next we set up Predicates (a type of query in java) to isolate the list of classes to only those pertaining to the framework
            // i.e. no dependencies included 
            ImmutableSet<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive("KeywordDrivenTestFramework.Testing");
            
            //We then filter the classes to only those who have the required annotations - annotations used to add meta
            //data to the TestClasses sothat we can scan them to read their Keywords - this uses Lambda notation only available in Java 8 and above.
            Predicate<ClassPath.ClassInfo> hasAnnotationPredicate = c-> c.load().isAnnotationPresent(KeywordAnnotation.class);
            Stream<ClassPath.ClassInfo> annotatedClasses =  allClasses.stream().filter(hasAnnotationPredicate);
            
            //The filtered list is then queried a second time in order to retrieve the valid TestClass based on the keywordName
            Predicate<ClassPath.ClassInfo> checkKeywordPredicate = c-> c.load().getAnnotation(KeywordAnnotation.class).Keyword().equals(keywordName);
            ClassPath.ClassInfo  testClass = annotatedClasses.filter(checkKeywordPredicate).findFirst().get();
            
            if(testClass == null)
            {
                err.println("[ERROR] Failed to resolve TestClass for keyword - " + keywordName + " - error: Keyword not found");
            }
            
            return testClass;
         
       }
       catch(Exception ex)
       {
           err.println("[ERROR] Failed to resolve TestClass for keyword - " + keywordName + " - error: " + ex.getMessage());
           
           return null;
       }
    }
    public static void stopAppiumServerIfExists()
    {
        if (AppiumDriverInstance != null && AppiumDriverInstance.isDriverRunning())
        {
            switch (currentPlatform)
            {
                case Android:
                {
                    AppiumDriverInstance.stopAndroidServer();
                    _isRemote = false;
                    AppiumDriverUtility.isCMDRunning = false;
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (Exception e)
                    {
                        Narrator.logError("[ERROR] - " + e.getMessage());
                    }
                    break;
                }
                case iOS:
                {
                    AppiumDriverUtility.Driver.removeApp(Enums.DeviceConfig.Santam_iOS.appPackage);
                    AppiumDriverUtility.Driver.installApp(Enums.DeviceConfig.Santam_iOS.ApplicationFilePath + "\\" + Enums.DeviceConfig.Santam_iOS.ApplicationName);
                    AppiumDriverUtility.Driver.launchApp();
                    break;
                }
            }
        }
        
        //WHY ARE WE DOING THIS?!
        if (SeleniumDriverInstance.isDriverRunning())
        {
            //SeleniumDriverInstance.shutDown();
        }
    }

    private List<TestEntity> loadTestData(String inputFilePath)
    {
        return excelInputReader.getTestDataFromExcelFile(inputFilePath);
    }

    public void loadCucumberData(DataTable tableData, String keyword, Scenario scenario)
    {
        TestEntity data = new TestEntity();
        data.TestCaseId = scenario.getName();
        data.TestMethod = keyword;
        data.TestDescription = getFeaturename(scenario.getId()).concat(" - ").concat(scenario.getName());

        if(tableData != null)
        {
            for (int i = 0; i < tableData.raw().size() - 1; i++)
            {
                for (int j = 0; j < tableData.raw().get(i).size(); j++)
                {
                    data.addParameter(tableData.raw().get(0).get(j), tableData.raw().get(i + 1).get(j));
                }
            }
        }
        
        if (testDataList == null)
        {
            testDataList = new ArrayList<>();
        }
        testDataList.add(data);

    }

    public static String getFeaturename(String scenarioId)
    {
        return scenarioId.split(";")[0].toUpperCase();
    }

    public static void CheckBrowserExists()
    {
        if (SeleniumDriverInstance == null)
        {
            SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
            SeleniumDriverInstance.startDriver();
        }

        if (!SeleniumDriverInstance.isDriverRunning())
        {
            SeleniumDriverInstance.startDriver();
        }
    }

    public static void ensureNewBrowserInstance()
    {
        if (SeleniumDriverInstance.isDriverRunning())
        {
            SeleniumDriverInstance.shutDown();
        }
        SeleniumDriverInstance.startDriver();
    }

    public String generateDateTimeString()
    {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        dateTime = dateFormat.format(dateNow).toString();
        return dateTime;
    }

    public void generateReportDirectory()
    {

            reportDirectory = ApplicationConfig.ReportFileDirectory() + resolveScenarioName() + "_" + this.generateDateTimeString();
            String[] reportsFolderPathSplit = TestMarshall.reportDirectory.split("\\\\");
            TestMarshall.currentTestDirectory = ApplicationConfig.ReportFileDirectory() + "\\" + reportsFolderPathSplit[reportsFolderPathSplit.length - 1];


    }

    //James removed(Moved to Narrator)
//    public void redirectOutputStreams() {
//        try {
//            File reportDirectoryFile = new File(reportDirectory);
//            File errorFile = new File(reportDirectory + "\\" + "ErrorTestLog.txt");
//            File infoFile = new File(reportDirectory + "\\" + "InfoTestLog.txt");
//            File narrator = new File(reportDirectory + "\\Narrator_Log.txt");
//            reportDirectoryFile.mkdirs();
//
//            errorOutputStream = new PrintStream(errorFile);//errorFile
//            infoOutputStream = new PrintStream(infoFile);//infoFile
//
//            System.setOut(infoOutputStream);
//            
//            System.setErr(errorOutputStream);
//            
//        } catch (FileNotFoundException ex) {
//            Narrator.errorLog(" could not create log files - " + ex.getMessage());
//        }
//    }
    //James removed (Moved to Narrator)
//    public void flushOutputStreams() {
//
//        errorOutputStream.flush();
//        infoOutputStream.flush();
//        
//        errorOutputStream.close();
//        infoOutputStream.close();
//
//    }
    public void reportTextFile(int numberOfTests)
    {
        reportGenerator.writeToFile4(numberOfTests, testDataList.size(), totalIgnore);
    }

}
