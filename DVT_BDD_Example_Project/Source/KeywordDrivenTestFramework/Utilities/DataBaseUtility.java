/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.DataColumn;
import KeywordDrivenTestFramework.Entities.DataRow;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.Enums.ResultStatus;
import KeywordDrivenTestFramework.Reporting.Narrator;
import static java.lang.System.err;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedList;
/**
 *
 * @author om22925
 */
public class DataBaseUtility extends BaseClass
{

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    ApplicationConfig appConfig = new ApplicationConfig();
    
    public void initVar() 
    {
        conn = null;
        stmt = null;
        rs = null;
    }

    public static void closeRS(ResultSet rs) 
    {
        try {
            if (rs != null) 
            {
                rs.close();
            }
        } catch (Exception ex) 
        {
            Narrator.logError("Error unable to close RS, fault -" + ex.getMessage());
        }
    }

    public static void closeStmt(Statement stmt)
    {
        try {
            if (stmt != null) 
            {
                stmt.close();
            }
        } catch (Exception ex)
        {
            Narrator.logError("Error unable to close Statement, fault -" + ex.getMessage());
        }
    }

    public static void closeConnection(Connection conn) 
    {
        try {
            if (conn != null) 
            {
                conn.close();
            }
        } catch (Exception ex) 
        {
            Narrator.logError("Error unable to close Connection, fault -" + ex.getMessage());
        }
    }
    
    public void closeDB() 
    {
            closeRS(rs);
            closeStmt(stmt);
            closeConnection(conn);      

    }

    public Connection formConnection(String dbName, String dbUser, String dbPassword)
    {
        try
        {
            Class.forName(currentDatabase.Driver);
            
            Connection con = DriverManager.getConnection(dbName, dbUser, dbPassword);
            Narrator.logDebug("[Info] Connection to db: " + currentDatabase.Driver + " found");
            return con;
        }
        catch(Exception e)
        {
            Narrator.logError("[Error] Connection to db: " + currentDatabase.Driver + " not found - " + e.getMessage());            
            return null;
        }
        
    }
    
    public Boolean connectToDB()  
    {
        try
        {
            conn = formConnection(currentDatabase.ConnectionString, currentDatabase.username, currentDatabase.password);

            Narrator.logDebug("[Info] Successfully connected to the " + currentDatabase.ConnectionString + " db");
            return true;
        }
    
        catch(Exception e)
        {
            Narrator.logError("[Error] Unable to connect to the " + currentDatabase.ConnectionString + " db, fault - "+e.getMessage());
            return false;
        }
            
    }
    
    public ResultSet RunQuery(String Statement) 
    {
       initVar();
       try 
        {
            connectToDB();
            
            stmt = conn.createStatement();
            
            String sql = Statement;
            ResultSet rs = stmt.executeQuery(sql);
            
            
            Narrator.logDebug("[Info] Successfully inserted test pack record into the Local DB");
            return rs;
            
        } 
        catch (Exception ex) 
        {
           Narrator.logError("[Error] Failed to insert the test pack record, fault -" + ex.getMessage());
            closeDB();
            
        }
       return rs;
    }
    
    
    
    public  LinkedList <DataRow> resultSetToArrayList(ResultSet rs2)
     {
         
         LinkedList <DataRow> dataRows = new LinkedList<>();
         
         try
         {
    
            ResultSetMetaData md = rs2.getMetaData();

            int columns = md.getColumnCount(); 

            DataColumn column = null;

            rs2.absolute(0);

            while (rs2.next()) 
            {
                DataRow newRow = new DataRow();

                for (int i = 1; i <= columns; ++i) 
                {
                    
                    // NOT TYPE SAFE!
                    if(rs2.getString(i) == null)
                    {

                       column = new DataColumn(md.getColumnName(i), " ", ResultStatus.UNCERTAIN);
                       newRow.DataColumns.add(column);
                    }
                    else
                    {
                       column = new DataColumn(md.getColumnName(i), rs2.getString(i), ResultStatus.UNCERTAIN);
                       newRow.DataColumns.add(column);
                    }

                }

                dataRows.add(newRow);  
            }
         }
         catch(Exception ex)
         {
             err.println("[INFO] Failed to convert result set to datarows - " + ex.getMessage());
         }

         return dataRows;
    }
    
    
    
    
}