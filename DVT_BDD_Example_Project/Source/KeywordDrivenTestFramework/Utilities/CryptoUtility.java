/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import static java.lang.System.err;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author rrawoot
 */
public class CryptoUtility 
{
    public CryptoUtility(){}
    
    String masterPass = "DVT_TestAutomation";
    
    public String encrypt(String rawString)
    {
        String encryptedString = "";
        
        try
        {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(masterPass);

            encryptedString = textEncryptor.encrypt(rawString);

            //String plainText = textEncryptor.decrypt(myEncryptedText);
        }
        catch(Exception ex)
        {
            err.println("[ERROR] Failed to encrypt string - " + ex.getMessage());
        }
        return encryptedString;
    }
    
    
    public String decrypt(String encryptedString)
    {
        String rawString = "";
        
        try
        {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(masterPass);

            rawString = textEncryptor.decrypt(encryptedString);
 
        }
        catch(Exception ex)
        {
            err.println("[ERROR] Failed to descrypt string - " + ex.getMessage());
        }
        return rawString;
    }
}
