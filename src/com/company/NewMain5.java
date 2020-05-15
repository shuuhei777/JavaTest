/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package JavaApplication8;

import java.sql.Connection;
import java.sql.Statement;
import utils.DbUtils;

/**
 *
 * @author shuhei
 */
public class NewMain5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DbUtils db = new DbUtils();
        Connection con = db.conOracle();
        Connection con2 = db.conOracle();
        
        //System.out.println(db.MaxColNumber("TBL02", "COL1"));
        
        
        long start, end;
        int cnt = 0;
        
        start = System.currentTimeMillis();
        
        String sql = "INSERT INTO TBL01(COL1,COL2) VALUES(1,2)";
        
        try{
            Statement stmt = con.createStatement();
            //stmt.executeUpdate(sql);
            for(int j=0;j<5000;j++){
            for(int i=0;i<10;i++){
                //sql = "INSERT INTO T_Sample(COL1,COL2) VALUES('AAA','BBB')" ;
                
                //cnt = db.ColCnt("TBL02", "COL1");
                
                //System.out.println("件数：" + j);
                
                /*
                if(cnt==0){
                    sql = "INSERT INTO TBL02(COL1,COL2) VALUES(0, 1)" ;
                } else{
                        */
                    //cnt = db.MaxColNumber("TBL02", "COL1");
                    //System.out.println("cnt:" + cnt);
                    sql = "INSERT INTO TBL02(COL1,COL2) VALUES(" + cnt + ",1)" ;
                    
                    //cnt = 1;
                    //sql = "INSERT INTO TBL02(COL1,COL2) VALUES(" + cnt + ",1)" ;
                //}
                //sql = "INSERT INTO T_Sample(COL1,COL2) VALUES(" + i + "," + utils.Rand(10000) + ")" ; 
                stmt.executeUpdate(sql);
                //System.out.println(utils.Rand(10000));
                //con.commit();
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
