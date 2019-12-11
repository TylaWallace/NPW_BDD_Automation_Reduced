/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.RetrievedTestValues;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Reporting.Narrator;
import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.appium.SeeTestIOSDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;

/**
 *
 * @author Sbeck
 */
public class AppiumDriverUtility extends BaseClass
{

    public static AppiumDriver Driver;
//    public static AndroidDriver Driver;
//    public RemoteWebDriver Driver;
    File fileIEDriver;
    public RetrievedTestValues retrievedTestValues;
    public String DriverExceptionDetail = "";
    public String Port = "";
    TestEntity testData;
    public Boolean _isDriverRunning = false;
    Runtime rt = Runtime.getRuntime();
    public static boolean isCMDRunning = false;

    public AppiumDriverUtility()
    {

        if (_isRemote == false)
        {



            if (isCMDRunning == false)
            {
                this.selectPlatform();

            }
            _isDriverRunning = true;
        }
        else
        {
            switch (currentPlatform)
            {
                case Android:

                    Driver = (AndroidDriver) setCapabilitiesAndroidRemoteRun(currentDevice.DeviceID, currentDevice.ServerURL);
                    _isDriverRunning = true;
                    break;

//                case iOS :
//                    Driver = (IOSDriver) 
//                    _isDriverRunning = true;
//                    break;
            }

        }

    }

    public WebDriver setCapabilitiesWithApk()
    {

        try
        {
            //The APK information is set in the appconfig
            File app = new File(currentDeviceConfig.ApplicationFilePath, currentDeviceConfig.ApplicationName);

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(currentDevice.CapabilityName, currentDevice.DeviceID);
            capabilities.setCapability("deviceName", currentDeviceConfig.deviceName);
            capabilities.setCapability("platformName", currentDeviceConfig.platformName);
            capabilities.setCapability("automationName", currentDeviceConfig.automationName);
            capabilities.setCapability(CapabilityType.VERSION, currentDeviceConfig.Version);

            if (currentBrowser.equals(currentBrowser.mobileChrome))
            {
                capabilities.setCapability("app", "");
                capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
            }
            else
            {
                capabilities.setCapability("app", app.getAbsolutePath());
            }

            //Call the following use RunActivity
//          capabilities.setCapability("appPackage", currentDeviceConfig.appPackage);
//          capabilities.setCapability("appActivity", currentDeviceConfig.appActivity);
            //launch dialer           
//            capabilities.setCapability("androidPackage", "com.android.dialer");
//            capabilities.setCapability("appActivity", "DialtactsActivity");
            //-------------
            capabilities.setCapability("newCommandTimeout", 360);
            isCMDRunning = true;
            return new SeeTestAndroidDriver<>(new URL(currentDevice.ServerURL), capabilities);
        }

        catch (Exception e)
        {
            Narrator.logError("Error setting Capabilities - " + e.getMessage());
            return null;
        }
    }

    public WebDriver setCapabilitiesWithIpa()
    {

        try
        {
            //The IPA information is set in the appconfig
            //dc.setCapability(SeeTestCapabilityType.TEST_NAME, testName);
            //capabilities.setCapability(MobileCapabilityType.UDID, currentDevice.DeviceID);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(SeeTestCapabilityType.REPORT_DIRECTORY, reportDirectory);
            capabilities.setCapability(SeeTestCapabilityType.REPORT_FORMAT, "xml");
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Enums.DeviceConfig.Santam_iOS.appPackage);
            //capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.Browser");
            
            
            capabilities.setCapability(SeeTestCapabilityType.INSTRUMENT_APP, true);
            //device timeout
            capabilities.setCapability(SeeTestCapabilityType.WAIT_FOR_DEVICE_TIMEOUT_MILLIS, 1500);
            //command timeout
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 5);
            
//            if(currentBrowser.equals(currentBrowser.mobileSafari))
//            {
//               capabilities.setCapability("browser", "Browser"); 
//            }
//            else
//            {
//                 capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, currentDeviceConfig.appPackage);
//            }
            isCMDRunning = true;
            return new SeeTestIOSDriver<>(new URL(currentDevice.ServerURL), capabilities);

        }

        catch (Exception e)
        {
            Narrator.logError("Error setting Capabilities - " + e.getMessage());
            return null;
        }
    }

    public WebDriver setCapabilitiesAndroidRemoteRun(String deviceUDID, String serverURL)
    {

        try
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("udid", deviceUDID);
            capabilities.setCapability("deviceName", deviceUDID);
            capabilities.setCapability("appPackage", currentDeviceConfig.appPackage);
            capabilities.setCapability("appActivity", currentDeviceConfig.appActivity);
            capabilities.setCapability("platformName", currentDevice.platform);
            capabilities.setCapability("newCommandTimeout", 360);
            capabilities.setCapability("onResetTimeout", 360);

            return new RemoteWebDriver(new URL(serverURL), capabilities)
            {
            };
        }

        catch (Exception e)
        {
            Narrator.logError("Error setting Capabilities - " + e.getMessage());
            return null;
        }
    }

    public void startAndroidServer(String directory)
    {
        try
        {
            Port = currentDevice.ServerURL;
            String new_dir = directory;
            String[] URLinfo = Port.split(":");
            String[] Portinfo = URLinfo[2].split("/");
            String PortNumber = Portinfo[0];

            rt.exec("cmd /c start " + System.getProperty("user.home") + "\\AppData\\Roaming\\npm\\appium.cmd -p" + PortNumber + " -bp 2251 -U " + currentDevice.DeviceID);
            Thread.sleep(20000);
           
            Narrator.logDebug("Started Android Appium Server");

        }
        catch (Exception e)
        {
            Narrator.logError("Failed to start server - " + e.getMessage());
        }

    }

    public void startIOSServer(String directory)
    {
        try
        {
            String tellCommand = "tell application \"Terminal\" to do script \"appium\"";

            String[] command =
            {
                "osascript", "-e",
                tellCommand
            };

            ProcessBuilder pBuilder = new ProcessBuilder(command);
            pBuilder.start();

            pause(8500);
        }
        catch (Exception e)
        {
            Narrator.logError("Failed to start server - " + e.getMessage());
        }

    }

    public boolean isDriverRunning()
    {
        return _isDriverRunning;
    }

    public void selectPlatform()
    {
        switch (currentDevice.platform)
        {
            case Android:
//                startAndroidServer(currentDevice.ServerURL);
                int counter = 1;
                Driver = (AndroidDriver) setCapabilitiesWithApk();
                while(Driver == null && counter < 61)
                {
                   Driver = (AndroidDriver) setCapabilitiesWithApk();
                    pause(500);
                    counter++;
                    
                }
                _isDriverRunning = true;
                break;
            case iOS:
                //startAndroidServer(currentDevice.ServerURL);
                Driver = (IOSDriver) setCapabilitiesWithIpa();
                AppiumDriverUtility.Driver.removeApp(Enums.DeviceConfig.Santam_iOS.appPackage);
                AppiumDriverUtility.Driver.installApp(Enums.DeviceConfig.Santam_iOS.ApplicationFilePath+"\\"+Enums.DeviceConfig.Santam_iOS.ApplicationName);
                AppiumDriverUtility.Driver.launchApp();
                _isDriverRunning = true;
                break;
        }
        retrievedTestValues = new RetrievedTestValues();
    }

    public void Reset()
    {
        try
        {

            Runtime close = Runtime.getRuntime();
            close.exec("taskkill /IM cmd.exe");
            Narrator.logDebug("Closed CMD");
        }
        catch (Exception e)
        {
            Narrator.logError("Error restarting the driver - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }

    }

    public void shutDown()
    {
        retrievedTestValues = null;
        try
        {
            Runtime close = Runtime.getRuntime();
            Driver.quit();
            close.exec("taskkill /IM cmd.exe");
            Narrator.logDebug("Shutdown Server and closed CMD");

        }
        catch (Exception e)
        {
            Narrator.logError("Error shutting down driver - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        _isDriverRunning = false;
    }

    public String get(final String url)
    {
        Driver.get(url);
        Narrator.logDebug("Navigated to " + url);
        return Driver.getPageSource();

    }

    public boolean Navigate(String url)
    {
        try
        {
            Driver.navigate().to(url);
            
           
        }
        catch (Exception e)
        {
            Narrator.logError("Error - " + e.getMessage());;
            return false;
        }
        return true;
    }

    public void getPageObjects(String fileName)
    {
        try
        {
            File file = new File(System.getProperty("user.dir") + "/iOS PageObjects/" + fileName + ".xml");
            FileWriter fw = new FileWriter(file);
            fw.append(AppiumDriverInstance.Driver.getPageSource());
            fw.close();
        }
        catch (Exception e)
        {
            Narrator.logError("[ERROR] - Failed to write to file - " + e.getMessage());
        }
    }

    public boolean elementGestureTest(String Id)
    {
        //SikuliDriverInstance.Desktop.find(SikuliDriverInstance.ScreenshotDirectory + ImageFilePath).sw;
        MobileElement e = (MobileElement) Driver.findElementById(Id);

        //SikuliDriverInstance.Desktop.find(SikuliDriverInstance.ScreenshotDirectory + ImageFilePath);
        e.swipe(io.appium.java_client.SwipeElementDirection.UP, 2000);
        e.swipe(io.appium.java_client.SwipeElementDirection.DOWN, 2000);
        Narrator.logDebug("Swiped to element");
        return true;
    }

    public enum SwipeElementDirection
    {
        /**
         * Up from the center of the lower
         */
        UP
        {
            @Override
            void swipe(AppiumDriver driver, MobileElement element, int duration)
            {
                Point p = element.getCenter();
                Point location = element.getLocation();
                Dimension size = element.getSize();
                driver.swipe(p.getX(), location.getY() + size.getHeight(), p.getX(), location.getY(), duration);
            }
        },
        /**
         * Down from the center of the upper
         */
        DOWN
        {
            @Override
            void swipe(AppiumDriver driver, MobileElement element, int duration)
            {
                Point p = element.getCenter();
                Point location = element.getLocation();
                Dimension size = element.getSize();
                driver.swipe(p.getX(), location.getY(), p.getX(), location.getY() + size.getHeight(), duration);
            }
        },
        /**
         * To the left from the center of the rightmost
         */
        LEFT
        {
            @Override
            void swipe(AppiumDriver driver, MobileElement element, int duration)
            {
                Point p = element.getCenter();
                Point location = element.getLocation();
                Dimension size = element.getSize();
                driver.swipe(location.getX() + size.getWidth(), p.getY(), location.getX(), p.getY(), duration);
            }
        },
        /**
         * To the right from the center of the leftmost
         */
        RIGHT
        {
            @Override
            void swipe(AppiumDriver driver, MobileElement element, int duration)
            {
                Point p = element.getCenter();
                Point location = element.getLocation();
                Dimension size = element.getSize();
                driver.swipe(location.getX(), p.getY(), location.getX() + size.getWidth(), p.getY(), duration);
            }
        };

        void swipe(AppiumDriver driver, MobileElement element, int duration)
        {
        }
    }

//   public boolean swipe() //845,477, 945,525
//    {
//        try
//        {
//            JavascriptExecutor js = (JavascriptExecutor) Driver;
//    HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//    swipeObject.put("startX", 1315.5);
//    swipeObject.put("startY", 978.4);
//    swipeObject.put("endX", 1315.5);
//    swipeObject.put("endY", 596.5);
//    swipeObject.put("duration", 0.20);
//    js.executeScript("mobile: swipe", swipeObject);
//          
//            return true;
//        }
//        catch(Exception e)
//        {
//            System.err.println("Error clicking element by Id - " + e.getMessage());
//            this.DriverExceptionDetail = e.getMessage();
//            return false;      
//        }
//    }
    public boolean scrollDown(String element)

    {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor) Driver;
            System.out.println("swiping to element " + element);
            js.executeScript("mobile: scrollTo", element);
            Driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//            JavascriptExecutor js = (JavascriptExecutor) Driver;
//            WebElement element = Driver.findElement(By.className(Name));
//            HashMap<String, String> arguments = new HashMap<String, String>();
//            arguments.put("element",((RemoteWebElement) element).getId());
//            js.executeScript("mobile: scrollTo", arguments);
//           JavascriptExecutor js = (JavascriptExecutor) Driver;
//
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//
//        scrollObject.put("direction", "down");
//
//        scrollObject.put("text", tillLastLink);
//
//        scrollObject.put("element",( (RemoteWebElement) scroll).getId());
//
//        js.executeScript("mobile: scrollTo", scrollObject);
//        
//        WebElement scrollableView = Driver.findElement(By.id("********/broadcast_scroll"));
//
//        scrollDown(scrollableView, "enter text till you want to scroll"); 
            return true;

        }
        catch (Exception e)
        {
            Narrator.logError("Could not find verification text - " + e.getMessage());
            return false;
        }
    }

    public boolean verifyIfScreenIsLoaded(String xpath, String verificationText)
    {
        try
        {
            String actionBarTitle = Driver.findElement(By.xpath(xpath)).getText();
            if (actionBarTitle.toUpperCase().trim().equals(verificationText.toUpperCase().trim()))
            {
                Narrator.logDebug("Verification text found...Screen loaded successfully");
                return true;
            }
            else
            {
                Narrator.logError("Could not find verification text - ");
                return false;
            }

        }
        catch (Exception e)
        {
            Narrator.logError("Could not find verification text - " + e.getMessage());
            return false;
        }
    }

    public boolean switchToFrameByXpath(String xpath)
    {
        int waitCount = 0;
        try
        {
            while (waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    Driver.switchTo().frame(xpath);
                    return true;
                }
                catch (Exception e)
                {
                    Thread.sleep(500);
                    waitCount++;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            Narrator.logError("Error switching to frame by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToLastDuplicateFrameByXpath(String Xpath)
    {
        int waitCount = 0;
        try
        {
            this.switchToDefaultContent();
            while (waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    List<WebElement> iframes = Driver.findElements(By.xpath(Xpath));

                    Driver.switchTo().frame((WebElement) iframes.toArray()[iframes.size() - 1]);
                    return true;
                }
                catch (Exception e)
                {
                    Thread.sleep(500);
                    waitCount++;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            Narrator.logError("Error switching to last duplicate frame by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToDefaultContent()
    {
        try
        {
            Driver.switchTo().defaultContent();
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error switching to default content  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean moveToElementByXpath(String Xpath)
    {
        try
        {
            Actions moveTo = new Actions(Driver);
            moveTo.moveToElement(Driver.findElement(By.xpath(Xpath)));
            moveTo.perform();
            Narrator.logDebug("Moved to element at xpath - " + Xpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error moving to element - " + Xpath + " - " + e.getStackTrace());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToDefaultContentWhenElementNoLongerVisible(String previousFrameXpath)
    {
        try
        {
            waitForElementNoLongerPresentByXpath(previousFrameXpath);
            Driver.switchTo().defaultContent();
            System.out.println("Successfully switched to default content, current frame handle = " + Driver.getWindowHandle() + ", previous frameId - " + previousFrameXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error switching to default content when element is no longer visible - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
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
                        Narrator.logDebug("Element no longer present at xpath - " + elementXpath);
                        elementFound = false;
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
            Narrator.logError(" waiting for element to be no longer present by ID  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean acceptAlertDialog()
    {
        int waitCount = 0;
        try
        {
            while (waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    Driver.switchTo().alert().accept();
                    Narrator.logDebug("Alert dialog accepted");
                    return true;
                }
                catch (Exception e)
                {
                    Thread.sleep(500);
                    waitCount++;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            Narrator.logError("Error accepting alert dialog - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean selectByValueFromDropDownListUsingXpath(String Xpath, String valueToSelect)
    {
        try
        {
            this.waitForElementByXpath(Xpath);
            Select dropDownList = new Select(Driver.findElement(By.xpath(Xpath)));
            dropDownList.selectByValue(valueToSelect);
            Narrator.logDebug("Selected: " + valueToSelect + " from Xpath - " + Xpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error selecting from dropdownlist by value using Name - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean selectByTextFromDropDownListUsingXpath(String Xpath, String Text)
    {
        try
        {
            this.waitForElementByXpath(Xpath);
            Select dropDownList = new Select(Driver.findElement(By.xpath(Xpath)));
            dropDownList.selectByVisibleText(Text);
            Narrator.logDebug("Selected: " + Text + " from Xpath - " + Xpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error selecting from dropdownlist by text using name - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public void waitUntilElementEnabledByXpath(String ElementXpath)
    {
        try
        {
            int counter = 0;
            boolean isEnabled = false;
            WebElement elementToWaitFor = Driver.findElement(By.xpath(ElementXpath));
            isEnabled = elementToWaitFor.isEnabled();
            while (!isEnabled && counter < 60)
            {
                counter++;
                Thread.sleep(500);
            }
        }
        catch (Exception e)
        {
            Narrator.logError("Error waiting for element to be enabled - " + e.getMessage());
        }

    }

    public boolean checkBoxSelectionByXpath(String elementXpath, boolean mustBeSelected)
    {
        try
        {
            Thread.sleep(2000);
            this.waitForElementByXpath(elementXpath);
            this.waitUntilElementEnabledByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement checkBox = Driver.findElement(By.xpath(elementXpath));
            if (checkBox.isSelected() != mustBeSelected)
            {
                checkBox.click();
            }
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error selecting checkbox by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean navigateToPreviousScreen()
    {
        try
        {
            Driver.navigate().back();
            Narrator.logDebug("Navigated Back");
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Failed to navigate to the previous screen - " + e.getMessage());
            return false;
        }

    }

    public boolean validateElementTextValueByXpath(String elementXpath, String elementText)
    {
        try
        {
            if (waitForElementByXpath(elementXpath))
            {
                WebElement elementToValidate = Driver.findElement(By.xpath(elementXpath));
                String textDetected = elementToValidate.getText();
                if (textDetected.contains(elementText))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            Narrator.logError("Error validating element text value by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public void WaitUntilDropDownListPopulatedByXpath(String elementXpath)
    {

        try
        {
            this.waitForElementByXpath(elementXpath);
            int waitCount = 0;
            List<WebElement> optionsList;
            while (waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    Select dropDownList = new Select(Driver.findElement(By.xpath(elementXpath)));
                    optionsList = dropDownList.getOptions();
                    if (optionsList.size() > 0)
                    {
                        break;
                    }
                }
                catch (Exception e)
                {

                }
                Thread.sleep(500);
                waitCount++;
            }
        }
        catch (Exception e)
        {
            Narrator.logError("Error waiting for dropdownlist to be populated by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
    }

    public boolean clickElementbyXpath(String elementXpath)
    {
        try
        {
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, 60);
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            Narrator.logDebug("Clicked element at xpath - " + elementXpath);
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error clicking element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean enterTextByXpath(String elementXPath, String textToEnter)
    {
        try
        {
            this.waitForElementByXpath(elementXPath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXPath));
            elementToTypeIn.sendKeys(textToEnter);
            Narrator.logDebug("Entered text : " + textToEnter + " to xpath - " + elementXPath);
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error entering text - " + elementXPath + " - " + e.getMessage());
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
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null)
                    {
                        Narrator.logDebug("Element found at xpath - " + elementXpath);
                        elementFound = true;
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

    public void pause(int milisecondsToWait)
    {
        try
        {
            Thread.sleep(milisecondsToWait);
        }
        catch (Exception e)
        {

        }
    }

    public void takeScreenShot(String screenShotDescription, boolean isError)
    {
        String imageFilePathString = "";

        if (testCaseId == null)
        {
            return;
        }

        if (Driver == null)
        {
            return;
        }

        try
        {
            StringBuilder imageFilePathBuilder = new StringBuilder();
            // add date time folder and test case id folder
            imageFilePathBuilder.append(AppiumDriverUtility.reportDirectory + "\\");
            // + testCaseId + "\\"

            relativeScreenShotPath = testCaseId + "\\";
            String PorF="";
            if (isError)
            {
//                imageFilePathBuilder.append("FAILED_");
                PorF = "FAILED_";
            }
            else
            {
//                imageFilePathBuilder.append("PASSED_");
                PorF = "PASSED_";
            }

            relativeScreenShotPath +=PorF+ testCaseId + "_" + screenShotDescription + ".png";

            imageFilePathBuilder.append(relativeScreenShotPath);

            if (imageFilePathBuilder.toString().contains("FAILED_"))
            {
                relativeScreenShotPath = "FAILED_" + relativeScreenShotPath;
            }
            else if (imageFilePathBuilder.toString().contains("PASSED_"))
            {
                relativeScreenShotPath = "PASSED_" + relativeScreenShotPath;
            }

            //imageFilePathBuilder.append(this.generateDateTimeString() + ".png");
            imageFilePathString = imageFilePathBuilder.toString();

            setScreenshotPath(imageFilePathString);

            if (AppiumDriverInstance != null && AppiumDriverInstance.isDriverRunning())
            {

                File screenShot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
                
                if (currentBrowser.equals(currentBrowser.mobileSafari))
                {
                    BufferedImage img = ImageIO.read(screenShot);
                    BufferedImage dest = img.getSubimage(0,100,640,780);

                    ImageIO.write(dest,"png",screenShot);
                }            
                
                FileUtils.copyFile(screenShot, new File(imageFilePathString.toString()));
            }
        }
        catch (WebDriverException | IOException e)
        {
            Narrator.logError("[Error] could not take screenshot - " + imageFilePathString.toString() + " - " + e.getMessage());
        }
    }

    public String generateDateTimeString()
    {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

        return dateFormat.format(dateNow).toString();
    }

    public void CloseChromeInstances() throws IOException
    {
        if (currentBrowser.equals(Enums.BrowserType.Chrome))
        {
            String TASKLIST = "tasklist";
            String KILL = "taskkill /IM ";
            String line;
            String serviceName = "chrome.exe";
            Process p = Runtime.getRuntime().exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            Narrator.logDebug("Closing Chrome tasks...");
            while ((line = reader.readLine()) != null)
            {

                if (line.contains(serviceName))
                {
                    Runtime.getRuntime().exec(KILL + serviceName);
                }
            }
        }
    }

    public boolean TabAndEnterText(String textToEnter)
    {
        try
        {
            Actions action = new Actions(Driver);
            action.sendKeys(Key.TAB, textToEnter);
            action.perform();
            return true;

        }
        catch (Exception e)
        {
            Narrator.logError(" Failed to Tab " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public String retrieveTextByXpath(String xpath)
    {
        String retrievedText = "";
        try
        {
            waitForElementByXpath(xpath);
            WebElement elementToRead = Driver.findElement(By.xpath(xpath));
            retrievedText = elementToRead.getText();
            Narrator.logDebug("Text:" + retrievedText + " found at xpath - " + xpath);
            return retrievedText;
        }
        catch (Exception e)
        {
            System.err.println("Error reading text from element - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

//    public boolean RunActivity(String Package, String Activity)
//    {
//        try
//        {
//            Driver.startActivity(Package, Activity);
//            return true;
//        }
//        catch (Exception e)
//        {
//            Narrator.logError("Failed to run activity, error - " + e.getMessage());
//            return false;
//        }
//
//    }
    public boolean SetOrientationPortrait()
    {
        try
        {
            ScreenOrientation orientation = Driver.getOrientation();
            if (orientation.value().equals(ScreenOrientation.PORTRAIT))
            {
                narrator.logDebug("Tried to change orientation to Portrait, but Orientation already was Portrait.");
                return true;
            }
            else
            {
                Driver.rotate(ScreenOrientation.PORTRAIT);
                narrator.logDebug("Screen orientation set to portrait.");
                return true;
            }
        }
        catch (Exception e)
        {
            narrator.logError("Failed to change screen orientation to Portrait.");
            return false;
        }
    }

    public boolean SetOrientationLandscape()
    {
        try
        {
            ScreenOrientation orientation = Driver.getOrientation();
            if (orientation.value().equals(ScreenOrientation.LANDSCAPE))
            {
                narrator.logDebug("Tried to change orientation to Landscape, but Orientation already was Landscape.");
                return true;
            }
            else
            {
                Driver.rotate(ScreenOrientation.LANDSCAPE);
                narrator.logDebug("Screen orientation set to Landscape.");
                return true;
            }
        }
        catch (Exception e)
        {
            narrator.logError("Failed to change screen orientation to Landscape.");
            return false;
        }
    }

    public boolean waitForElementById(String id)
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
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id))) != null)
                    {
                        elementFound = true;
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
            Narrator.logError(" waiting for element by Xpath '" + id + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean clickElementbyId(String id)
    {
        try
        {
            waitForElementById(id);
            WebDriverWait wait = new WebDriverWait(Driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
            WebElement elementToClick = Driver.findElement(By.id(id));
            elementToClick.click();

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error clicking element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean enterTextByID(String elementID, String textToEnter)
    {
        try
        {
            this.waitForElementById(elementID);
            WebElement elementToTypeIn = Driver.findElement(By.id(elementID));
            elementToTypeIn.sendKeys(textToEnter);

            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error entering text - " + elementID + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public String retrieveTextByID(String elementID)
    {
        String retrievedText = "";
        try
        {

            this.waitForElementByXpath(elementID);
            WebElement elementToRead = Driver.findElement(By.id(elementID));
            retrievedText = elementToRead.getText();
            System.out.println("[Info]Text retrieved successfully from element - " + elementID);
            return retrievedText;

        }
        catch (Exception e)
        {
            System.err.println("[Error] Failed to retrieve text from element ID - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    //jason seekoei
    public boolean swipeByCoordinates(int x1, int y1, int x2, int y2, int time)
    {

        try
        {
            Driver.swipe(x1, y1, x2, y2, time);
            System.out.println("[Info]Swiped screen successfully from postion - (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
            return true;

        }
        catch (Exception e)
        {
            System.err.println("[Error] Failed to swipe screen from postion - (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")" + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public void stopAndroidServer()
    {
        retrievedTestValues = null;
        try
        {
            Runtime close = Runtime.getRuntime();
            Driver.quit();
            close.exec("taskkill /IM cmd.exe");
            close.exec("c:\\windows\\system32\\TASKKILL.exe /F /IM adb.exe");
        }
        catch (Exception e)
        {
            Narrator.logError("Error shutting down driver - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        _isDriverRunning = false;
    }    
    
    //Mugammad Added
    public boolean quitTerminal()
    {
        try
        {
            String tellCommand = "tell application \"Terminal\" to quit";

            String[] command =
            {
                "osascript", "-e",
                tellCommand
            };

            ProcessBuilder pBuilder = new ProcessBuilder(command);
            pBuilder.start();

            System.out.println("Terminal exited successfully.");
            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Failed to quit terminal - " + e.getMessage());
            return false;
        }
    }
    
    //Mugammad Added
    public void stopIOSServer()
    {
        String[] killNodes =
        {
            "/usr/bin/killall", "-9", "node"
        };
        String[] killProxy =
        {
            "/usr/bin/killall", "-9", "ios_webkit_debug_proxy"
        };
        try
        {
            Runtime.getRuntime().exec(killNodes);
            System.out.println("Appium server stopped.");
            Runtime.getRuntime().exec(killProxy);
            System.out.println("iProxy server stopped.");
            quitTerminal();
        }
        catch (Exception e)
        {
            Narrator.logError("Error stopping iOS server - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }

        quitTerminal();
    }
     
    public boolean waitForElementById(String id, int timeout)
    {
        boolean elementFound = false;
        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < timeout)
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, 1);
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id))) != null)
                    {
                        elementFound = true;
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
            Narrator.logError(" waiting for element by ID '" + id + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }
    
    public boolean waitForElementByID(String elementID)
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
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID))) != null)
                    {
                        elementFound = true;
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
            Narrator.logError(" waiting for element by ID '" + elementID + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }
    
    public boolean clickUsingTouchActions(String xpath)
    {
        try
        {
            WebElement elementToTouch = Driver.findElement(By.xpath(xpath));
            TouchAction touch = new TouchAction(Driver);
            touch.longPress(elementToTouch, 1).release().perform();
            return true;
        }
        catch (Exception e)
        {
            this.DriverExceptionDetail = e.getMessage();
            Narrator.logError(e.getMessage());
            return false;
        }
    }
    
    public boolean holdAndSlideByCoOrdinates(int startX, int startY, int endX, int endY)
    {
        try
        {
            AppiumDriverInstance.Driver.swipe(startX, startY, endX, endY, 3000);

            return true;
        }
        catch (Exception e)
        {
            narrator.logError("Failed to scroll");
            return false;
        }
    }
    

    public boolean checkElementPresenceByXpath(String elementXpath)
    {
        boolean returnValue = false;
        try
        {
            waitForElementByXpath(elementXpath);
            WebElement element = Driver.findElement(By.xpath(elementXpath));

            if (element.isDisplayed())
            {
                returnValue = true;
            }
        }
        catch (Exception e)
        {
            returnValue = false;
        }
        return returnValue;
    }
    
    public boolean clickElementbyIdWithoutWait(String id)
    {
        try
        {
            //waitForElementById(id);
            //WebDriverWait wait = new WebDriverWait(Driver, 60);
//            wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
            WebElement elementToClick = Driver.findElement(By.id(id));
            elementToClick.click();

            return true;
        }
        catch (Exception e)
        {
            Narrator.logError("Error clicking element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean waitForElementByXpath(String elementXpath, int timeout)
    {
        boolean elementFound = false;
        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < timeout)
            {
                try
                {
                    WebDriverWait wait = new WebDriverWait(Driver, 1);
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null)
                    {
                        elementFound = true;
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
     
    public boolean clearTextByXpath(String elementXPath)
    {
        try
        {
            this.waitForElementByXpath(elementXPath);
            WebElement elementToClear = Driver.findElement(By.xpath(elementXPath));

            String text = elementToClear.getAttribute("text");
            int textLength = text.length();
            while (textLength >= 0)
            {
                text = elementToClear.getAttribute("text");
                if (text.length() != 0)
                {
                    elementToClear.sendKeys(Keys.DELETE);
                    textLength--;
                }
                else
                {
                    textLength = -1;
                }
            }
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error clearing text - " + elementXPath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
    
    public boolean clearTextByXpathiOS(String elementXPath)
    {
        try
        {
            this.waitForElementByXpath(elementXPath);
            WebElement elementToClear = Driver.findElement(By.xpath(elementXPath));

            String text = elementToClear.getAttribute("text");
            int textLength = text.length();
            while (textLength >= 0)
            {
                text = elementToClear.getAttribute("text");
                if (text.length() != 0)
                {
                    elementToClear.clear();
                    textLength--;
                }
                else
                {
                    textLength = -1;
                }
            }
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error clearing text - " + elementXPath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }
}
