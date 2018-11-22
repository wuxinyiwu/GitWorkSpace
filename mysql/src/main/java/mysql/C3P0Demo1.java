package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import util.C3P0DBUtils;

public class C3P0Demo1 {
	static Connection con=null;
	 static PreparedStatement sql=null;
	 static ResultSet rs=null;
	public static void main(String[] args) {
		try {
             con = C3P0DBUtils.getConnection(); // 声明Connection对象
             sql = con.prepareStatement("select * from myguests"); // 声明PreparedStatement对象
             rs = sql.executeQuery(); // 声明ResultSet对象
            while(rs.next()) {
            	System.out.println(
    					rs.getString(1) + "\t" 
    					+ rs.getString(2) + "\t" 
    					+ rs.getString(3) + "\t" 
    				    + rs.getString(4) + "\t" 
    					+ rs.getString(5));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	try {
				rs.close();
				sql.close();
		        con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
           
		}


	}
	

}
