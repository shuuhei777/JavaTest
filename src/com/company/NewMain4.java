/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.sql.Connection;
import java.sql.Statement;
import utils.DbUtils;
import utils.Utils;

/**
 *
 * @author shuhei
 */
public class NewMain4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Utils utils = new Utils();
        DbUtils db = new DbUtils();
        Connection con = db.conOracle();
        
        long start, end;
        
        start = System.currentTimeMillis();
        
        String sql = "INSERT INTO TBL01(COL1,COL2) VALUES(1,2)";
        
        try{
            Statement stmt = con.createStatement();
            //stmt.executeUpdate(sql);
            for(int j=0;j<50000;j++){
            for(int i=0;i<10;i++){
                //sql = "INSERT INTO T_Sample(COL1,COL2) VALUES('AAA','BBB')" ;
                sql = "INSERT INTO TBL01(COL01,COL02) VALUES('AAA','BBB')" ;
                //sql = "INSERT INTO T_Sample(COL1,COL2) VALUES(" + i + "," + utils.Rand(10000) + ")" ; 
                stmt.executeUpdate(sql);
                //System.out.println(utils.Rand(10000));
                con.commit();
            }
                System.out.println(j);
            }
            
            con.commit();
            
            end = System.currentTimeMillis();
            
            System.out.println((end - start)/1000 +"[sec]");
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
}
