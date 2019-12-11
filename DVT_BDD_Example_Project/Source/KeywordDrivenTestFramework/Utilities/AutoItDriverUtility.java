/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Reporting.Narrator;
import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author SBeck
 */
public class AutoItDriverUtility extends BaseClass
{

    String jacobDllVersionToUse;
    public static AutoItX Driver;


    public AutoItDriverUtility()
    {
        try
        {
            if (jvmBitVersion().contains("32"))
            {
                jacobDllVersionToUse = "jacob-1.18-x86.dll";

            }
            else
            {
                jacobDllVersionToUse = "jacob-1.18-x64.dll";

            }

            File file = new File("Dependencies\\libs", jacobDllVersionToUse);
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

            Driver = new AutoItX();
        }
        catch (Exception e)
        {
            narrator.logError("Failed to launch AutoIT Driver. Error - " + e.getMessage());
        }
    }

    public boolean RunFile(String FileName)
    {

        try
        {
            Thread.sleep(100);
            Driver.run(FileName);

        }
        catch (Exception e)
        {
            narrator.logError("Failed to run file: " + FileName + ", error - " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean FileOpen(String FileName, String FilePath, int WindowSize)
    {

        try
        {
            
            Thread.sleep(100);
            Driver.run(FileName, FilePath, WindowSize);

        }
        catch (Exception e)
        {
            narrator.logError("Failed to run file: " + FileName + ", error - " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean ActivateWindow(String title)
    {
        try
        {
            Driver.winActivate(title, "");
            Driver.winWaitActive(title,"",10);
            return true;
        }
        catch (Exception e)
        {
            narrator.logError("Failed to Activate window of title: " + title + ", with error - " + e.getMessage());
            return false;
        }
    }

    public boolean DoubleClickElementByControl(String title, String control)
    {
        if (WaitForApplicationToExist(title))
        {
            try
            {
                if (ActivateWindow(title))
                {
                    Driver.controlClick(title, "", control + "[,clicks = 2]");
                    return true;
                }
                else
                {
                    narrator.logError("Failed to Activate window: " + title);
                return false;
                }
            }
            catch (Exception e)
            {
                narrator.logError("Failed to click window: " + title + ", at the control of: " + control + "." + e.getMessage());
                return false;
            }
            
        }
        else
        {
            narrator.logError("Window " + title + ", was not found.");
            return false;
        }
    }
    public boolean SelectFromDropDown (String title, String control, String Option)
    {
        try
        {
            Driver.winActivate(title);
            Driver.controlCommandSelectString(title, "", control, Option);
            return true;
        }
        catch(Exception e)
        {
            narrator.logError("Failed to select option: "+Option+", from dropdown: "+control+". Error - "+e.getMessage());
            return false;
        }
    }
    public boolean ClickElementByControl(String title, String control, String ControlText)
    {
        if (WaitForApplicationToExist(title))
        {
            try
            {
                Driver.winActivate(title, "");
                Driver.controlClick(title, ControlText, control);
            }
            catch (Exception e)
            {
                narrator.logError("Failed to click window: " + title + ", at the control of: " + control + "." + e.getMessage());
                return false;
            }
            return true;
        }
        else
        {
            narrator.logError("Window " + title + ", was not found.");
            return false;
        }
    }

    public boolean EnterTextByControl(String title, String Control, String Text)
    {
        Driver.winActivate(title);
        if (WaitForApplicationToExist(title))
        {
            try
            {
                Driver.winActivate(title);
                Driver.controlSend(title, "", Control, Text);
                return true;
            }
            catch (Exception e)
            {
                narrator.logError("Failed to enter text: " + Text + ", to the Control: " + Control + ". Error -" + e.getMessage());
                return false;
            }
        }
        else
        {
            narrator.logError("Window " + title + ", was not found.");
            return false;
        }
    }
    //Retrieve Text needs testing.
    public String RetrieveTextByControl(String title,String text, String Control)
    {   Driver.winActivate(title);
        String getText = "";
        if (WaitForApplicationToExist(title))
        {
            try
            {
                Driver.winActivate(title);
                getText = Driver.controlGetText(title,text,Control); 
                return getText;
                
            }
            catch (Exception e)
            {
                narrator.logError("Failed to retrieve text from the Control: " + Control + ". Error -" + e.getMessage());
                return getText;
            }
        }
        else
        {
            narrator.logError("Window " + title + ", was not found.");
            return getText;
        }
    }
    public boolean WaitForApplicationToExist(String title)
    {
        boolean elementFound = false;

        try
        {
            int waitCount = 0;
            while (!elementFound && waitCount < ApplicationConfig.WaitTimeout())
            {
                try
                {
                    if (Driver.winWaitActive(title, "", 10))
                    {
                        elementFound = true;
                        break;
                    }
                }
                catch (Exception e)
                {
                    narrator.logError("Failed to wait for Application: " + title + ", to open. Error - " + e.getMessage());
                }
                waitCount++;
            }
        }
        catch (Exception e)
        {
            narrator.logError("Failed to wait for Application: " + title + ", to open. Error - " + e.getMessage());
            return false;
        }
        return elementFound;
    }

    public boolean IESave()
    {
        try
        {
            String activeWindowHandle;
            String activeWindowTitle;
            String jacobDllVersionToUse;
            Float pixelColour;

            //Checks java version and uses the relevant jacob and autoit dlls
            if (jvmBitVersion().contains("32"))
            {
                jacobDllVersionToUse = "jacob-1.18-x86.dll";
            }
            else
            {
                jacobDllVersionToUse = "jacob-1.18-x64.dll";
            }

            File file = new File("Dependencies\\libs", jacobDllVersionToUse);
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

            AutoItX x = new AutoItX();

            activeWindowHandle = x.winGetHandle("[Class:IEFrame]", "");
            activeWindowTitle = x.winGetTitle("[HANDLE:" + activeWindowHandle + "]");
            pixelColour = x.pixelGetColor(x.controlGetPosX(activeWindowTitle, "", "[Class:DirectUIHWND;INSTANCE:1]"), x.controlGetPosY(activeWindowTitle, "", "[Class:DirectUIHWND;INSTANCE:1]"));

            //TO DO - exit criteria
            while (pixelColour < 0)
            {
                activeWindowTitle = x.winGetTitle("[HANDLE:" + activeWindowHandle + "]");
                pixelColour = x.pixelGetColor(x.controlGetPosX(activeWindowTitle, "", "[Class:DirectUIHWND;INSTANCE:1]"), x.controlGetPosY(activeWindowTitle, "", "[Class:DirectUIHWND;INSTANCE:1]"));
                Thread.sleep(500);
            }

            //IeSave
            x.winActivate(activeWindowTitle, "");
            x.controlGetHandle("[Class:IEFrame]", "", "[ClassNN:DirectUIHWND1]");
            Thread.sleep(1000);
            x.controlSend("[Class:IEFrame]", "", "[ClassNN:DirectUIHWND1]", "{TAB}");
            Thread.sleep(1000);
            x.controlSend("[Class:IEFrame]", "", "[ClassNN:DirectUIHWND1]", "{TAB}");
            Thread.sleep(1000);
            x.controlSend("[Class:IEFrame]", "", "[ClassNN:DirectUIHWND1]", "{enter}");
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] - " + e.getMessage());
            return false;
        }

        return true;
    }

    //Returns if the JVM is 32 or 64 bit version
    public static String jvmBitVersion()
    {
        return System.getProperty("sun.arch.data.model");
    }
}


