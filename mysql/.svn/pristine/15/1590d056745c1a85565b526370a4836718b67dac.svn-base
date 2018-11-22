package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import util.C3P0DBUtils;

public class ResultSetTest {
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	{
		try {
			con = C3P0DBUtils.getConnection();
			 pst=con.prepareStatement("select id as did,firstname,lastname,email,reg_date from myguests");
			 rs=pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * 滚动结果集
	 * @throws SQLException
	 */
	public void test1() throws SQLException{

		//开始时当前行为0
		System.out.println(rs.getRow());
		rs.next();
		//向下移动一条记录，即第一条记录
		System.out.println(rs.getString(2));
		//继续向下移动一条记录，即第二条记录
		rs.next();
		System.out.println(rs.getString(2));
		//跳到第一条记录
		rs.first();
		System.out.println(rs.getString(2));
		//跳到最后一条记录
		rs.last();
		System.out.println(rs.getString(2));
		//获取最后一条记录所在行
		System.out.println(rs.getRow());
		rs.absolute(2);
		//获取绝对定位到2的记录值
		System.out.println(rs.getString(2));
		rs.relative(1);
		//向下相对移动2条记录
		System.out.println(rs.getString(2));
		
	}
	@Test
	/*
	 * 获取结果集元数据！
	 */
	public void test2() throws SQLException{
		ResultSetMetaData meta= rs.getMetaData();
		int num=meta.getColumnCount();
		System.out.println("数据库有"+num+"列");
		for (int i = 0; i < num; i++) {
			System.out.println(meta.getColumnName(i+1));
			System.out.println(meta.getColumnClassName(i+1));
			System.out.println(meta.getColumnTypeName(i+1));
			System.out.println(meta.getColumnLabel(i+1));
		}
	}



}
