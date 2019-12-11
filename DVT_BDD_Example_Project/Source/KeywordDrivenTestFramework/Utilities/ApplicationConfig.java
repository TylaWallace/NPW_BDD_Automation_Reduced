/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Entities.Enums.BrowserType;
import KeywordDrivenTestFramework.Reporting.Narrator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author fnell
 */
public final class ApplicationConfig 
{

    
    
    public Properties appConfig;
    private String appConfigFilePath = "config.properties";
    
    private static String ExcelInputFile, ReportFileDirectory, ReportTextFileDirectory, browserTypeConfig, MyPageUrl, mailingList, seeTestExecutablePath;
    private static String emailSender, emailHost, projectName, runName;
    private static int WaitTimeout;
    public static BrowserType browserType;
    
    public static int WaitTimeout()
    {
        return WaitTimeout;
    }
    
    public static String InputFileName()
    {
        return ExcelInputFile;
    }
    
    public static String SeeTestExecutablePath()
    {
        return seeTestExecutablePath;
    }
    
    public static String MyPageUrl()
    {
        return MyPageUrl;
    }
  
    public static String ReportFileDirectory()
    {
        return ReportFileDirectory;
    }
    
    public static String ReportTextFileDirectory()
    {
        return ReportTextFileDirectory;
    }
    
    public static BrowserType SelectedBrowser()
    {
        return browserType;
    }
    public static String EmailHost()
    {
        return emailHost;
    }
    
    public static String[] MailingList() 
    {
        return mailingList.split(";");
    }
    
    public static String EmailSender()
    {
        return emailSender;
    }

    public static String projectName()
    {
        return projectName;
    }

    public static String runName()
    {
        return runName;
    }
   
    
    public ApplicationConfig()
    {
        
        try
        {
            loadConfigurationSettings();
        }
        catch(Exception e)
        {
          // One or more of the appConfig values could not be found in the config file - 
          // Reload default values and read from file. 
          generateDefaultConfigurationFile();
          loadExistingConfigurationFile();
          loadConfigurationSettings();
        }
        
    }
    
    private void loadConfigurationSettings()
    {
        if(!loadExistingConfigurationFile())
        {
            generateDefaultConfigurationFile();
        }
        try
        {
            ExcelInputFile = appConfig.getProperty("ExcelInputFile");
            ReportFileDirectory = appConfig.getProperty("ReportFileDirectory");
            ReportTextFileDirectory = appConfig.getProperty("ReportTextFileDirectory");
            MyPageUrl = appConfig.getProperty("MyPageUrl");
            
            mailingList = appConfig.getProperty("MailingList");
            emailSender = appConfig.getProperty("EmailSender");
            emailHost = appConfig.getProperty("EmailHost");
            
            WaitTimeout = Integer.parseInt(appConfig.getProperty("WaitTimeout"));
            
            seeTestExecutablePath = appConfig.getProperty("SeeTestExecutablePath");
            projectName = appConfig.getProperty("ProjectName");
            runName = appConfig.getProperty("RunName");
            browserType = resolveBrowserType();
        }
        catch(Exception e)
        {
            Narrator.logError(" Loading application configuration...see stack trace:");
            e.printStackTrace();
        }       
                
    }
    
    
    public BrowserType resolveBrowserType()
    {
        browserTypeConfig = appConfig.getProperty("BrowserType");
        
        switch(browserTypeConfig)
        {
            case "IE":
                return BrowserType.IE;
            case "FireFox":
                return BrowserType.FireFox;
            case "Chrome":
                return BrowserType.Chrome;
            case "Safari":
                return BrowserType.Safari;
            default: 
                return BrowserType.IE;
        }
    }
    
    public BrowserType resolveBrowserType(String browserType) 
    {
        browserTypeConfig = browserType;

        switch (browserTypeConfig) {
            case "IE":
                return BrowserType.IE;
            case "FireFox":
                return BrowserType.FireFox;
            case "Chrome":
                return BrowserType.Chrome;
            case "Safari":
                return BrowserType.Safari;
            default:
                return BrowserType.IE;
        }
    }
    
    
    private void generateDefaultConfigurationFile()
    {
        try
        {
           appConfig = new Properties();
           appConfig.setProperty("ExcelInputFile", "Keyword Input.xls");
           appConfig.setProperty("ReportFileDirectory", "KeywordDrivenTestReports\\");
           appConfig.setProperty("BrowserType","IE");
           appConfig.setProperty("WaitTimeout", "15");
           appConfig.setProperty("MyPageUrl","");
           
           appConfig.setProperty("MailingList", "fnell@dvt.co.za");
           appConfig.setProperty("EmailSender" , "Automation@etana.co.za");
           appConfig.setProperty("EmailHost", "localhost");
           
           appConfig.store(new FileOutputStream(appConfigFilePath),null);
           
        }
        catch(Exception e)
        {
            Narrator.logError(" Loading default configuration...see stack trace:");
            e.printStackTrace();
        }
    }
    private boolean loadExistingConfigurationFile()
    {
        try
        {  
            if(appConfig == null)
            {
                appConfig = new Properties();
            }
           appConfig.load(new FileInputStream(appConfigFilePath));
           return true;
           
        }
        catch(Exception e)
        {
            Narrator.logError("Configuration file not found, reverting to default configuration...see stack trace:");
            e.printStackTrace();
            Narrator.logError("Loading default configuration");
            return false;
        }
    }
    
}
