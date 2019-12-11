/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Reporting;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.DataColumn;
import KeywordDrivenTestFramework.Entities.DataRow;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import static java.lang.System.err;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author fnell
 * @editir jjoubert
 */
public class TestReportEmailerUtility extends BaseClass {

    ApplicationConfig appConfig = new ApplicationConfig();
    int testCount = 0;
    int failCount = 0;
    int passCount = 0;
    long totalSeconds = 0;
    long totalMinutes = 0;
    long totalHours = 0;
    Calendar calendar = Calendar.getInstance();
    Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
    
    Enums.BrowserType browser = this.currentBrowser;

    List<TestResult> testResults;
    String[] emailRecipients;
    String emailSender;
    String emailSubject;
    String emailHost;
    List<String> dataColumnName;

    long testDuration;

    StringBuilder HtmlEmailBody;
    TestResult testResult;

    public TestReportEmailerUtility(List<TestResult> _testResults/*, String inputFilePath*/) {
        this.testResults = _testResults;
        this.emailRecipients = ApplicationConfig.MailingList();
        this.emailSender = ApplicationConfig.EmailSender();
        this.emailHost = ApplicationConfig.EmailHost();
        //inputFilePath = inputFilePath;
        HtmlEmailBody = new StringBuilder();

        this.CalculateTestStatistics();
    }

    
    public TestReportEmailerUtility() 
    {
       
    }
    public String GenerateTestReport() {
        
        
        GenerateDatabase();
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateTime = dateFormatter.format(calendar.getTime());

        HtmlEmailBody.append("<!doctype html>\n");
        HtmlEmailBody.append("<html lang='en'>\n");

        HtmlEmailBody.append("<head>\n");
        HtmlEmailBody.append("<meta charset='utf-8'>\n");
        HtmlEmailBody.append("<title style=\"font-family:\"Courier New\";\">Automation Test Report - " + resolveScenarioName() + "</title>\n");
        
        HtmlEmailBody.append( "<script src=\"http://code.jquery.com/jquery-1.5.1.min.js\" type=\"text/javascript\"></script>\n");
        HtmlEmailBody.append("<script type=\"text/javascript\">\n") ;
        HtmlEmailBody.append("$(document).ready(function () {$('.testResultRowHeader').click(function () {$(this).next('.testCaseResultRows').slideToggle(200);});});\n");        
        HtmlEmailBody.append("</script>\n");
        HtmlEmailBody.append("</head>\n\n");
        
        HtmlEmailBody.append("<body style=\"background-color:#EBEBEB;\" text=\"#FFA500\">\n");    
        
        HtmlEmailBody.append("<table width=\"100%\" style=\"background-color:#151B54;\">");
            HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td>\n");
                    HtmlEmailBody.append("<div align=\"center\"><img src=\"http://s24.postimg.org/b3tub65k5/DVT.png\" alt=\"Smiley face\" height=\"80\"\"></div>");
                    HtmlEmailBody.append("<h1 align=\"center\" style=\"font-family:courier new;\">Automation Test Report - " + resolveScenarioName() + " On - " + currentEnvironment.toString() + " Environment</h1>\n");
                    HtmlEmailBody.append("<h3 align=\"center\" style=\"font-family:courier new;\">Report date - " + dateTime + "</h2>\n");
                    HtmlEmailBody.append("<h3 align=\"center\" style=\"font-family:courier new;\">Browser Type: " + browser + "</h2>\n");
                    //HtmlEmailBody.append( "<h3>Test statistics</h3>\n" );
                HtmlEmailBody.append("<td>\n");
            HtmlEmailBody.append("</tr>\n");
        HtmlEmailBody.append("</table>\n");
        HtmlEmailBody.append("<br/>\n");
        
        HtmlEmailBody.append("<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse; font-family:courier new; border:2px solid black;\">\n");
        HtmlEmailBody.append("<tr>\n");
        HtmlEmailBody.append("<td align=\"center\" colspan=\"4\" style=\"background-color:#737CA1; color:#E5E4E2;font-size: 18pt;border:2px solid black;\"><b>Test Statistics</b></td>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#FFFF00;font-size:17px;\">Total Tests</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#57E964;font-size:17px;\">Total Passed</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#C11B17;font-size:17px;\">Total Failed</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Total Runtime</th>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
        HtmlEmailBody.append("<td style=\"border:1px solid black;text-align:center;font-size:17px;background-color:#79BAEC;color:#000000;\">" + this.testCount + "</td>\n");
        HtmlEmailBody.append("<td style=\"border:1px solid black;text-align:center;font-size:17px;background-color:#79BAEC;color:#000000;\">" + this.passCount + "</td>\n");
        HtmlEmailBody.append("<td style=\"border:1px solid black;text-align:center;font-size:17px;background-color:#79BAEC;color:#000000;\">" + this.failCount + "</td>\n");
        HtmlEmailBody.append("<td style=\"border:1px solid black;text-align:center;font-size:17px;background-color:#79BAEC;color:#000000;\">" + this.totalHours + " hour(s), " + this.totalMinutes + " minute(s), " + this.totalSeconds + " second(s)</td>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("</table>\n");

        HtmlEmailBody.append("<br/>\n");
        
        HtmlEmailBody.append("<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse;font-family:courier new; border:2px solid black;\">\n");

        HtmlEmailBody.append("<tr>\n");
        HtmlEmailBody.append("<td align=\"center\" colspan=\"6\" style=\"background-color:#737CA1;color:#E5E4E2; font-size:18pt;border:2px solid black;\"><b>Results Summary</b></td>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Test Case ID</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Keyword</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Test Description</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Pass \\ Fail</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Test Message</th>\n");
        HtmlEmailBody.append("<th style=\"border:1px solid black;background-color:#B2B2B2;color:#ffffff;font-size:17px;\">Test Duration</th>\n");
        HtmlEmailBody.append("</tr>\n");

        for (TestResult testResult : testResults) {
            String bgColourString = this.getBackGroundColourForTestStatus(testResult.testStatus);  
            String testStatus = testResult.testStatus.toString();            
            String extractedCheck = "";
            String pointerStyle = "";
            
            if(testResult.testData.ExtractedParameters != null)
            {
                extractedCheck = "*";
                pointerStyle = "style=\"cursor: pointer;\"";
            }
            
            HtmlEmailBody.append("<tbody "+pointerStyle+" class=\"testResultRowHeader\" width=\"100%\">\n");
            HtmlEmailBody.append("<tr style=\"outline: thin solid black;\">\n");  
            
            HtmlEmailBody.append("<td align=\"center\" bgcolor=\"" + bgColourString + "\" style=\"border-left:1px solid black;color:#000000;font-size:17px;\"><b>" + testResult.testData.TestCaseId + extractedCheck + "</b></td>\n");
            HtmlEmailBody.append("<td align=\"center\" style=\"border:1px solid black;font-size:17px;background-color:#79BAEC;color:#000000;\">" + testResult.testData.TestMethod + "</td>\n");
            HtmlEmailBody.append("<td align=\"center\" style=\"border:1px solid black;font-size:17px;background-color:#79BAEC;color:#000000;\">" + testResult.testData.TestDescription + "</td>\n");
            HtmlEmailBody.append("<td align=\"center\" style=\"border:1px solid black;font-size:17px;background-color:#79BAEC;color:#000000;\">"+testStatus+"</td>\n");

            HtmlEmailBody.append("<td align=\"center\" style=\"border:1px solid black;font-size:17px;background-color:#79BAEC;color:#000000;\">" + testResult.errorMessage + "</td>\n");

            HtmlEmailBody.append("<td align=\"center\" style=\"border:1px solid black;font-size:17px;background-color:#79BAEC;color:#000000;\">" + testResult.calculateFormattedTestTime() + "</td>\n");
            HtmlEmailBody.append("</tr>\n");
            HtmlEmailBody.append("</tbody>\n");
            
            if(testResult.testData.ExtractedParameters != null)
            {
                int extractedSize = testResult.testData.ExtractedParameters.size();
                String extractedBGColourString; 
                double cellWidth = 100/extractedSize;

                HtmlEmailBody.append("<tbody colspan=\"6\" width=\"100%\" class=\"testCaseResultRows\" style = \"display : none; border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");
                HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td colspan=\"6\">\n");
                HtmlEmailBody.append("<table colspan=\"6\" width=\"100%\"  style = \"border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");
                HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td colspan=\""+extractedSize+"\" style=\"background-color:#FFA500; font-size: 15pt; border-collapse:collapse; font-family:verdana; border:2px solid black\">Extracted Test Data</td>\n");
                HtmlEmailBody.append("</tr>\n");
                
                HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"6\">\n");
                for(Map.Entry<String,ArrayList<String>> entry : testResult.testData.ExtractedParameters.entrySet())
                {
                    extractedBGColourString = this.getBackGroundColourForParameterStatus(entry.getValue().get(1).toString().toUpperCase());  
                    HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"border:1px solid black;\" width=\""+cellWidth+"%\">"+entry.getKey()+"</td>\n");
                }
                HtmlEmailBody.append("</tr>\n");
                
                HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"6\">\n");
                for(Map.Entry<String,ArrayList<String>> entry : testResult.testData.ExtractedParameters.entrySet())
                {
                    extractedBGColourString = this.getBackGroundColourForParameterStatus(entry.getValue().get(1).toString().toUpperCase());  
                    HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"border:1px solid black;\" width=\""+cellWidth+"\">"+entry.getValue().get(0).toString()+"</td>\n");
                }
                HtmlEmailBody.append("</tr>\n");
                
                HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td colspan=\""+extractedSize+"\" style=\"background-color:#FFA500; font-size: 12pt; border-collapse:collapse; font-family:verdana; border:2px solid black\"><span style=\"background-color:#B2FCA3;\">Green = Pass</span>;<span style=\"background-color:#FF9494;\">Red = Fail</span>;<span style=\"background-color:#BDBDBD;\">Grey = Unchecked</span></td>\n");
                HtmlEmailBody.append("</tr>\n");
                
                HtmlEmailBody.append("</table>");
                HtmlEmailBody.append("</td>\n");
                HtmlEmailBody.append("</tr>\n");
                HtmlEmailBody.append("</tbody>");
                HtmlEmailBody.append("</tr>\n");
            }   
            
            
            if (testResult.testData.gridValidationDataRows.size() > 0)
            {

                int extractedSize = testResult.testData.gridValidationDataRows.get(0).DataColumns.size(); 
               
                String extractedBGColourString; 
                String extractedBGColourString1; 
                double cellWidth = 100/extractedSize;
                int HTMLrowCounter = 0;
                
                HtmlEmailBody.append("<tbody colspan=\"6\" width=\"100%\" class=\"testCaseResultRows\" style = \"display : none; border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");
                HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td colspan=\"6\">\n");
                HtmlEmailBody.append("<table colspan=\"6\" width=\"100%\"  style = \"border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");
               
               
                HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"" + extractedSize + "\">\n");
                
                for(DataColumn column :testResult.testData.gridValidationDataRows.get(0).DataColumns)
                {
                    
                    HtmlEmailBody.append("<td style=\"border-left:1px solid black;\" width=\""+cellWidth+"%\">"+"<b>"+column.columnHeader+"</b>"+"</td>\n");         
                }
                
                for(DataRow row : testResult.testData.gridValidationDataRows)
                {

                    
                    
                     HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"6\">\n");
                    
                    for(DataColumn column :row.DataColumns)
                    {
    
                        extractedBGColourString = this.getBackGroundColourForParameterStatus(column.resultStatus.name());  
                        HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"border-left:1px solid black;\" width=\""+cellWidth+"%\">"+"<b>"+column.columnValue+"</b>"+"</td>\n");
    
                    }
                    
                    HtmlEmailBody.append("</tr>\n");
                }
                
                HtmlEmailBody.append("</table>");
                HtmlEmailBody.append("</td>\n");
                HtmlEmailBody.append("</tr>\n");
                HtmlEmailBody.append("</tbody>");
                HtmlEmailBody.append("</tr>\n");
                
            }
        }

        HtmlEmailBody.append("</table>\n");
        
        HtmlEmailBody.append("<p style=\"font-size: 10pt;color:#000000; font-family:verdana\">* Click for extracted parameters</p>\n");
        
        HtmlEmailBody.append("</body>\n\n");

        HtmlEmailBody.append("</html>\n");

        this.saveAsHTMLTestReport(resolveScenarioName() + ".html");

        return HtmlEmailBody.toString();
    }
    
    private String getBackGroundColourForTestStatus(Enums.ResultStatus testStatus)
    {
        switch(testStatus)
        {
            case PASS:
                return "#6CBB3C";
            case FAIL:
                return "#C11B17";
            case WARNING:
                return "#F88017";
            default:
                return "#FF9900";
        }
    }
    
    public String generateExtractionGrid(TestEntity testData)
    {
        StringBuilder HtmlEmailBody = new StringBuilder();
        
        int extractedSize = testData.ExtractedParameters.size();
        String extractedBGColourString; 
        double cellWidth = 100/extractedSize;

        HtmlEmailBody.append("<span class=\"testCaseResultRows\">");
        HtmlEmailBody.append("<table colspan=\"" + extractedSize + "\" style=\"border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");

        HtmlEmailBody.append("<tr>\n");
        HtmlEmailBody.append("<td colspan=\""+extractedSize+"\" style=\"background-color:#BDBDBD; font-size: 15pt; border-collapse:collapse; font-family:verdana; border:2px solid black\">Extracted Test Data</td>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"6\">\n");
        for(Map.Entry<String,ArrayList<String>> entry : testData.ExtractedParameters.entrySet())
        {
            extractedBGColourString = this.getBackGroundColourForParameterStatus(entry.getValue().get(1).toString().toUpperCase());  
            HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"border:1px solid black;\" >"+entry.getKey()+"</td>\n");
        }
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"6\">\n");
        for(Map.Entry<String,ArrayList<String>> entry : testData.ExtractedParameters.entrySet())
        {
            extractedBGColourString = this.getBackGroundColourForParameterStatus(entry.getValue().get(1).toString().toUpperCase());  
            HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"border:1px solid black;\" width=\""+cellWidth+"\">"+entry.getValue().get(0).toString()+"</td>\n");
        }
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("<tr>\n");
        HtmlEmailBody.append("<td colspan=\""+extractedSize+"\" style=\"background-color:#BDBDBD; font-size: 12pt; border-collapse:collapse; font-family:verdana; border:2px solid black\"><span style=\"background-color:#B2FCA3;\">Green = Pass</span>;<span style=\"background-color:#FF9494;\">Red = Fail</span>;<span style=\"background-color:#BDBDBD;\">Grey = Unchecked</span></td>\n");
        HtmlEmailBody.append("</tr>\n");

        HtmlEmailBody.append("</table>");
        HtmlEmailBody.append("</span>");

        return HtmlEmailBody.toString();
    }
    
    public String generateGridValidationTable(TestEntity testData)
    {
        StringBuilder HtmlEmailBody = new StringBuilder();
        
        if (testData.gridValidationDataRows.size() > 0)
            {
                
                

                int extractedSize = testData.gridValidationDataRows.get(0).DataColumns.size(); 
                
                if(extractedSize < 6)
                    extractedSize = 6;
               
                String extractedBGColourString; 
                String extractedBGColourString1; 
                double cellWidth = 100/extractedSize;
                int HTMLrowCounter = 0;
                
                HtmlEmailBody.append("<span class=\"testCaseResultRows\">");
                HtmlEmailBody.append("<table colspan=\"" + extractedSize + "\" style=\"border-collapse:collapse;font-family:verdana; border:1px solid black;\" cellpadding=\"2\" cellspacing=\"0\"\">\n");
               
                HtmlEmailBody.append("<tr>\n");
                HtmlEmailBody.append("<td colspan=\""+extractedSize+"\" style=\" white-space: nowrap; min-width:600px background-color:#BDBDBD; font-size: 15pt; border-collapse:collapse; font-family:verdana; border:2px solid black\">Extracted Grid Data</td>\n");
                HtmlEmailBody.append("</tr>\n");
                HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"" + extractedSize + "\">\n");
                
                for(DataColumn column :testData.gridValidationDataRows.get(0).DataColumns)
                {
                    
                    HtmlEmailBody.append("<td style=\" max-width:1000px; white-space: nowrap; border-left:1px solid black;\">"+"<b>"+column.columnHeader+"</b>"+"</td>\n");         
                }
                
                for(DataRow row : testData.gridValidationDataRows)
                {

                    
                    
                    HtmlEmailBody.append("<tr style=\"outline: thin solid black;\" colspan=\"" + extractedSize + "\">\n");
                    
                    for(DataColumn column :row.DataColumns)
                    {
    
                        extractedBGColourString = this.getBackGroundColourForParameterStatus(column.resultStatus.name());  
                        HtmlEmailBody.append("<td bgcolor = \""+extractedBGColourString+"\" style=\"width:auto; white-space: nowrap; border-left:1px solid black;\">"+"<b>"+column.columnValue+"</b>"+"</td>\n");
    
                    }
                    
                    HtmlEmailBody.append("</tr>\n");
                }
                
                HtmlEmailBody.append("</table>");
                HtmlEmailBody.append("</span>");
          
            }
        return HtmlEmailBody.toString();
    }
    
    private String getBackGroundColourForParameterStatus(String parameterStatus)
    {
        switch(parameterStatus)
        {
            case "PASS":
                return "#6CBB3C";
            case "FAIL":
                return "#C11B17";
            default:
                return "#BDBDBD";
        }
    }


    private void saveAsHTMLTestReport(String htmlReportFileName) {

        try {

            if (this.reportDirectory != null) {
                File evidenceReportFile = new File(this.reportDirectory + "\\" + htmlReportFileName);

                if (!evidenceReportFile.exists()) {
                    evidenceReportFile.createNewFile();
                }

                try {
                    BufferedWriter writerEvidence = new BufferedWriter(new FileWriter(evidenceReportFile));
                    writerEvidence.write(HtmlEmailBody.toString());

                    evidenceReportFile = null;
                    writerEvidence.close();
                } catch (Exception ex2) {
                    err.println(" Failed to save HTML test report to evidence folder - " + ex2.getMessage());
                }
            }

            File reportFile = new File("HTMLTestReport\\HTMLTestReport.html");

            if (!reportFile.exists()) 
            {
                reportFile = new File("HTMLTestReport");
                
                reportFile.mkdirs();
                reportFile = new File("HTMLTestReport\\HTMLTestReport.html");
                reportFile.createNewFile();
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile));
                writer.write(HtmlEmailBody.toString());

                reportFile = null;
                writer.close();
            } catch (Exception ex1) {
                err.println(" Failed to save HTML test report to HTML Test Report directory - " + ex1.getMessage());
            }
        } catch (Exception e) {
            Narrator.logError(" Failed to save HTML report to - HTMLTestReport.html - Error - " + e.getMessage());
        }

    }

    public void SendResultsEmail() {
        try {
            Properties properties = System.getProperties();

            properties.setProperty("mail.smtp.host", this.emailHost);
            properties.setProperty("mail.user", "ferdinandN");
            properties.setProperty("mail.password", "password");

            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);

            message.setSubject("SeeCrypt - Automation Test Report - " + this.resolveScenarioName());

            MimeMultipart multipartMessage = new MimeMultipart();

            MimeBodyPart messageBody = new MimeBodyPart();
            MimeBodyPart attachment = new MimeBodyPart();
            DataSource inputFile = new FileDataSource(this.inputFilePath);

            messageBody.setContent(this.GenerateTestReport(), "text/html");
            
            

            message.setFrom(new InternetAddress(this.emailSender));

            for (String recipient : this.emailRecipients) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            attachment.setDataHandler(new DataHandler(inputFile));
            attachment.setHeader("Content-ID", "<" + UUID.randomUUID().toString() + ">");
            attachment.setFileName(inputFile.getName());

            multipartMessage.addBodyPart(messageBody);
            multipartMessage.addBodyPart(attachment);

            message.setContent(multipartMessage);

            Transport.send(message);

            Narrator.logDebug("Report Email sent...");

        } catch (Exception e) {
            Narrator.logError(" could not send results email - " + e.getMessage());
        }

    }
    
   public void GenerateDatabase()
    {
        
       try {
           
            dataColumnName = new ArrayList<>();
            String host="jdbc:derby://localhost:1527/TestResults";
            String username= "Test";
            String userPassword="Test";
            Connection conn = DriverManager.getConnection(host,username,userPassword);
            
            Statement statement;
            String sql;
            String insert;
            String select;
            String newcolumn;
            String update;
                    
            statement = conn.createStatement();
            
               DatabaseMetaData dbmd = conn.getMetaData();
               
               ResultSet testData = dbmd.getTables(null, null, "TESTRESULTS",null);
               
               if (!testData.next())
               {
                    sql = "CREATE TABLE TestResults ( TimeStamp varchar(255) NOT NULL PRIMARY KEY,  Total_Tests INT, Total_Passed INT, Total_Failed INT,  Total_Hours INT, Total_Minutes INT, Total_Seconds INT)"; 
                    statement.executeUpdate(sql);
               }
               
               ResultSet testCaseID = dbmd.getTables(null, null, "TESTDATA", null);
               
               
               
               if (!testCaseID.next())
               {
                    sql = "CREATE TABLE TestData ( TimeStamp varchar(255) NOT NULL PRIMARY KEY, TestCaseID varchar(255), Keyword varchar(255) )"; 
                    statement.executeUpdate(sql);
               }
               
               ResultSet testcase = dbmd.getTables(null, null, "TESTCASE", null);
               
               if (!testcase.next())
               {
                    sql = "CREATE TABLE TestCase ( TestCaseNumber INT NOT NULL PRIMARY KEY generated always as identity,TimeStamp varchar(255) ,TestCaseID varchar(255), Keyword varchar(255), TestDescription varchar(255),  TestCaseStatus varchar(255),TestMessage varchar(255), TestDuration varchar(255),FOREIGN KEY (TimeStamp) References TestData(TimeStamp) ON DELETE CASCADE)"; 
                    statement.executeUpdate(sql);
                 
               }
               
               ResultSet rs = statement.executeQuery("SELECT * FROM TESTDATA");
               ResultSetMetaData rsmd = rs.getMetaData();
               
               
               for(int i=1 ; i <= rsmd.getColumnCount(); i++)
               {
                   String ColumnName = rsmd.getColumnName(i);
                   dataColumnName.add(ColumnName);
               }
              
               for (TestResult testResult : testResults) 
               {
                     
                     
                    insert = "INSERT INTO TestResults VALUES ('"+currentTimestamp.toString().substring(0, 19)+"', "+this.testCount+","+this.passCount+","+this.failCount+","+this.totalHours+","+this.totalMinutes+","+this.totalSeconds+")";
                    statement.executeUpdate(insert);
                     
                    insert = "INSERT INTO TestData (TimeStamp, TestCaseID, Keyword)  VALUES ('"+currentTimestamp.toString().substring(0, 19)+"','"+testResult.testData.TestCaseId+"', '"+testResult.testData.TestMethod+"')";
                    statement.executeUpdate(insert);
                 
                  
                    insert = "INSERT INTO TestCase (TimeStamp, TestCaseID, Keyword, TestDescription, TestCaseStatus, TestMessage, TestDuration)  VALUES ('"+currentTimestamp.toString().substring(0, 19)+"', '"+testResult.testData.TestCaseId+"', '"+testResult.testData.TestMethod+"', '"+testResult.testData.TestDescription+"', '"+testResult.testStatus.toString()+"', '"+testResult.errorMessage+"', '"+testResult.calculateFormattedTestTime()+"')";
                    statement.executeUpdate(insert);
                    
                        for(Map.Entry<String,ArrayList<String>> entry : testResult.testData.ExtractedParameters.entrySet())
                        {
                            if (!dataColumnName.contains(entry.getKey().toUpperCase())) 
                            {
                                newcolumn ="ALTER TABLE TestData ADD "+entry.getKey()+" varchar(255)";
                                statement.executeUpdate(newcolumn);
                            }
                            
                             update = "Update TestData set "+entry.getKey()+" = '"+entry.getValue().get(0).toString()+"' where TimeStamp = '"+currentTimestamp.toString().substring(0, 19)+"'";
                             statement.executeUpdate(update);
                        }
                    
                }
            
               select = "SELECT TestCase.TestCaseNumber,  TestData.TestCaseID,TestCase.KEYWORD, TestData.USERNAME, TestData.PASSWORD  FROM TestCase INNER JOIN TestData ON TestCase.TestCaseID=TestData.TestCaseID ORDER BY TestCase.TestCaseNumber";
               statement.executeQuery(select);
 
            conn.close();
            
        } catch (SQLException err) {
            Narrator.logError(err.getMessage());
        }
    }
   

    private void CalculateTestStatistics() {

        for (TestResult result : testResults) {
            this.totalSeconds += result.testDuration;
            this.testCount++;

            if (result.testStatus == Enums.ResultStatus.PASS || result.testStatus == Enums.ResultStatus.WARNING) {
                this.passCount++;
            } else if (result.testStatus == Enums.ResultStatus.FAIL) {
                this.failCount++;
            }
        }

        if (totalSeconds > 60) {
            while (totalSeconds > 60) {
                totalMinutes += 1;
                totalSeconds -= 60;

            }
        }

        if (totalMinutes > 60) {
            while (totalMinutes > 60) {
                totalHours += 1;
                totalMinutes -= 60;
            }
        }
    }
   
    
    
}
    


