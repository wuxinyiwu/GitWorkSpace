package mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCDBUtils;

public class JDBCDemo1 {
	private static Connection con;
	private static Statement state;
	private static ResultSet rs;
	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try{
		con = JDBCDBUtils.getConnect();
		state = con.createStatement();
		rs = state.executeQuery("select * from myguests");
		while (rs.next()) {
			System.out.println(
					rs.getString(1) + "\t" 
					+ rs.getString(2) + "\t" 
					+ rs.getString(3) + "\t" 
				    + rs.getString(4) + "\t" 
					+ rs.getString(5));
		}
		
		  System.out.println("返回表的所有列："); 
		  DatabaseMetaData metadata = con.getMetaData();
		  ResultSet rs2 = metadata.getColumns(null, null,"person", null); 
		  while (rs2.next()) {
		 System.out.println(rs2.getString(4)); }
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCDBUtils.close(con, state, rs);
		}
	}
	

}
