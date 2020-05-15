/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author s-nakayama
 */
public class Utils {
    /**************************
     *  
     *  boolToInt
     *      booleanをintに変換
     *          true    ⇒1
     *          false   ⇒0
     * @param bool
     * @return 
     *************************/
    public int boolToInt(boolean bool){
        if(bool){
            return 1;
        } else{
            return 0;
        }
    }
    /****************************
     *  nullToString
     * @param str
     * @return 
     ***************************/
    public String nullToString(String str){
        if(str==null){
            return "";
        } else{
            return str;
        }
    }
    public String brankToNull(String str){
        if(str.equals("")){
           return null; 
        } else{
            return str;
        }
    }
    public String brankToNullforDate(String str){
        if(str==null){
          return null;  
        } else if(str.equals("")){
           return null; 
        } else{
            return "'" + str + "'";
        }
    }
    public String brankToNullforMonthToDate(String str){
        if(str==null){
          return null;  
        } else if(str.equals("")){
           return null; 
        } else{
            return "'" + str + "-01" + "'";
        }
    }
    public String getToday(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    /*
        期間日数を算出する
    
    */
    public long calDays(String fromDate, String toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTo = null;
        Date dateFrom = null;

        // 日付を作成します。
        try {
            dateFrom = sdf.parse(fromDate);
            dateTo = sdf.parse(toDate);
    } catch (Exception e) {
        e.printStackTrace();
    }
 
    // 日付をlong値に変換します。
    long dateTimeTo = dateTo.getTime();
    long dateTimeFrom = dateFrom.getTime();
 
    // 差分の日数を算出します。
    long dayDiff = ( dateTimeTo - dateTimeFrom  ) / (1000 * 60 * 60 * 24 ) + 1;
    
    return dayDiff;
    }
    
    public boolean checkDate(String strDate) {
        if (strDate == null || strDate.length() != 10) {
            return true;
        }
        strDate = strDate.replace('-', '/');
        DateFormat format = DateFormat.getDateInstance();
        format.setLenient(false);
        try {
            format.parse(strDate);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    /**********************************************
     * 
     *  ファイル名を返す
     * 
     * @param filePath
     * @return 
     *********************************************/
    public String getFileNM(String filePath){
        
        if(filePath ==null || filePath.equals("")){ 
            return "";
        } else{
            return filePath.substring(filePath.lastIndexOf("\\")+1);
        }
    }
    /**********************************************
     * 
     *  計測機器フォルダからの相対パスを返す
     * 
     * @param filePath
     * @return 
     *********************************************/
    public String getKeisokuPath(String filePath){
        String returnStr = "";
        
        if(filePath ==null || filePath.equals("")){ 
            return "";
        } else{
            returnStr = "http://teh01/keisokuDoc/" + filePath.substring(filePath.indexOf("\\11_計測機器関連")+11);
            return returnStr.replace("\\", "/");
        }
    }
    /*----------------------------------------------
     * 
     * @param date
     * @return 
     *
     * 
     * 
     * 
     ---------------------------------------------*/
    public Date strToDate(String date){
        
        // YYYY/MM/DD形式に変更する
        date = date.replace("-", "/");
        
        return new Date(date);
    }
    public Date strToDate(Object date){

        
        String date2 = date.toString().replace("-", "/");
        
        
        //String dates = (String)date;
        
        return new Date(date2);
    }
    /*****************************************
     * 
     * 概要     日付を月に変更する
     * 
     * @param date      日付
     * @return            月
     * 
     ******************************************/
    public String dateToMonth(String date){
        if(date==null){
            return "";
        } else if(date.equals("")){
            return "";
        } else{
            return date.substring(0, 4) + "/" + date.substring(5,7);
        }
    }
    
    /****************************************************
     * 
     * 概要     対象日付に指定日数の日付を加算する
     * 
     * @param strDate   対象日付
     * @param days      指定日数
     * @return 
     ****************************************************/
    public String addDate(String strDate, int days){
        try{
            // DateFormat
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            // YYYY/MM/DD形式に変更
            strDate = strDate.replace("-", "/");
            Date date = new Date(strDate);
            Calendar cal = Calendar.getInstance();
            //Calendarクラスのインスタンスを生成
            cal.setTime(date);
            // 加算
            cal.add(Calendar.DATE, days);
            return df.format(cal.getTime());
        
         } catch(Exception e){
             
         }
         return "";
    }
    
    /**************************************************
     * 
     * 概要     対象日付に指定月数を加算する
     * 
     * @param strDate   対象日付
     * @param months      指定日数
     * @return 
     *************************************************/
    public String addMonth(String strDate, int months){
        try{
            // DateFormat
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            // YYYY/MM/DD形式に変更
            strDate = strDate.replace("-", "/");
            Date date = new Date(strDate);
            Calendar cal = Calendar.getInstance();
            //Calendarクラスのインスタンスを生成
            cal.setTime(date);
            // 加算
            cal.add(Calendar.MONTH, months);
            return df.format(cal.getTime());
        
         } catch(Exception e){
             
         }
         return "";
    }
    
    /*****************************************************
     * 
     * 数値チェック
     * double に変換でききない文字列が渡された場合は false を返します。
     * 
     * @param str チェック文字列
     * @return 引数の文字列が数値である場合 true を返す。
     ******************************************************/
    public boolean chkNumber(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch(NumberFormatException e) {
            return true;
        }
    }
    
    
    public String padInt(int num){
        return String.format("%1$03d", num);
    }
    /**********************************
     * 
     *  getBranchNumber
     * 
     *  対象テーブル、カラムの連番を採番
     * 
     * @param tblNM     テーブル名
     * @param colNM     カラム名
     * @return              連番
     * 
     **********************************/
    public String getBranchNumber(String tblNM, String colNM){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        int max = 0;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getString("MAX_CD")==null){
                max=0;
            } else{
                max = Integer.parseInt(rs.getString("MAX_CD"));
            }
            
            return padInt(max+1);
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    
   /***********************************************
     * 
     *  getBranchNumber
     * 
     *  対象テーブル、カラムの連番を採番
     * 
     * @param tblNM     テーブル名
     * @param colNM     カラム名
     * @return              連番
     * 
     **********************************************/
    public String getBranchNumber(Connection con, String tblNM, String colNM){
        DbUtils dbUtils = new DbUtils();
        //Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        int max = 0;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getString("MAX_CD")==null){
                max=0;
            } else{
                max = Integer.parseInt(rs.getString("MAX_CD"));
            }
            
            return padInt(max+1);
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    /******************************************
     * 
     * 対象テーブル、カラムの連番を採番(WHERE句あり)
     * 
     * @param tblNM         テーブル
     * @param colNM         カラム
     * @param where         WHERE条件
     * @return                  連番
     *****************************************/
    public String getBranchNumber(String tblNM, String colNM, String where){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM + " WHERE " + where;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            int max = Integer.parseInt(rs.getString("MAX_CD"));
            
            return padInt(max+1);
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    /******************************************
     * 
     * 対象テーブル、カラムの連番を採番
     * 
     * @param tblNM         テーブル
     * @param colNM         カラム
     * @return                  連番
     *****************************************/   
    public int getBranchNumberInt(String tblNM, String colNM){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        int max = 0;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getString("MAX_CD")==null){
                max=0;
            } else{
                max = Integer.parseInt(rs.getString("MAX_CD"));
            }
            
            return max+1;
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    /******************************************
     * 
     * 対象テーブル、カラムの連番を採番(WHERE句あり)
     * 
     * @param tblNM         テーブル
     * @param colNM         カラム
     * @param where         WHERE条件
     * @return                  連番
     *****************************************/
    public int getBranchNumberInt(String tblNM, String colNM, String where){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        int max = 0;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM + " WHERE " + where;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getString("MAX_CD")==null){
                max=0;
            } else{
                max = Integer.parseInt(rs.getString("MAX_CD"));
            }
            
            return max+1;
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    
/**********************************************
 * 
 *  最大値を取得
 * 
 * @param tblNM     テーブル
 * @param colNM     カラム
 * @return              最大値
 * 
 **********************************************/
    public int getMaxNumberInt(String tblNM, String colNM){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT MAX(" + colNM + ") AS MAX_CD FROM " + tblNM;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            return rs.getInt("MAX_CD");
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public String aaa(){
        return "aa";
    }
    public boolean chkExist(String tblNM, String where){
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT COUNT(*) AS CNT FROM " + tblNM + " WHERE " + where;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getInt("CNT") > 0){
                return true;
            } else{
                return false;
            }
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /*********************************************
    * 
    *  対象テーブルのデータ件数を取得する（抽出条件あり）
    * 
    * @param tblNM      対象テーブル
    * @param where      抽出条件
    * @return           データ件数
    * 
    *********************************************/
    public int getCNTWhere(String tblNM, String where){
        int returnInt=0;
        
        DbUtils dbUtils = new DbUtils();
        Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT COUNT(*) AS CNT FROM " + tblNM + " where " + where;
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            return rs.getInt("CNT");
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return returnInt;
    }
    /*********************************************
    * 
    *  対象テーブルのデータ件数を取得する（抽出条件あり、接続先指定）
    * 
    * @param con        対象DBへのコネクション
    * @param tblNM      対象テーブル
    * @param where      抽出条件
    * @return           データ件数
    * 
    *********************************************/
    public int getCNTWhereCon(Connection con,String tblNM, String where){
        int returnInt=0;
        
        DbUtils dbUtils = new DbUtils();
        //Connection con = dbUtils.conSqlSvr();
        String sql = "";
        
        ResultSet rs = null;
        
        try{
            Statement stmt = con.createStatement();
            
            sql = "SELECT COUNT(*) AS CNT FROM " + tblNM + " where " + where;
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            return rs.getInt("CNT");
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return returnInt;
    }

    /*********************************************
    * 
    *  プロパティファイルから設定を取得
    * 
    * @param path      プロパティファイルパス
    * @param id        取得するID
    * @return          設定値
    * 
    *********************************************/
    public String getPropertiesValue(String path, String id){
        String returnStr="";
        Properties prop = new Properties();
        InputStream inStream = null;
        try{
            Class c = getClass();
            inStream = c.getResourceAsStream(path);
            /*
            inStream = new BufferedInputStream(new FileInputStream(path));
            inStream = PropertiesSample2.class.getClassLoader()
                 .getResourceAsStream("sampleprop.properties");
                    */
            prop.load(inStream);
            returnStr = prop.getProperty(id);
        } catch(Exception e){
            e.printStackTrace();
        }
        return returnStr;
    }
    
    public String rightPad(String str, int size) {
        StringBuilder sb = new StringBuilder();
 
        sb.append(str);
 
    for (int i = str.length(); i < size; i++) {
        sb.append(" ");
    }
 
        return sb.toString();
    }
    /****************************************************
     * 
     * 名称：cmpPeriod
     * 機能：対象期間の過去・現在・未来
     * 
     * @param checkDate1
     * @param checkDate2
     * @return 過去 ： 1
     *               現在 ： 2
     *               未来 ： 3
     ****************************************************/
    public int cmpPeriod(String checkDate1, String checkDate2){
        
        try{
            
            // 開始日をDate型へ
            Date date1 = DateFormat.getDateInstance().parse(checkDate1.replace('-', '/'));

            // 終了日をDate型へ
            Date date2 = DateFormat.getDateInstance().parse(checkDate2.replace('-', '/'));
            
            Date today = DateFormat.getDateInstance().parse(getToday().replace('-', '/'));
            
            int diff1 = today.compareTo(date1);
            int diff2 = today.compareTo(date2);
            if(diff1 < 0){
                return 3;
            } else if(diff2 > 0){
                return 1;
            } else{
                return 2;
            }
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return 0; 
    }
    public String dateFormat(String str, int format){
        String year = str.substring(0, 4);
        String month = str.substring(5,7);
        String day = str.substring(8, 10);
        
        if(format==1){
            return year + "年" + month + "月" + day + "日";
        } else{
            return year + "年" + month + "月" + day + "日";
        }
        
    }
    
    public long getFldSize(String fldPath){
        long fldSize=0;
        
        // フォルダの存在チェック
        if(fldExists(fldPath)){
            File target = new File(fldPath);
            for (File member : target.listFiles()) {
                //System.out.println(calc(member) + "\t" + member.getName());
                fldSize += calc(member);
            }
            return fldSize;
        // 存在していなければ0を返す
        } else{
            return 0;
        }
    }
    
    public boolean fldExists(String fldPath){
        File file = new File(fldPath);
        if (file.exists()){
            return true;
        } else{
            return false;
        }
    }
    
    public static long calc(File f) {
        if (f.isDirectory()) {
            File[] children = f.listFiles();
            if (children == null) {
                System.err.println("Permission denied: " + f);
                return 0;
            }
            long sum = 0;
            for (File child : children) {
                System.out.println(child.getPath());
                sum += calc(child);
            }
            return sum;
        }
        return f.length();
    }
    
    public List getFileList(File f) {
        
        ArrayList<String> list = new ArrayList<String>();
        
        if (f.isDirectory()) {
            File[] children = f.listFiles();
            if (children == null) {
                System.err.println("Permission denied: " + f);
                return list;
            }
            long sum = 0;
            for (File child : children) {
                //System.out.println(child.getPath());
                sum += calc(child);
                list.add(child.getPath());
            }
            //return sum;
        }
        return list;
    }
    
    
    public int Rand(int num){
        // Randomクラスのインスタンス化
        Random rnd = new Random();
        
        return rnd.nextInt(num);
    }
    
    public double binomialDist(int numCorrect, int numTrials, double probValue){
        BigInteger ntF = factorial(numTrials);
	BigInteger denom = factorial(numCorrect).multiply(factorial(numTrials - numCorrect));
        
	BigDecimal ntFBD = new BigDecimal(ntF);
	BigDecimal denomBD = new BigDecimal(denom);
	BigDecimal quotient = ntFBD.divide(denomBD, 40, RoundingMode.HALF_UP);

	BigDecimal restBD = BigDecimal.valueOf(Math.pow(probValue, numCorrect) * Math.pow((1d - probValue), numTrials - numCorrect));
	
        return(quotient.multiply(restBD).doubleValue());
    }
    
    public BigInteger factorial(int n){
        BigInteger res = BigInteger.ONE;
        
        for (int i = n; i>1; i--){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return(res);
    }
    
    // slow
    public static double binomial1(int N, int k, double p){
        if(N==0 && k==0){
            return 1.0;
        }
        if(N < 0 || k < 0){
            return 0.0;
        }
        return (1.0-p)*binomial1(N-1, k, p) + p*binomial1(N-1, k-1, p);
    }
    
    // memorization
    public static double binomial2(int N, int k, double p){
        double[][] b = new double[N+1][k+1];
        
        // base cases
        for(int i=0;i<=N;i++){
            b[i][0] = Math.pow(1.0-p,i);
        }
        b[0][0] = 1.0;
        
        // recursive formula
        for(int i=1;i<=N;i++){
            for(int j=1;j<=k;j++){
                b[i][j] = p * b[i-1][j-1] + (1.0-p)*b[i-1][j];
            }
        }
        return b[N][k];
    } 
    
    
    public double[][] ddd(double[][] x, double[][] y){
        
        double[][] b = new double[10][20];
        
        return b;
    }
    
}
