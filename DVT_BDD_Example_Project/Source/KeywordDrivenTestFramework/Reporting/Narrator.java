/*
 * Developed By: James Joubert
 * ID: 9203165141087
 * Email: jjoubert@dvt.co.za
 */
package KeywordDrivenTestFramework.Reporting;

import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.SikuliDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.reportDirectory;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author jjoubert
 */
public class Narrator extends BaseClass
{

    public TestEntity testData;
    private static final String formatStr = "%n%-24s %-20s %-60s %-25s";
    private String line = "______________________________________________________________________________________________________________________________________";
    private static String logMessage = "";
    private static int counter = 0;
    //private long totalTime = 0;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
    //private static Date startDate;
    
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extentReports;

    private String ExtentLocation;
    public static int ScreenShotCounter = 0;
    public static String ScreenShotName;

    private boolean testRailPassed = true;
    String directory;
    String extentReportDirectory = "";
    
    private static ExtentTest currentTest;
    private static ExtentTest childTest;

    //Initializes the Narrator class
    public Narrator(TestEntity testData)
    {
        this.testData = testData;
        this.setStartTime();
        directory = reportDirectory + "\\Narrator_Log.txt";
 
        extentReportDirectory = reportDirectory + "\\extentReport.html";
        //checks if file exists if not, create it
        
        try
        {

            File logfile = new File(directory);
            File reportfile = new File(extentReportDirectory);
            if (!logfile.exists())
                Files.createFile(Files.createDirectories(logfile.toPath())).toFile();
            if(!reportfile.exists())
                Files.createFile(Files.createDirectories(reportfile.toPath())).toFile();
                    
            //initializes the text file with new test class data
            counter = 0;
            PrintWriter writer = new PrintWriter(new FileWriter(logfile, true));
            //writer.println(" ");
            writer.println(String.format(formatStr, dateFormat.format(new Date()), "- [KEYS] START KEYWORD:", testData.TestCaseId, ""));
            writer.close();
        }
        catch (IOException e)
        {
            System.out.printf(e.getMessage());
        }

        System.setProperty("LatestReportFolder", reportDirectory);

        htmlReporter = new ExtentHtmlReporter(extentReportDirectory);
        htmlReporter.setAppendExisting(true);
        htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
        Locale.setDefault(Locale.ENGLISH);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        
        //Set System Info
        extentReports.setSystemInfo("OS", "Win10");
        extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
        currentTest = extentReports.createTest(testData.TestCaseId, testData.TestDescription);
        

    }

    public Narrator(String TestCaseId, String TestDescription)
    {
        this.testData = testData;
        this.setStartTime();
        directory = reportDirectory + "\\Narrator_Log.txt";
 
        extentReportDirectory = reportDirectory + "\\extentReport.html";
        //checks if file exists if not, create it
        
        
        try
        {
            File logfile = new File(directory);
            File reportfile = new File(extentReportDirectory);
            if (!logfile.exists())
                Files.createFile(Files.createDirectories(logfile.toPath())).toFile();
            if(!reportfile.exists())
                Files.createFile(Files.createDirectories(reportfile.toPath())).toFile();
                    
            //initializes the text file with new test class data
            counter = 0;
            PrintWriter writer = new PrintWriter(new FileWriter(logfile, true));
            //writer.println(" ");
            writer.println(String.format(formatStr, dateFormat.format(new Date()), "- [KEYS] START KEYWORD:", TestCaseId, ""));
            writer.close();
        }
        catch (IOException e)
        {
            System.out.printf(e.getMessage());
        }
       
        
        System.setProperty("LatestReportFolder", reportDirectory);

        htmlReporter = new ExtentHtmlReporter(extentReportDirectory);
        htmlReporter.setAppendExisting(true);
        htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
        Locale.setDefault(Locale.ENGLISH);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        
        //Set System Info
        extentReports.setSystemInfo("OS", "Win10");
        
        extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
        
        currentTest = extentReports.createTest(testCaseId,TestDescription);
        

        
    }
    
    public Narrator()
    {
        
    }


    public String getData(String parameterName)
    {
        String returnedValue = this.testData.TestParameters.get(parameterName);

        if (returnedValue == null)
        {
            Narrator.logError(" Parameter ' " + parameterName + " ' not found");
            returnedValue = "";
        }

        return returnedValue;
    }

    //Takes the screenshot
    public static boolean takeScreenShot(boolean testStatus, String message)
    {
        if (SeleniumDriverInstance != null && SeleniumDriverInstance.isDriverRunning())
        {
            SeleniumDriverInstance.takeScreenShot(++counter + " ", !testStatus);
            return true;
        }
        else if (SikuliDriverInstance != null && SikuliDriverInstance.isDriverRunning())
        {
            SikuliDriverInstance.TakeScreenshot(++counter + " ", !testStatus);
            return true;
        }
        else if (AppiumDriverInstance != null && AppiumDriverInstance.isDriverRunning())
        {
            AppiumDriverInstance.takeScreenShot(++counter + " ", !testStatus);
            return true;
        }
        return false;
    }

    //Used when a test passes
    //Writes to the text file and writes the html file
    public void stepPassed(String message)
    {
        ScreenShotCounter++;

        logMessage = formatMessage(message);
        logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);
        
        takeScreenShot(true, logMessage);
        if(isCucumberTesting)
        {
            Reporter.addStepLog(message);
            
          
        }
        else
            currentTest.log(Status.PASS,message);
    }
    
     public void stepPassedWithScreenShot(String message)
    {
        try
        {

            ScreenShotCounter++;

            logMessage = formatMessage(message);
            logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);

            takeScreenShot(true, logMessage);
            if(isCucumberTesting)
            {       //TODO fix screenshot
                Reporter.addStepLog(message);
                Reporter.addScreenCaptureFromPath(getRelativeScreenshotPath());
  
            }
            else
            {
                currentTest.log(Status.PASS,message);
                currentTest.addScreenCaptureFromPath(getRelativeScreenshotPath());
            }

        }
        catch(Exception ex)
        {
            /// TO-DO Add Exception Hanlding
            System.out.println("");
        }

    }


    //Used where a test fails
    //Writes to the  text file and writes to the html file
    public TestResult testFailed(String message)
    {
        try
        {
            ScreenShotCounter++;
            ScreenShotName = ScreenShotCounter + "";
            //takes sceenshot
            if(!takeScreenShot(false, ScreenShotName))
            {
                logError("Failed to take a screenshot.");
            }

            logMessage = formatMessage(message);
            
            currentTest.fail("Failed: " + message);
            
                currentTest.addScreenCaptureFromPath(getRelativeScreenshotPath());
            
            extentReports.flush();
            //Writes info to the text file

            logFailure(" STEP " + ++counter + ":" + logMessage);

            testRailPassed = false;
        }
        catch(Exception ex)
        {
            //failed to log extent failure
        }

        //returns results
        return new TestResult(testData, Enums.ResultStatus.FAIL, message, this.getTotalExecutionTime());
    }
    
    public void testFailedCucumber(String message)
    {
        try
        {
            ScreenShotCounter++;
            ScreenShotName = ScreenShotCounter + "";
            //takes sceenshot
            if(!takeScreenShot(false, ScreenShotName))
            {
                logError("Failed to take a screenshot.");
            }

            logMessage = formatMessage(message);
            
//            currentTest.fail("Failed: " + message);
            
            if(isCucumberTesting)
            {       //TODO fix screenshot
                Reporter.addStepLog(message);
                Reporter.addScreenCaptureFromPath(getRelativeScreenshotPath());
            }
            else
            {
                currentTest.addScreenCaptureFromPath(getRelativeScreenshotPath());
            }
            

            extentReports.flush();
            //Writes info to the text file

            logFailure(" STEP " + ++counter + ":" + logMessage);

            testRailPassed = false;
        }
        catch(Exception ex)
        {
            //failed to log extent failure
        }

    }

    //Use at the end of a test class when everything passes
    //Writes to the text file and writes the html file
    public TestResult finalizeTest(String message)
    {
        try
        {
            ScreenShotCounter++;
            screenshotPath = "" + ScreenShotCounter;
            logMessage = formatMessage(message);

            //takes sceenshot
            takeScreenShot(true, screenshotPath);

            currentTest.pass("Passed: " + message);

            currentTest.addScreenCaptureFromPath(getRelativeScreenshotPath());

    //        if(testRailPassed)
    //        {
    //            testRailInstance.updateTestResult(LogStatus.PASS.toString());
    //        }
    //        else
    //        {
    //            testRailInstance.updateTestResult(LogStatus.FAIL.toString());
    //        }
            extentReports.flush();
            copyReportFileForJenkins();
        }
        catch(Exception ex)
        {
            //failed to log extent failure
        }
        //returns results
        return new TestResult(testData, Enums.ResultStatus.PASS, message, this.getTotalExecutionTime());
    }

    public void extractedParameters(TestEntity testData)
    {
        ArrayList keys = new ArrayList();
        ArrayList values = new ArrayList();
        ArrayList status = new ArrayList();
        if (testData.ExtractedParameters != null)
        {
            logMessage = "Extracted Parameters:";

            String extractedParameters = "<span style='font-weight:bold;font-family: Georgia;'>" + logMessage + "</span></br><table>";

            for (String key : testData.ExtractedParameters.keySet())
            {
                keys.add(key);
                for (String value : testData.ExtractedParameters.get(key))
                {
                    status.add(testData.ExtractedParameters.get(key).get(1));
                    values.add(value);
                    break;
                }
            }

            for (int i = 0; i < keys.size(); i++)
            {
                if (status.get(i).equals("PASS"))
                {
                    extractedParameters += "<tr style='background: #60A84D;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                }
                else if (status.get(i).equals("FAIL"))
                {
                    extractedParameters += "<tr style='background: #FF4536;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                }
                else if (status.get(i).equals("WARNING"))
                {
                    extractedParameters += "<tr style='background: #FF8E1A;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                }
                else
                {
                    extractedParameters += "<tr><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                }
            }

            extractedParameters += "</table>";

            currentTest.log(Status.INFO,extractedParameters);
        }
    }

    //Creating a new text file
    public static void createNewTextFile()
    {
        //Creates the file and initializes the header
        try
        {
            String directory = reportDirectory + "\\Narrator_Log.txt";
            File file = new File(directory);
            file.createNewFile();
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(String.format(formatStr, "", "-- NARRATOR LOG FILE --", "", ""));
            writer.close();
        }
        catch (IOException e)
        {
            System.out.printf(e.getMessage());
        }
    }

    //Returns data from the spreadsheet
//    public String getData(String data) {
//        return testData.getData(data);
//    }
    //Checks the length of the message
    public String formatMessage(String message)
    {
        String newMessage = message.replace("\\.", "");

        newMessage = newMessage.replace(":", "");

        newMessage = newMessage.replace("\\", "");

        newMessage = newMessage.replace("/", "");

        newMessage = newMessage.replace("\\*", "");

        newMessage = newMessage.replace("\\?", "");

        newMessage = newMessage.replace("\"", "");

        newMessage = newMessage.replace("<", "");

        newMessage = newMessage.replace(">", "");

        newMessage = newMessage.replace("\\|", "");

        try
        {
            //checks if message is longer than 60 characters, if so then remove a word. LOOP
            while (newMessage.length() > 60)
            {
                newMessage = newMessage.split(" ", 2)[1];
            }
        }
        catch (Exception ex)
        {
            logError("Failed to reduce message length - " + ex.getMessage());
        }
        return newMessage;
    }


    public static void logError(String error)
    {
        writeToLogFile("- [EROR] " + error);
    }

    public static void logDebug(String debug)
    {
        writeToLogFile("- [DBUG] " + debug);

    }

    public static void logPass(String failure)
    {
        writeToLogFile("- [PASS] " + failure);

    }

    public static void logFailure(String failure)
    {
        writeToLogFile("- [FAIL] " + failure);

    }

    public static void logInfo(String info)
    {
        writeToLogFile("- [INFO] " + info);

    }

    private static void writeToLogFile(String logMessage)
    {
        String directory = reportDirectory + "\\Narrator_Log.txt";
        File file = new File(directory);

        if (!file.exists())
        {
            createNewTextFile();
        }

        //Writes info to the text file        
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(String.format(formatStr, dateFormat.format(new Date()), logMessage, "", ""));
            writer.close();
        }
        catch (IOException e)
        {
            System.out.printf(e.getMessage());
        }
    }

    public String convertPNGToBase64(String imageFilePath)
    {
        String base64ReturnString = "";

        try
        {
            out.println("[INFO] Converting error screenshot to Base64 format...");
            File image = new File(imageFilePath);

            FileInputStream imageInputStream = new FileInputStream(image);

            byte imageByteArray[] = new byte[(int) image.length()];

            imageInputStream.read(imageByteArray);

            base64ReturnString = Base64.encodeBase64String(imageByteArray);

            out.println("[INFO] Converting completed, image ready for embedding.");
        }
        catch (Exception ex)
        {
            out.println("[ERROR] Failed to convert image to Base64 format - " + ex.getMessage());
        }

        return base64ReturnString;
    }

   

    public void AppendHTML()
    {
        //Finds Location of Report

        ExtentLocation = reportDirectory + "\\extentReport.html";

        //Appends Extent with Logo
        try
        {
            //Reads in the html file    
            File input = new File(ExtentLocation);
            Document doc = Jsoup.parse(input, "UTF-8", "");
            //Edits the Broken Time display, to show an Image instead
            Element Panel = doc.getElementsByAttributeValue("class", "card suite-total-time-overall").first();
            Panel.text("");

            String workingDir = System.getProperty("user.dir");
            //Convert the Logo to Base64
            String Image64 = convertPNGToBase64(workingDir + "\\DVT Logo.png");
            //Embeds the Base64Image to the HTML
            Panel.appendElement("img").attr("src", "data:image/png;base64," + Image64);

            //Removes Redundant tabs
//        String[] categories = {"categories-view","exceptions-view","testrunner-logs-view"};
//        
//            for (int i = 0; i < categories.length; i++)
//            {
//                Element Category = doc.getElementsByAttributeValue("class", categories[i]).first();
//                Category.remove();
//            }
            //Writes the Changes over the old file
            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ExtentLocation), "UTF-8"));
//        System.out.println("\n" + doc.outerHtml());
            htmlWriter.write(doc.toString());
            htmlWriter.flush();
            htmlWriter.close();

        }
        catch (Exception e)
        {
            logError("Failed to Create an appened ExtentReport, error message -  " + e.getMessage());
        }
    }

    public void copyReportFileForJenkins()
    {
        try
        {
            File extentReport = new File(extentReportDirectory);

            File extentReportDependancies = new File(reportDirectory + "\\extentreports");

            File htmlReportDirectory = new File("HTMLTestReport");

            if (!htmlReportDirectory.exists())
            {
                htmlReportDirectory.mkdirs();
            }

            FileUtils.copyFileToDirectory(extentReport, htmlReportDirectory);

            if (extentReportDependancies.exists())
            {
                FileUtils.copyDirectory(extentReportDependancies, htmlReportDirectory);
            }

        }
        catch (Exception ex)
        {
            err.println("[ERROR] Failed to copy extentReport file to HTML Test Report Directory - " + ex.getMessage());
        }
    }

}
