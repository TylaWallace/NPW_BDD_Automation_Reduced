/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.DataColumn;
import KeywordDrivenTestFramework.Entities.DataRow;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.RetrievedTestValues;
import KeywordDrivenTestFramework.Reporting.Narrator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author fnell
 * @editir jjoubert
 */
// Contains logic for handling accessor methods and driver calls.
public class SeleniumDriverUtility extends BaseClass
{

    public WebDriver Driver;
    private Enums.BrowserType browserType;
    File fileIEDriver;
    File fileChromeDriver;
    File filePhantomJsDriver;
    File fileEdgeDriver;
    public Boolean _isDriverRunning = false;
    public RetrievedTestValues retrievedTestValues;
    public String DriverExceptionDetail = "";
//    TestEntity testData;
    public Object document;
    public String mainWindowsHandle;

    EventFiringWebDriver eventDriver;

    public SeleniumDriverUtility(Enums.BrowserType selectedBrowser)
    {
        retrievedTestValues = new RetrievedTestValues();

        _isDriverRunning = false;
        browserType = selectedBrowser;

        fileIEDriver = new File("IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", fileIEDriver.getAbsolutePath());

        fileChromeDriver = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        
        fileEdgeDriver = new File("MicrosoftWebDriver.exe");
        System.setProperty("webdriver.edge.driver",fileEdgeDriver.getAbsolutePath());
		

        filePhantomJsDriver = new File("phantomjs.exe");
        System.setProperty("phantomjs.binary.path", filePhantomJsDriver.getAbsolutePath());

    }

    public boolean isDriverRunning()
    {
        return _isDriverRunning;
    }

    public void startDriver()
    {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

        switch (browserType)
        {
            case EDGE:
                Driver = new EdgeDriver();
                _isDriverRunning = true;
                break;
            case IE:
                Driver = new InternetExplorerDriver();
                _isDriverRunning = true;
                break;
            case FireFox:
                Driver = new FirefoxDriver();
                _isDriverRunning = true;
                break;
            case Chrome:
                Driver = new ChromeDriver();
                _isDriverRunning = true;
                break;
            case PhantomJs:
                Driver = new PhantomJSDriver();
                _isDriverRunning = true;
                break;
            case mobileChrome:
                AppiumDriverInstance = new AppiumDriverUtility();
                Driver = AppiumDriverInstance.Driver;
                _isDriverRunning = true;
                break;
            case Safari: ;
                break;
        }
        if(browserType != Enums.BrowserType.mobileChrome)
        {
            retrievedTestValues = new RetrievedTestValues();
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            Driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            Driver.manage().window().maximize();
        }

    }

    public boolean navigateTo(String pageUrl)
    {
        try
        {
            Driver.navigate().to(pageUrl);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" navigating to URL  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean clickElementbyXpath(String elementXpath)
    {
        try
        {

            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, ApplicationConfig.WaitTimeout());
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean roboClickLatestElementbyXpath(String elementXpath)
    {
        try
        {
            List <WebElement> elementList = SeleniumDriverInstance.Driver.findElements(By.xpath(elementXpath));
            WebDriverWait wait = new WebDriverWait(Driver, ApplicationConfig.WaitTimeout());
            wait.until(ExpectedConditions.elementToBeClickable(elementList.get(elementList.size()-1)));
            WebElement elementToClick = ((elementList.get(elementList.size()-1)));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean roboWaitForLatestElementbyXpath(String elementXpath)
    {
        try
        {
            List <WebElement> elementList = SeleniumDriverInstance.Driver.findElements(By.xpath(elementXpath));
            WebDriverWait wait = new WebDriverWait(Driver, ApplicationConfig.WaitTimeout());
            wait.until(ExpectedConditions.elementToBeClickable(elementList.get(elementList.size()-1)));
            //WebElement elementToClick = ((elementList.get(elementList.size()-1)));
            //elementToClick.click();
            Narrator.logDebug("Waited for element by Xpath : " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean clickElementbyXpathUsingActions(String elementXpath)
    {
        try
        {

            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, ApplicationConfig.WaitTimeout());
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            Actions action = new Actions(Driver);
            action.moveToElement(elementToClick);
            action.click(elementToClick);
            action.perform();
            Narrator.logDebug(" Clicked element by Xpath : " + elementXpath);

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean clickElementbyXpathUsingActionsOffset(String elementXpath)
    {
        try
        {

            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, ApplicationConfig.WaitTimeout());
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            Actions action = new Actions(Driver);
            action.moveToElement(elementToClick, -400, 0);
            action.click(elementToClick);
            action.perform();
            Narrator.logDebug(" Clicked element by Xpath : " + elementXpath);

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean doubleClickElementbyXpath(String elementLinkText)
    {
        try
        {
            //waitForElementByLinkText(elementLinkText);
            Actions act = new Actions(Driver);
            WebElement elementToClick = Driver.findElement(By.xpath(elementLinkText));

            act.doubleClick(elementToClick).perform();
            Narrator.logDebug(" Double-clicked element by Xpath : " + elementLinkText);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" failed to double click element by link text  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean waitForElementByXpath(String elementXpath)
    {
        boolean elementFound = false;
        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, 1);

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    if(!isCucumberMobileTesting)
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null)
                    {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                }
                catch (Exception e)
                {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }
            if (waitCount == ApplicationConfig.WaitTimeout())
            {
                Narrator.logError("Reached TimeOut whilst waiting for element by Xpath '" + elementXpath + "'");

                return elementFound;
            }

        }
        catch (Exception e)
        {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementPresentByXpath(String elementXpath)
    {
        boolean elementFound = false;
        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, 1);

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null)
                    {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                }
                catch (Exception e)
                {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        }
        catch (Exception e)
        {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementByXpath(String elementXpath, int timeout)
    {
        boolean elementFound = false;
        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, timeout);

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null)
                    {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                }
                catch (Exception e)
                {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        }
        catch (Exception e)
        {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementNoLongerPresentByXpath(String elementXpath)
    {
        boolean elementFound = true;
        try
        {
            int waitCount = 0;
            while (elementFound && waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, 1);
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) == null)
                    {
                        elementFound = false;
                        Narrator.logDebug(" Element no longer present at Xpath : " + elementXpath);
                        break;
                    }
                }
                catch (Exception e)
                {
                    this.DriverExceptionDetail = e.getMessage();
                    elementFound = false;
                    break;
                }

                waitCount++;
            }
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to wait for element to no longer be present by Xpath  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean enterTextByXpath(String elementXpath, String textToEnter)
    {
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            pause(1000);
            elementToTypeIn.sendKeys(textToEnter);
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    //implement still...
    public boolean roboEnterLastTextByXpath(String elementXpath, String textToEnter)
    {
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            elementToTypeIn.sendKeys(textToEnter);
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }


    public boolean ClearTextByXpathUsingActions(String elementXpath)
    {
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
            //elementToTypeIn.clear;
            Actions typeText = new Actions(Driver);
            typeText.moveToElement(elementToTypeIn);
            typeText.click(elementToTypeIn);
            typeText.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
//            elementToTypeIn.clear();
//            typeText.sendKeys(elementToTypeIn, textToEnter);
//            typeText.click(elementToTypeIn);
//            typeText.perform();
            Narrator.logDebug("Cleared Text of: " + elementXpath);

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" clearing text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean enterTextByXpathUsingActions(String elementXpath, String textToEnter)
    {
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
            //elementToTypeIn.clear;
            Actions typeText = new Actions(Driver);
            typeText.moveToElement(elementToTypeIn);
            typeText.click(elementToTypeIn);
            typeText.sendKeys(elementToTypeIn, textToEnter);
            typeText.click(elementToTypeIn);
            typeText.perform();
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean selectFromDropDownListUsingXpath(String elementXpath, String valueToSelect)
    {
        try
        {
            waitForElementByXpath(elementXpath);
            Select dropDownList = new Select(Driver.findElement(By.xpath(elementXpath)));
            WebElement formxpath = Driver.findElement(By.xpath(elementXpath));
            formxpath.click();
            dropDownList.selectByVisibleText(valueToSelect);
            Narrator.logDebug("Selected Text of: " + valueToSelect + " from: " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" selecting from dropdownlist by text using Id - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean alertHandler()
    {
        try
        {
            Narrator.logDebug("Attempting to click OK in alert pop-up");
            // Get a handle to the open alert, prompt or confirmation
            Alert alert = Driver.switchTo().alert();
            // Get the text of the alert or prompt
            alert.getText();
            // And acknowledge the alert (equivalent to clicking "OK")
            alert.accept();
            Narrator.logDebug("Ok Clicked successfully...proceeding");
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" clicking OK in alert pop-up - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean alertHandlerType(String username, String password)
    {
        try
        {
            Narrator.logDebug("Attempting to click OK in alert pop-up");
            
            
            WebDriverWait wait = new WebDriverWait(Driver, 10);     
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        
            alert.authenticateUsing(new UserAndPassword(username, password));
            
            
            Narrator.logDebug("Ok Clicked successfully...proceeding");
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" clicking OK in alert pop-up - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public String retrieveTextByXpath(String elementXpath)
    {
        String retrievedText = "";
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));
            retrievedText = elementToRead.getText();
            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from element - " + elementXpath);
            return retrievedText;

        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public String retrieveAttributeByXpath(String elementXpath, String Attribute)
    {
        String retrievedAttribute = "";
        try
        {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));
            retrievedAttribute = elementToRead.getAttribute(Attribute);
            Narrator.logDebug("Attribute retrieved successfully from element - " + elementXpath);
            return retrievedAttribute;
        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to retrieve attribute from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedAttribute;
        }
    }

    public void pressKey(Keys keyToPress)
    {
        try
        {
            Actions action = new Actions(Driver);
            action.sendKeys(keyToPress);
            action.perform();
        }
        catch (Exception e)
        {
            this.DriverExceptionDetail = e.getMessage();
            Narrator.logError(" Failed to send keypress to element - " + keyToPress);
        }
    }

    public Boolean ValidateByAttribute(String elementXpath, String Attribute, String testData)
    {
        try
        {
            String attribute = retrieveAttributeByXpath(elementXpath, Attribute);
            String data = testData;

            if (!attribute.equals(data))
            {
                Narrator.logError("Failed to validate " + attribute + ", against " + data + ".");
                return false;
            }
            Narrator.logDebug("Validated by attribute value: " + attribute + ", successfully.");

            return true;

        }
        catch (Exception e)
        {
            Narrator.logError("Failed to Validate attribute against TestData - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public Boolean ValidateByText(String elementXpath, String testData)
    {
        try
        {
            String text = retrieveTextByXpath(elementXpath);
            String data = testData;

            if (!text.equals(data))
            {
                Narrator.logError("Failed to validate " + text + ", against " + data + ".");
                return false;
            }
            Narrator.logDebug("Validated by text value: " + text + ", successfully.");

            return true;

        }
        catch (Exception e)
        {
            Narrator.logError("Failed to Validate Text against TestData - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public boolean switchToFrameByXpath(String frameXpath)
    {
        int waitCount = 0;
        try
        {
            while (waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    Driver.switchTo().frame(frameXpath);
                    Narrator.logDebug("Switched to frame " + frameXpath);
                    return true;
                }
                catch (Exception e)
                {
                    //Thread.sleep(500);
                    waitCount++;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            Narrator.logError(" switching to frame by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToDefaultContent()
    {
        try
        {
            Driver.switchTo().defaultContent();
            Narrator.logDebug("Switched to default content");
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError(" switching to default content  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public LinkedList<DataColumn> captureTableInformation(String TableXpath)
    {
        DataRow newRow = new DataRow();

        try
        {
            //Finds the Table
            List<WebElement> Header = Driver.findElements(By.xpath(TableXpath + "//th"));
            List<String> Headers = new ArrayList<>();

            for (int i = 0; i < Header.size(); i++)
            {
                String Head = retrieveTextByXpath(TableXpath + "//th[" + (i + 1) + "]");
                Headers.add(Head);
            }

            List<WebElement> Rows = Driver.findElements(By.xpath(TableXpath + "//tr"));

            for (WebElement Row : Rows)
            {
                List<WebElement> Cells = Row.findElements(By.xpath(TableXpath + "//td"));

                for (int i = 0; i < Cells.size(); i++)
                {

                    DataColumn newColumn = new DataColumn("", "", Enums.ResultStatus.UNCERTAIN);

                    newColumn.columnHeader = Headers.get((i % (Headers.size())));
                    newColumn.columnValue = Cells.get(i).getText();
                    newColumn.resultStatus = Enums.ResultStatus.UNCERTAIN;

                    newRow.DataColumns.add(newColumn);
                }

                return newRow.DataColumns;
            }

        }
        catch (Exception e)
        {
            System.err.println("Error Creating Table" + e);
            this.DriverExceptionDetail = "Error Creating Table";

        }
        return newRow.DataColumns;
    }

    public boolean SaveCookiesToFile(String filepath)
    {
        Set<Cookie> cookieSetReturn;

        Set<Cookie> cookies = Driver.manage().getCookies();

        String path = filepath + "\\Cookies.ser";
        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cookies);
            out.close();
            fileOut.close();
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Failed to Add Cookies, error - " + e.getMessage());
            return false;
        }
    }

    public boolean AddCookiesFromFile(String filepath)
    {
        Set<Cookie> cookieSetReturn;

        try
        {
            //Reads the Cookie File
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cookieSetReturn = (Set<Cookie>) in.readObject();
            in.close();
            fileIn.close();

            for (Cookie getcookies : cookieSetReturn)
            {
                Driver.manage().addCookie(getcookies);

            }
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Failed to Add Cookies, error - " + e.getMessage());
            return false;
        }
    }

    public void takeScreenShot(String screenShotDescription, boolean isError)
    {
        String imageFilePathString = "";

        if(!isCucumberTesting)
        {
            if (testCaseId == null)
            {
                //return;
            } 
        }
       

        try
        {
            StringBuilder imageFilePathBuilder = new StringBuilder();
            // add date time folder and test case id folder
            imageFilePathBuilder.append(this.reportDirectory + "\\");

            relativeScreenShotPath = testCaseId + "\\";

            if (isError)
            {
                relativeScreenShotPath += "FAILED_";
            }
            else
            {
                relativeScreenShotPath += "PASSED_";
            }

            relativeScreenShotPath += screenShotDescription + ".png";

            imageFilePathBuilder.append(relativeScreenShotPath);

            //imageFilePathBuilder.append(this.generateDateTimeString() + ".png");
            imageFilePathString = imageFilePathBuilder.toString();

            setScreenshotPath(imageFilePathString);

            File screenShot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(imageFilePathString.toString()));
        }
        catch (Exception e)
        {
            Narrator.logError(" could not take screenshot - " + imageFilePathString.toString() + " - " + e.getMessage());
        }
    }

    public void shutDown()
    {
        retrievedTestValues = null;
        try
        {

            Driver.quit();

        }
        catch (Exception e)
        {
            Narrator.logError(" shutting down driver - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        _isDriverRunning = false;
    }

    public void copyKeys()
    {
        try
        {
            Actions action = new Actions(Driver);
            action.sendKeys(Keys.CONTROL, "c");
            action.perform();
        }
        catch (Exception e)
        {
            this.DriverExceptionDetail = e.getMessage();
            Narrator.logError(" Failed to send keypress to element - Contoll + C");

        }
    }

    public boolean downloadImgUsingURL(String imageURL, String destinationFile)
    {
        try
        {
            URL url = new URL(imageURL);
            System.setProperty("jsse.enableSNIExtension", "false");
            InputStream fis = url.openStream();
            OutputStream fos = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = fis.read(b)) != -1)
            {
                fos.write(b, 0, length);
            }

            fis.close();
            fos.close();
            return true;
        }
        catch (Exception e)
        {
            narrator.logError("Failed to download Image, error - " + e.getMessage());
            return false;
        }
    }

    public boolean switchToTabOrWindow()
    {
        try
        {
            String winHandleBefore = SeleniumDriverInstance.Driver.getWindowHandle();
            for (String winHandle : SeleniumDriverInstance.Driver.getWindowHandles())
            {
                SeleniumDriverInstance.Driver.switchTo().window(winHandle);
            }
        }
        catch (Exception ex)
        {
            Narrator.logError("Could not switch to new tab" + ex.getMessage());
            this.DriverExceptionDetail = ex.getMessage();
            return false;
        }
        Narrator.logDebug("Switched to window " + Driver.getTitle());
        return true;
    }

    public boolean switchToWindow(WebDriver driver, String title)
    {
        mainWindowsHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles(); // Gets all the available windows
        for (String handle : handles)
        {
            driver.switchTo().window(handle); // switching back to each window in loop
            if (driver.getTitle().equals(title)) // Compare title and if title matches stop loop and return true
            {
                Narrator.logDebug("Switched to window " + Driver.getTitle());
                return true; // We switched to window, so stop the loop and come out of funcation with positive response
            }
        }
        driver.switchTo().window(mainWindowsHandle); // Switch back to original window handle
        return false; // Return false as failed to find window with given title.
    }

    public static String getCurrentDate()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public boolean scrollToElement(String elementXpath)
    {
        try
        {
            WebElement element = Driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Narrator.logDebug("Scrolled to element - " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error scrolling to element - " + elementXpath + " - " + e.getStackTrace());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

}
