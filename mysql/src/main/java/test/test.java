package test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class test {
	public static void main(String[] args) {
		java.util.Date utilDate=new java.util.Date();
		         System.out.println("util的date:"+utilDate);
		         
		         java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		         System.out.println("sql的date:"+sqlDate);
		         
		         java.util.Date sTOu=new java.util.Date(sqlDate.getTime());
		         System.out.println("sqlDate转化为UtilDate"+sTOu);
		        
		        
		         Timestamp ts=new Timestamp(utilDate.getTime());
		         System.out.println("Timestamp:"+ts);
		         
		         java.util.Date tTOu=new java.util.Date(ts.getTime());
		         System.out.println("tTOu:"+tTOu);
		         
		         java.sql.Date sqlDate1=java.sql.Date.valueOf("2014-12-10");
		         System.out.println(sqlDate1);
		         
		         SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		         SimpleDateFormat f1=new SimpleDateFormat("yyyy");
		         System.out.println("format后的数据:"+f.format(tTOu));
		         System.out.println("format后的数据:"+f1.format(tTOu));

	}
}
