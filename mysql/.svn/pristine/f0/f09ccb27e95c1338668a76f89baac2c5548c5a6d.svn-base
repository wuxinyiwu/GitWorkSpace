package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class C3P0DBUtils {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static CallableStatement callableStatement = null;
	private static ResultSet rs = null;
	private C3P0DBUtils() {
	    throw new IllegalStateException("Utility class");
	  }
	private static DataSource dataSource = new ComboPooledDataSource("mvcApp");
	
	/**
	 * 获取连接
	 *
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 释放连接
	 * 
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			log.error("释放连接失败",e);
		} 
	}

	public static void close() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("result关闭失败");
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("statement关闭失败");
		} finally {
			ps = null;
		}

		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				callableStatement = null;
			}
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connect关闭失败");
		} finally {
			con = null;
		}
	}

	// 实现增删改
	public static int update(String sql, Object[] objects) throws SQLException {
		int num = 0;
		try {
			 con=getConnection();
			ps = con.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject((i + 1), objects[i]);
				}
			}
			num = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0DBUtils.close();
		}
		return num;
	}

	// 查询
	public static ResultSet getResultSet(String sql, Object[] objects) {
		ps = null;
		try {
			con=getConnection();
			ps = con.prepareStatement(sql);
			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject((i + 1), objects[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * SQL 查询将查询结果：第一行第一列
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数数组，若没有参数则为null
	 * @return 结果集
	 */
	public static Object executeQuerySingle(String sql, Object[] params) {
		Object object = null;
		try {
			con=getConnection();
			// 调用SQL
			ps = con.prepareStatement(sql);
			// 参数赋值
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行
			rs = ps.executeQuery();
			if (rs.next()) {
				object = rs.getObject(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return object;
	}

	/**
	 * 获取结果集，并将结果放在List中
	 * 
	 * @param sql
	 *            SQL语句 params 参数，没有则为null
	 * @return List 结果集
	 * 
	 */

	public static List<Object> excuteQuery(String sql, Object[] params) {
		// 执行SQL获得结果集
		ResultSet rs = getResultSet(sql, params);
		// 创建ResultSetMetaData对象
		ResultSetMetaData rsmd = null;
		List<Object> list =null;
		// 结果集列数
		int columnCount = 0;
		try {
			rsmd = rs.getMetaData();
			// 获得结果集列数
			columnCount = rsmd.getColumnCount();
			// 创建List
			 list = new ArrayList<Object>();
		
				// 将ResultSet的结果保存到List中
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						map.put(rsmd.getColumnLabel(i), rs.getObject(i));
					}
					list.add(map);
					// 每一个map代表一条记录，把所有记录存在list中
				}
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		} finally {
			// 关闭所有资源
			close();
		}
		
		return list;
	}

	/**
	 * 存储过程带有一个输出参数的方法
	 * 
	 * @param sql
	 *            存储过程语句
	 * @param params
	 *            参数数组
	 * @param outParamPos
	 *            输出参数位置
	 * @param SqlType
	 *            输出参数类型
	 * @return 输出参数的值
	 * @throws SQLException
	 */
	public static Object excuteQuery(String sql, Object[] params, int outParamPos, int SqlType) throws SQLException {
		Object object = null;
		con = getConnection();
		try {
			// 调用存储过程
			// prepareCall:创建一个 CallableStatement 对象来调用数据库存储过程。
			callableStatement = con.prepareCall(sql);
			// 给参数赋值
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					callableStatement.setObject(i + 1, params[i]);
				}
			}
			// 注册输出参数
			callableStatement.registerOutParameter(outParamPos, SqlType);
			// 执行
			callableStatement.execute();
			// 得到输出参数
			object = callableStatement.getObject(outParamPos);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// 释放资源
			close();
		}
		return object;

	}

}
