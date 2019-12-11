/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import com.eviware.soapui.*;
import com.eviware.soapui.config.ProjectConfig;
import com.eviware.soapui.config.SettingsConfig;
import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.model.iface.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import static java.lang.System.err;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author fnell
 */
public class WebServiceDriverUtility extends BaseClass
{

    WsdlProject project;
    WsdlInterface wsdlInterface;
    WsdlOperation wsdlOperation;
    WsdlRequest request;
    WsdlSubmit submit;
    Response response;
    public static String responseString;

    String requestString = "", primaryInsuredType = "", lifeInsureInsuredType = "", primarySelected = "", lifeInsureSelected = "";

    public WebServiceDriverUtility()
    {
        Logger.getLogger("httpclient.wire").setLevel(Level.OFF);
        Logger.getLogger("groovy.log").setLevel(Level.OFF);
        Logger.getLogger(SoapUI.class).setLevel(Level.OFF);
    }

    public boolean ImportWSDL(String URL)
    {
        try
        {
            project = new WsdlProject();
// Requires JGoodies
//            SoapUI.setSoapUICore(new StandaloneSoapUICore(true)); 

            ProjectConfig config = project.getConfig();

            SettingsConfig settings = config.getSettings();

            settings.getSettingList();

            wsdlInterface = WsdlInterfaceFactory.importWsdl(project, URL, false)[0];

            out.println("[INFO] Successfully imported WSDL - " + URL);

            out.println("[INFO] WSDL Operations detected - ");

            for (Operation operation : wsdlInterface.getAllOperations())
            {
                out.println("[INFO] WSDL Operation:  " + operation.getName());
            }
            out.println("[INFO] End of WSDL Operations list");

            return true;
        }
        catch (Exception ex)
        {
            err.println("[ERROR] Failed to load Web Service WSDL - " + URL + " - Error : " + ex.getMessage());

            return false;
        }
    }

    public boolean GetWSDLOperation(String operationName)
    {
        try
        {
            if (this.wsdlInterface != null)
            {
                wsdlOperation = (WsdlOperation) this.wsdlInterface.getOperationByName(operationName);
                System.out.println("[Info] Operation retrieval complete");
                return true;
            }
            else
            {
                err.println("[ERROR] Failed to retrieve operation by name - " + operationName + ", Error: Interface was null, WSDL was not loaded correctly");
                return false;
            }

        }

        catch (Exception ex)
        {
            err.println("[ERROR] Failed to retrieve operation by name - " + operationName + ", Error: " + ex.getMessage());
            return false;
        }
    }

    public String GetRequestString()
    {
        if (this.wsdlOperation != null)
        {
            return this.wsdlOperation.createRequest(true);
        }
        else
        {
            err.println("[ERROR] WDSL Operation was not instantiated");
            return "ERROR - WSDL Operation is null";
        }
    }

    public String SubmitRequest(String requestContent)
    {

        try
        {
            if (this.wsdlInterface != null && this.wsdlOperation != null)
            {
                this.request = wsdlOperation.addNewRequest("New Request");

                requestString = this.wsdlOperation.createRequest(true);

                this.request.setRequestContent(requestContent);

                ModelItem model = this.request.getModelItem();

                WsdlSubmitContext submitContext = new WsdlSubmitContext(model);
                out.println("[INFO] ----------------Request content ---------------------------------------------  ");
                for (String pName : submitContext.getPropertyNames())
                {
                    out.println("[INFO] Request property: " + pName);
                }
                out.println("[INFO] ------------------------------------------------------------------------------");
                //submitContext.put(requestName, requestContent);
                out.println("[INFO] Request content as string - " + this.request.getRequestContent());

                this.submit = (WsdlSubmit) this.request.submit(submitContext, false);

                this.response = this.submit.getResponse();

                responseString = this.response.getContentAsString();
                out.println("[INFO] --------Response content ------------ - ");
                out.println("[INFO] Response content as string - " + responseString);
                out.println("[INFO] ------------------------------------------ ");
                return responseString;
            }
            else
            {
                err.println("[ERROR]  Error: Interface was null or the Operation was not retrieved");
                return responseString;
            }
        }
        catch (Exception ex)
        {
            err.println("[ERROR] Failed to submit request and retrieve response, Error: " + ex.getMessage());
            return responseString;
        }
    }

    public String FromatDateString(String dateString)
    {

        String result = "";

        if (!dateString.equals("0") && !dateString.equals("99999999"))
        {
            try
            {
                String[] dateSplit = dateString.split("/");

                result = dateSplit[2] + dateSplit[1] + dateSplit[0];

            }
            catch (Exception ex)
            {
                err.println("[ERROR] Failed to format date string - " + ex.getMessage());
            }
        }

        return result;
    }

    public String GetDateOfClaim()
    {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfClaim = df.format(new Date());

        return this.FromatDateString(dateOfClaim);
    }

    public String RESTRequest(String Endpoint, String JSON_STRING, StringEntity input, String Header, String HeaderValue)
    {
        String line;
        try
        {
            HttpClient client = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(Endpoint);

            httppost.addHeader(Header, HeaderValue);
            input.setContentType("application/json");

            //Execute and get the response.
            httppost.setEntity(input);
            HttpResponse response = client.execute(httppost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            line = rd.readLine();
            return line;
        }

        catch (Exception e)
        {
            narrator.logError("Failed to run HTTP Request - " + e.getMessage());
            line = "Failed to send Request";
            return line;
        }

    }

}
