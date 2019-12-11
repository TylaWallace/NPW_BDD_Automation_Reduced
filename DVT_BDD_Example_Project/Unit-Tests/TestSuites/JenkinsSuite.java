/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestSuites;

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
public class JenkinsSuite 
{
 static TestMarshall instance;
    
    @Test
    public void executeHollardTestPack() throws FileNotFoundException, IOException
    { 
        
        Properties props = System.getProperties();
        
        String browser = props.getProperty("Browser");
        
        String testpack = props.getProperty("TestPack");
        
        String myTest = props.getProperty("myTest");
        
        out.println(props.stringPropertyNames());
        
        
        String environment = props.getProperty("Environment");
        
        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment + ", Test Pack - " + testpack + ", MyTest = " + myTest);
        
        
        ApplicationConfig appConfig = new ApplicationConfig();
        
        instance = new TestMarshall("TestPacks\\" + testpack , appConfig.resolveBrowserType(browser));
        
        TestMarshall.currentEnvironment = Enums.resolveTestEnvironment(environment);
        
        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment + ", Test Pack - " + testpack);
        
        instance.runKeywordDrivenTests();
    }   
}
