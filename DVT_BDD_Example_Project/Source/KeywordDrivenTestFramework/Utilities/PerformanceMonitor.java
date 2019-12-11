/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;


import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.BMProxy;
import java.io.File;
import static java.lang.System.err;
import static java.lang.System.out;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.mitm.TrustSource;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author fnell
 */
public class PerformanceMonitor extends BaseClass
{
    Har perfLogs;
    
    boolean monitoringOnline = false;
    public PerformanceMonitor()
    {
        
    }
    
    
    public DesiredCapabilities initialiseBMProxyService(DesiredCapabilities caps)
    {
        
        if(EnablePerformanceMonitoring)
        {
            try
            {

                if(BMProxy == null)
                {
                    File cert = new File("PerformanceMonitoringInfo\\ssl-support\\ca-certificate-ec.cer");
                    TrustSource trustSource = TrustSource.defaultTrustSource().add(cert);

    
    
                    BMProxy = new BrowserMobProxyServer();
                    BMProxy.setTrustSource(trustSource);
                    BMProxy.setTrustAllServers(true);

                    //Proxy port is configurable here
                    BMProxy.start(0);

                    seleniumProxy = ClientUtil.createSeleniumProxy(BMProxy);

                    //Specify which details are captured
                    
                    //captureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT
                    BMProxy.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS,CaptureType.RESPONSE_HEADERS);
                    monitoringOnline = true;

                }

                out.println("[INFO] Starting monitoring proxy on port " + BMProxy.getPort());

                // Set Proxy to BMP service
                caps.setCapability(CapabilityType.PROXY, seleniumProxy); 


            }
            catch(Exception ex)
            {
                err.println("Failed to start performance monitoring Driver, reverting to standard driver - " + ex.getMessage());

            }
        }
        
        return caps;
    }
    
    public void generatePerformanceReport(Har _perfLogs)
    {
        perfLogs = _perfLogs;
        
        generateReport();
                
                
    }
    
    
    public void startMonitoring(String PageName)
    {
        if(EnablePerformanceMonitoring)
        {
            if(BMProxy.getHar() == null)
            {
               // the HAR file is the stats output for performance monitoring
               BMProxy.newHar(PageName);
               monitoringOnline = true;
            }
        }
    }
    
    public void newPage(String PageName)
    {
        if(EnablePerformanceMonitoring && monitoringOnline)
            BMProxy.newPage(PageName);
    }
    
    public void endPage()
    {
        if(EnablePerformanceMonitoring && monitoringOnline)
            BMProxy.endPage();
    }
    
    
    public void endMonitoring()
    {
        if(EnablePerformanceMonitoring && monitoringOnline)
        {
            generatePerformanceReport(BMProxy.endHar());

        }
    }
    
    void generateReport() 
    {
        try
        {
            File harFile = new File(reportDirectory + "\\" + resolveScenarioName() + ".har");
            perfLogs.writeTo(harFile);
            
            
           ProcessBuilder pb=new ProcessBuilder("RunHAR.bat", "\"" + harFile.getAbsolutePath() + "\"");
           pb.redirectErrorStream(true);

           Process process=pb.start();

           //Hard wait for HarViewer to finish initialising before terminating
           Thread.sleep(5000);

            
        }
        catch(Exception ex)
        {
            err.println("Failed to generate HAR file for performance report - " + ex.getMessage());
        }
    }
    
    
   
    
    
}
