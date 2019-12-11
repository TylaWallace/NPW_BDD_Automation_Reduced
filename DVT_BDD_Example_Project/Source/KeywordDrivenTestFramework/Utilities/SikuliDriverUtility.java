/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.testCaseId;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.sikuli.basics.Settings;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author Ferdinand
 * @editir jjoubert
 */
public class SikuliDriverUtility extends BaseClass 
{
    public Screen Desktop;
    
    public Region ScreenRegion;
    
    public String ScreenshotDirectory;
    
    Process seeTestManual;
    
    public boolean isDriverRunning = false;
    
    public SikuliDriverUtility(String screenShotDir)
    {
        this.Desktop = new Screen();
        this.ScreenshotDirectory = screenShotDir;
        Settings.OcrTextSearch = true;
        isDriverRunning = true;

    }
    
    public boolean isDriverRunning() {
        return isDriverRunning;
    }
    
    //<editor-fold defaultstate="collapsed" desc="See Test Manual Scripts">
    
    public boolean openSeeTest()
    {
        try
        {
            Desktop = new Screen();
            
            String seeTestExecutablePath = ApplicationConfig.SeeTestExecutablePath();
            this.seeTestManual = Runtime.getRuntime().exec(seeTestExecutablePath);
            
            if(!this.WaitForElementToAppear("SeeTestManualWindowTitle.PNG"))
            {
                Narrator.logError(" opening application - SeeTestManual - could not verify dialog window title");
                return false;
            }
            
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" opening application - SeeTestManual - please make sure that the application is installed and that the path is correclty set in the appConfig " + e.getMessage());
            return false;
        }
    }

    public boolean PressKey(String KeyToPress)
    {
        try
        {
            this.Desktop.type(KeyToPress);
            return true;
        }
        catch (Exception e)
        {
            System.err.println("[Error] Failed to press key: {" + KeyToPress + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean openDevice(String deviceListingRow, String deviceWindowTitle)
    {
        try
        {
            if(this.WaitForElementToAppearAdjacentTo(deviceListingRow, "deviceOfflineStatus.png", Enums.RelativePosition.Right))
            {
                Narrator.logError("Device disconnected - please connect device and confirm connection before running the test");
                return false;
            }
            
            
            
            if(!this.WaitForElementToAppearAdjacentTo(deviceListingRow,"ReadyStatus.png" ,Enums.RelativePosition.Right))
            {
                Narrator.logError("Device not ready for connection - please connect device and confirm connection before running the test");
                return false;
            }
            
            this.MouseClickElement(deviceListingRow);
            
            this.MouseClickElement("OpenDeviceIcon.png");
            
            this.WaitForElementToAppear(deviceWindowTitle);

            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" opening device, check connection - " + e.getMessage());
            return false;
        }
    
    }
    
    public boolean verifyElementPresent(String elementImagePath, int timeOutInMiliseconds)
     {
         try
         { 
            this.ScreenRegion = Desktop.wait(elementImagePath, timeOutInMiliseconds);
            
            if(this.ScreenRegion != null)
            {
                this.ScreenRegion.highlight(1);
                return true;
            }
            else
            { 
                Narrator.logDebug(" Element not present - " + elementImagePath);
                return false;
            }
         }
         catch(Exception e)
         {
             Narrator.logError(" verifying element present - " + elementImagePath + " -  - " + e.getMessage());
             return false;
         }
     }   
      
    public boolean exitSeeTest()
    {
        try
        {        
            this.seeTestManual.destroy();
            
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" closing application - SeeTest Manual - " + e.getMessage());
            return false;
        }
    
    }
    
    
    //</editor-fold>
    
    public void setScreenshotDirectory(String screenShotDir)
    {
        this.ScreenshotDirectory = screenShotDir;
    }
    
    public boolean MouseClickElement(String ImageFilePath)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                
                this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).click();
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to click element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean MouseHoverElement(String ImageFilePath)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                
                this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).hover();
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to hover element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean MouseDoubleClickElement(String ImageFilePath)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {          
                this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).highlight(1).doubleClick();
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to double click element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean MouseTripleClickElement(String ImageFilePath, String regionFile, int x, int y) {
        try {

            Region otpRegion = this.Desktop.wait(this.ScreenshotDirectory + regionFile, 30).highlight(1);

            Pattern otpPattern = new Pattern(this.ScreenshotDirectory + ImageFilePath).targetOffset(x, y);

            otpRegion.above().find(otpPattern).highlight(1).click();

            otpRegion.above().find(otpPattern).mouseDown(Button.LEFT);
            otpRegion.above().find(otpPattern).mouseUp();

            otpRegion.above().find(otpPattern).mouseDown(Button.LEFT);
            otpRegion.above().find(otpPattern).mouseUp();

            return true;

        } catch (Exception e) {

            Narrator.logError(" Failed to click element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean WaitForEitherImageToAppear(String imageOne, String imageTwo) {
        boolean result = false;
        if(this.WaitForElementToAppearThrowsNoError(imageOne)){
            result = true;
        }
        else if(this.WaitForElementToAppearThrowsNoError(imageTwo)){
            result = true;
        }
        
        return result;
        
    }
    
    public boolean WaitSpecificTimeElementAppear(String ImageFilePath, int timeOut) {
        try {
            this.Desktop.wait(this.ScreenshotDirectory + ImageFilePath, timeOut).highlight(1);

            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to find element on desktop, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean MouseRightClickElement(String ImageFilePath)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).highlight(1).rightClick();
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to right click element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    
    public boolean TypeTextAtElement(String ImageFilePath, String InputText)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).type(InputText);
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to enter text at element, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean WaitForElementToAppear(String ImageFilePath)
    {
        try
        {
            this.Desktop.wait(this.ScreenshotDirectory + ImageFilePath, ApplicationConfig.WaitTimeout());
    
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to find element on desktop, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    
    public void TakeScreenshot(String screenshotFileName, Boolean isError)
    {
        String imageFilePathString = "";
        
        if(testCaseId == null)
            return;

        try 
        {
            StringBuilder imageFilePathBuilder = new StringBuilder();
            // add date time folder and test case id folder
            imageFilePathBuilder.append(this.reportDirectory + "\\");
            
            relativeScreenShotPath = testCaseId + "\\";

            if (isError) {
                relativeScreenShotPath += "FAILED_";
            } else {
                relativeScreenShotPath += "PASSED_";
            }
            
            relativeScreenShotPath += testCaseId + "_" + screenshotFileName + ".png";

            imageFilePathBuilder.append(relativeScreenShotPath);

            //imageFilePathBuilder.append(this.generateDateTimeString() + ".png");
            imageFilePathString = imageFilePathBuilder.toString();
            
            setScreenshotPath(imageFilePathString);
        
            ScreenImage screenShot = this.Desktop.capture();

            FileUtils.copyFile(new File(screenShot.getFile()), new File(imageFilePathString.toString()));
        }
        catch(Exception e)
        {
            Narrator.logError(" could not take screenshot - " + imageFilePathString.toString() + " - " + e.getMessage());
        }
    }
    
    public String generateDateTimeString()
    {
      Date dateNow = new Date( );
      SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd_hh-mm-ss");
      
      return dateFormat.format(dateNow).toString();
    }
    public boolean copyToClipBoard()
    {
        
        try
        {
            this.Desktop.type("c", Key.C_CTRL);
    
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to copy content to clipboard via ctrl + C, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean pasteFromClipBoard()
    {
        
        try
        {
            this.Desktop.type("v", Key.C_CTRL);
    
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to paste content from clipboard via ctrl + V , error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean setClipboardContents(String aString)
    {
        try
        {
            StringSelection stringSelection = new StringSelection(aString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection , stringSelection);
    
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to clipboard contents , error :" + e.getMessage());
            return false;
        }
    }
    public String getClipboardContents() 
    {
      String result = "";
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      //odd: the Object param of getContents is not currently used
      Transferable contents = clipboard.getContents(null);
      
      if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) 
      {
        try 
        {
            
          result = (String)contents.getTransferData(DataFlavor.stringFlavor);
          
        }
        catch (Exception ex)
        {
          Narrator.logError(" Failed to read from clipboard - " + ex);
        }
      }
      return result;
    }

    private boolean WaitForElementToAppearAdjacentTo(String anchorPoint, String adjacentImage, Enums.RelativePosition relation) 
    {
        try
        {
            Region anchorPointRegion = this.Desktop.wait(this.ScreenshotDirectory + anchorPoint, ApplicationConfig.WaitTimeout()).highlight(1);
            
            if(anchorPointRegion == null)
                return false;
            
            
            switch(relation)
            {
                case Above:
                {
                    anchorPointRegion.above().find(this.ScreenshotDirectory + adjacentImage).highlight(1);
                    return true;
                }
                case Below:
                {
                    anchorPointRegion.below().find(this.ScreenshotDirectory + adjacentImage).highlight(1);
                    return true;
                }
                case Right:
                {
                    anchorPointRegion.right().find(this.ScreenshotDirectory + adjacentImage).highlight(1);
                    return true;
                }
                case Left:
                {
                    anchorPointRegion.left().find(this.ScreenshotDirectory + adjacentImage).highlight(1);
                    return true;
                }
            }
            
    
            return false;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to find element on desktop, image: {" + adjacentImage + "} adjacent to: {" + anchorPoint + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean WaitForElementToAppearThrowsNoError(String ImageFilePath) {
        try {
            this.Desktop.wait(this.ScreenshotDirectory + ImageFilePath, ApplicationConfig.WaitTimeout()).highlight(1);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean MouseClickHighlightedOrNormal(String normalPath, String highlightedPath) {
        try {
            if (this.WaitSpecificTimeElementAppear(normalPath, 5)) {

                this.Desktop.find(this.ScreenshotDirectory + normalPath).highlight(1).click();
                return true;

            } else if (this.WaitSpecificTimeElementAppear(highlightedPath, 5)) {

                this.Desktop.find(this.ScreenshotDirectory + highlightedPath).highlight(1).click();
                return true;

            }
            
            else{
                return false;
            }

        } catch (Exception e) {

            Narrator.logError(" Failed to click element, image: {" + highlightedPath + "}, error :" + e.getMessage());
            return false;
        }

    }
    
    public boolean Enter (String Imagepath)
    {
        try
        {
            if (!SikuliDriverInstance.WaitForElementToAppear(Imagepath))
            {
            Narrator.logError("Failed to click Image "+Imagepath+".");
            return false;
            }
            this.Desktop.type(Key.ENTER);            
    
            return true;
        }
        catch(Exception e)
        {
            Narrator.logError(" Failed to press key Enter, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean EnterText(String ImageFilePath, String Text)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                
                this.Desktop.type(Text);
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to enter text , error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean SelectDownArrowFromDropdownUsingKeys (String ImageFilePath, int ListPosition)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                
                //this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).click();
                
                for (int i = 0; i < (ListPosition); i++)
                {
                    this.Desktop.type(Key.DOWN);
                }

                this.Desktop.type(Key.ENTER);
                
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to select option# "+ListPosition+" from the dropdown list, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
    public boolean SelectUpArrowFromDropDownUsingKeys (String ImageFilePath, int ListPosition)
    {
        try
        {
            if(this.WaitForElementToAppear(ImageFilePath))
            {
                
                //this.Desktop.find(this.ScreenshotDirectory + ImageFilePath).click();
                
                for (int i = 0; i < (ListPosition); i++)
                {
                    this.Desktop.type(Key.UP);
                }

                this.Desktop.type(Key.ENTER);
                
                return true;
            }
            else
                return false;     
        }
        catch(Exception e)
        {
            
            Narrator.logError(" Failed to select option# "+ListPosition+" from the dropdown list, image: {" + ImageFilePath + "}, error :" + e.getMessage());
            return false;
        }
    }
    
}
