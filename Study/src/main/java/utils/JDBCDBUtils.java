package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDBUtils {
	private JDBCDBUtils(){
		throw new  IllegalStateException("Utility class");
	}
		static{
			PropertiesUtil.setProperties("mysql.properties");
		}
	// 数据库用户名
		
		private static final String USERNAME = PropertiesUtil.getString("username");
		// 数据库密码
		private static final String PASSWORD = PropertiesUtil.getString("password");
		// 数据库地址
		private static final String URL = PropertiesUtil.getString("url");
	
		private static Connection con;
		public static Connection getConnect() throws SQLException{
			try {
				//jdbc现在可以不显示的加载驱动，自动加载
				Class.forName(PropertiesUtil.getString("driver"));
				con=  DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	
		public static void close(Connection con,Statement state,ResultSet rs){
			try {
				if(rs!=null){
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("result关闭失败");
			}
			
			try {
				if(state!=null){
				state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("statement关闭失败");
			}
			try {
				if(con!=null){
				con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connect关闭失败");
			}
			
		}
}
