/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestSuites;

import static KeywordDrivenTestFramework.Core.BaseClass.requiresBrowser;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.Properties;
import org.junit.Test;

/**
 *
 * @author fnell
 */
public class JenkinsSuiteAndroid 
{
 static TestMarshall instance;
    
    @Test
    public void executeHollardTestPack() throws FileNotFoundException, IOException
    { 
        
        Properties props = System.getProperties();
        
        String browser = props.getProperty("Browser");
        
        String testpack = props.getProperty("TestPack");
        
        String myTest = "MyTest";
        
        out.println(props.stringPropertyNames());
        
        
        String environment = props.getProperty("Environment");
        
        String device = props.getProperty("Device");
        
        String deviceConfig = props.getProperty("DeviceConfig");
        
        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment + ", Test Pack - " + testpack + ", MyTest = " + myTest);
        
        
        ApplicationConfig appConfig = new ApplicationConfig();
        
        instance = new TestMarshall("TestPacks\\" + testpack);
        
        TestMarshall.currentEnvironment = Enums.resolveTestEnvironment(environment);
        
        TestMarshall.currentDevice = Enums.resolveDevice(device);
        
        TestMarshall.currentDeviceConfig = Enums.resolveDeviceConfig(deviceConfig);
        TestMarshall.currentPlatform = TestMarshall.currentDevice.platform;
        requiresBrowser = false;
        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment + ", Device - " + device +", DeviceConfig - " + deviceConfig +", Test Pack - " + testpack);
        
        instance.runKeywordDrivenTests();
    }   
}
