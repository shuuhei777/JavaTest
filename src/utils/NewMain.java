/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author macbook
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Utils util = new Utils();
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conOracle();
        
        try{
        
        Statement stmt = con.createStatement();
        
        String str = "1977-12-07";
        String sql = "INSERT INTO TEST(COL1) VALUES('AAA')";
        
        for(int i=0;i<100;i++)
        stmt.executeUpdate(sql);
        
        con.commit();
        
        System.out.println(util.addDate(str, 15));
        System.out.println(util.addMonth(str, 15));
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
