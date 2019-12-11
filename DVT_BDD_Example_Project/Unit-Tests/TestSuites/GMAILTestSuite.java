
package TestSuites;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import java.io.FileNotFoundException;
import org.junit.Test;

/**
 *
 * @author dluis
 */
public class GMAILTestSuite
{

    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public GMAILTestSuite()
    {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
       

    }

    @Test
    public void GMAILTEST() throws FileNotFoundException
    {
        Narrator.logDebug("GMAIL - Scenario 1  - Test Pack");
        instance = new TestMarshall("TestPacks\\GMAIL TEST PACK.xlsx", Enums.BrowserType.Chrome);
        instance.runKeywordDrivenTests();
    }

    

}

