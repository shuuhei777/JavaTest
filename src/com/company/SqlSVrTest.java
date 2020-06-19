package com.company;

import java.sql.Connection;
import java.sql.Statement;
import utils.DbUtils;
import utils.Utils;

public class SqlSVrTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Utils utils = new Utils();
        DbUtils db = new DbUtils();
        Connection con = db.conSqlSvr();

        long start, end;

        start = System.currentTimeMillis();

        String sql = "INSERT INTO TBL01(COL1,COL2) VALUES(1,2)";

        try{
            Statement stmt = con.createStatement();
            //stmt.executeUpdate(sql);
            for(int j=0;j<300;j++){
                for(int i=0;i<10;i++){
                    //sql = "INSERT INTO T_Sample(COL1,COL2) VALUES('AAA','BBB')" ;
                    sql = "INSERT INTO TBL01(COL1,COL2) VALUES('AAA','BBB')" ;
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
