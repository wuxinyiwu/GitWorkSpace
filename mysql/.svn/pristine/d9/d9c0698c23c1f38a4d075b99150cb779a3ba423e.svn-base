package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import entity.Guests;
import util.C3P0DBUtils;
import util.DateUtils;


public class DBUtilTest {
	QueryRunner qr = new QueryRunner(C3P0DBUtils.getDataSource());
	@Test
	public void test1(){
		String sql = "insert into myguests values(null,?,?,?,?)";
		try {
			int update = qr.update(sql,"狗蛋","王","wang@123",DateUtils.getTimestampNow());
			if(update>0){
				System.out.println("插入成功"+update);
			}else{
				System.out.println("插入失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		String sql = "update myguests set firstname=? where id =?";
		try {
			int update = qr.update(sql,"哪吒",10);
			if(update>0){
				System.out.println("修改成功"+update);
			}else{
				System.out.println("修改失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test3(){
		String sql = "delete from myguests  where id =?";
		try {
			int update = qr.update(sql,9);
			if(update>0){
				System.out.println("删除成功"+update);
			}else{
				System.out.println("删除失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试BeanHandler
	 */
	@Test
	public void test4(){
		String sql = "select * from myguests";
		try {
			 Guests guest = qr.query(sql, new BeanHandler<Guests>(Guests.class));
			if(guest!=null){
				System.out.println("获取成功"+guest.getFirstname());
			}else{
				System.out.println("获取失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试BeanListHandler
	 */
	@Test
	public void test5(){
		String sql = "select * from myguests";
		try {
			 List<Guests> list = qr.query(sql, new BeanListHandler<Guests>(Guests.class));
			if(list!=null){
				System.out.println("获取成功"+list.size());
			}else{
				System.out.println("获取失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 需求：测试ScalarHandler策略
	 * ScalarHandler:封装类似count、avg、max、min、sum。。。。函数的执行结果
	 */
	@Test
	public void test6(){
		String sql = "select count(*) from myguests";
		try {
			Object object= qr.query(sql, new ScalarHandler());
			if(object!=null){
				System.out.println("获取成功"+object);
			}else{
				System.out.println("获取失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 自定义结果集封装
	 */
	@Test
	public void test7(){
		String sql = "select * from myguests";
		try {
			List<Guests> object= qr.query(sql,new ResultSetHandler<List<Guests>>(){

				@Override
				public List<Guests> handle(ResultSet rs) throws SQLException {
					 List<Guests> guests = new ArrayList<Guests>();
		                
                     while (rs.next()) {
                    	 Guests guest = new Guests();
                         guest.setId(rs.getInt("id"));
                         guest.setFirstname(rs.getString("firstname"));
                         guest.setLastname(rs.getString("lastname"));
                         guest.setEmail(rs.getString("email"));
                         guest.setReg_date(rs.getTimestamp("reg_date"));
                         guests.add(guest);
                     }
                     return guests;
				}
				
			}
			);
			if(object!=null){
				for (int i = 0; i < object.size(); i++) {
					Guests guest=object.get(i);
					System.out.println(guest.getFirstname()+"\t"+guest.getEmail());
				}
			
			}else{
				System.out.println("获取失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
