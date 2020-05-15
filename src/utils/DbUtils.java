/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
/**
 *
 * @author s-nakayama
 */
public class DbUtils {
    public Connection conSqlSvr(){
        Connection con = null;
        try{
            Utils util = new Utils();
            
            String dbServer = util.getPropertiesValue("/resources/Config.properties", "dbServer");
            String dbName = util.getPropertiesValue("/resources/Config.properties", "dbName");
            String dbUser = util.getPropertiesValue("/resources/Config.properties", "dbUser");
            String dbPass = util.getPropertiesValue("/resources/Config.properties", "dbPass");
            System.out.println("DB_NAME：" + util.getPropertiesValue("/resources/Config.properties", "dbName"));
            Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String connUrl = "jdbc:sqlserver://" + dbServer + ";database=" + dbName + ";" + "integratedSecurity=false;user=" + dbUser + ";password=" + dbPass;
            con = d.connect(connUrl, new Properties());
            
            con.setAutoCommit(false);
        
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            return con;
        }
    }
    public Connection conSqlSvr2(){
        Connection con = null;
        try{
            Utils util = new Utils();
            
            String dbServer = util.getPropertiesValue("/resources/Config.properties", "dbServer");
            String dbName = util.getPropertiesValue("/resources/Config.properties", "dbName");
            String dbUser = util.getPropertiesValue("/resources/Config.properties", "dbUser");
            String dbPass = util.getPropertiesValue("/resources/Config.properties", "dbPass");
            System.out.println("DB_NAME：" + util.getPropertiesValue("/resources/Config.properties", "dbName"));
            Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String connUrl = "jdbc:sqlserver://" + dbServer + ";database=" + dbName + ";" + "integratedSecurity=false;user=" + dbUser + ";password=" + dbPass;
            con = d.connect(connUrl, new Properties());
            
            con.setAutoCommit(true);
        
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            return con;
        }
    }
    public Connection conOracle(){
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //String connUrl = "jdbc:oracle:thin:@EXPJ:1521:oracle";
            //con = d.connect(connUrl, new Properties());
            //con = DriverManager.getConnection("jdbc:oracle:thin:@svr-exp02:1521:EXPJ", "EXPJ","EXPJ");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@CAD08:1521:ORCL", "ADMIN","ADMIN");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "C##ADMIN","ADMIN");
            //con = DriverManager.getConnection("jdbc:oracle:thin@192.168.28.128:1521:ORCL2", "C##ADMIN","ADMIN");
            
            con.setAutoCommit(false);
        
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            return con;
        }
    }
    
    public int MaxColNumber(String Tbl, String Col){
        int retNumber = 0;
        
        String sql = "select COUNT(" + Col + ")  From " + Tbl;
        
        
        
        try{
            Connection con = conOracle();
            PreparedStatement ps = con.prepareStatement(sql);
            
            
            
            ResultSet rs = ps.executeQuery();
            
            boolean isExists = rs.next();
            
            if(rs.getInt(1) == 0){
                retNumber = 0;
            } else{
                
                sql = "select Max(" + Col + ") AS MAXCOL From " + Tbl;
                
                ps = con.prepareStatement(sql);
                
                rs = ps.executeQuery();
            
                rs.next();
   
                retNumber = rs.getInt("MAXCOL") + 1;
            
            }
            
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
        return retNumber;
    }
    
    public int ColCnt(String Tbl, String Col){
        int retNumber = 0;
        
        final String sql = "select COUNT(" + Col + ")  From " + Tbl;
        
        try{
            Connection con = conOracle();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            boolean isExists = rs.next();
            
            
            
            retNumber = rs.getInt(1);
            
            con.close();
        
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return retNumber;
    }
    
}
