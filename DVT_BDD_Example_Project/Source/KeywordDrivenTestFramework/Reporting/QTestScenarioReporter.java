/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Reporting;

import static KeywordDrivenTestFramework.Core.BaseClass.currentExecution;
import KeywordDrivenTestFramework.Entities.QTest_DAO.Execution;
import com.fasterxml.jackson.databind.ObjectMapper;
import gherkin.JSONParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ferdinand
 */
public class QTestScenarioReporter
{
    HttpClient httpClient;
    
    public QTestScenarioReporter()
    {
        httpClient = HttpClientBuilder.create().build();
        
    }
    
   
    public void PushJSONReportToJira(String JSONReportPath, long testCycleID, long projectID)
    {
        try
        {
            HttpPost request = new HttpPost("https://pulse.qas-labs.com/api/v1/webhooks/WmJcKoDnBfNitqcZC/zE7zh6RhmgwYKCb49/c015f130-17d3-4858-9309-06580b11d957");
            ObjectMapper mapper = new ObjectMapper();

            // add request heade
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept-Type", "application/json");
            
            Execution qtTestExec = new Execution();
            
            qtTestExec.projectid = projectID;
            qtTestExec.test_cycle = testCycleID;
            
            File reportFile = new File(JSONReportPath);
 
            String jsonTxt = new String(Files.readAllBytes(reportFile.toPath()));
  
            JSONArray array = new JSONArray(jsonTxt); 
          
            String jsonResultsString = array.toString();
    
            qtTestExec.result = jsonResultsString;
        
            String requestBody = mapper.writeValueAsString(qtTestExec);
            
            requestBody = requestBody.replace("test_cycle", "test-cycle");
            
            out.println(requestBody);  
   
            request.setEntity(new StringEntity(requestBody));  
               
            HttpResponse response = httpClient.execute(request);  
            
            if(response.getStatusLine().getStatusCode() != HttpStatus.OK_200)
            {
                err.println("[ERROR] QTest API - Response code - " + response.getStatusLine());
               
            }
            else
               out.println("[INFO] QTest Request sent - " + EntityUtils.toString(response.getEntity())) ;
                
        }
        catch(Exception ex)
        {
            err.println("[ERROR] QTest API - Response code - " + ex.getMessage());
        }
    }
}
