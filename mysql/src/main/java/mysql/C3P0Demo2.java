package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.C3P0DBUtils;
import util.DateUtils;

public class C3P0Demo2 {
	 private Connection con=null;
	 private PreparedStatement sql = null;
	 private ResultSet rs = null;

	{
		try {
			con=C3P0DBUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		C3P0Demo2 c3p0=new C3P0Demo2();
		c3p0.query1();
	}
	
	
	
	public void query1() throws SQLException{
		sql = con.prepareStatement("select * from myguests"); 
		 rs = sql.executeQuery(); // 声明ResultSet对象
         while(rs.next()) {
         	System.out.println(
 					rs.getString(1) + "\t" 
 					+ rs.getString(2) + "\t" 
 					+ rs.getString(3) + "\t" 
 				    + rs.getString(4) + "\t" 
 					+ rs.getTimestamp(5));
         }
	}
	public void createDataBase() throws SQLException{
		sql = con.prepareStatement("create database javatest"); 
		sql.executeUpdate();
	}
	public void insert() throws SQLException{
		sql = con.prepareStatement("insert into myguests values(null,?,?,?,?) "); 
		sql.setString(1, "Jak");
		sql.setString(2, "Jake");
		sql.setString(3, "jake@123");
		sql.setTimestamp(4, DateUtils.utilDateToTimestamp(new java.util.Date()));
		sql.executeUpdate();
	}
}
