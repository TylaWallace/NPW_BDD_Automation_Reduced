/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nkelechi
 */
public class CucumberUtility
{

    public CucumberUtility()
    {
    }

    public void readCucumber(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;

        try
        {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
            }

        }
        catch (IOException e)
        {

            e.printStackTrace();

        }
        finally
        {

            try
            {

                if (br != null)
                {
                    br.close();
                }

                if (fr != null)
                {
                    fr.close();
                }

            }
            catch (IOException ex)
            {

                ex.printStackTrace();

            }

        }
    }

}
