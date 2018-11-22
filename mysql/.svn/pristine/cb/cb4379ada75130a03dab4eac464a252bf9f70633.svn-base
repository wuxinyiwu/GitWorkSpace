package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import exception.MyException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DBCPUtils {
	private DBCPUtils(){
		throw new IllegalStateException("Utility class");
	}
	/**
	 * 
	 * 获得数据源
	 * @return
	 * 
	 */ 
	static{
		PropertiesUtil.setProperties("dbcp.properties");
	}
	private static Properties props = PropertiesUtil.getProperties();
      /**
       * 
       * @return
       * @throws MyException
       */
	public static DataSource getDataSource() throws MyException  {
		try {
			return BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			throw new MyException("获得数据源失败");
		}

	}

	/**
     * 获取连接
     *
     * @return
	 * @throws MyException 
     */

    public static Connection getConnection() throws MyException {
           try {
                  return getDataSource().getConnection();
           } catch (SQLException e) {
                  throw new MyException("连接数据库失败");
           }

    }

	/**
     * 关闭连接
     *
     * @param conn
     * @param prep
     * @param rs
     */

    public static void close(Connection conn, Statement prep,ResultSet rs) {
           if (rs != null) {
                  try {
                         rs.close();
                  } catch (SQLException e) {
                        log.error("rs错误",e);
                  } 

           }
           if (prep != null) {
                  try {
                         prep.close();
                  } catch (SQLException e) {
                	  log.error("prep错误",e);
                  } 
           }
           if (conn != null) {
                  try {
                         conn.close();
                  } catch (SQLException e) {
                	  log.error("coon错误",e);
                  } 

           }

    }


}
