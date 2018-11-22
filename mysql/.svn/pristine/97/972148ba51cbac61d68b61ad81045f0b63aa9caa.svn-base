package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import dao.GuestsDao;
import entity.Guests;
import lombok.extern.slf4j.Slf4j;
import util.C3P0DBUtils;
import util.DateUtils;
@Slf4j
public class GuestsDaoImpl implements GuestsDao{

	@Override
	public int insertGuests(Guests guest) {
		 String sql="insert into myguests values(null,?,?,?,?)";
		 Object[] objects ={guest.getFirstname(),guest.getLastname(),guest.getEmail(),guest.getReg_date()}; 
		 int num = 0;
		try {
			num = C3P0DBUtils.update(sql, objects);
		} catch (SQLException e) {
			log.error("插入错误",e);
		} 
		 return num;
	}

	@Override
	public int updateGuestsName(Guests guest) {
		String sql="update myguests set lastname=? where id=?";
		Object[] objects={guest.getLastname(),guest.getId()}; 
		int num = 0;
		try {
			num = C3P0DBUtils.update(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	    return num;


	}

	@Override
	public int deleteGuests(int id) {
		String sql="delete from myguests where id=?";
		Object[] objects={id}; 
		int num = 0;
		try {
			num = C3P0DBUtils.update(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	    return num;
	}

	@Override
	public List<Guests> selectAllGuests() {
		 String sql="select * from myguests"; 
		 List<Guests> list=null; 
		 try { 
			 ResultSet rs=C3P0DBUtils.getResultSet(sql, null); 
			 list =new ArrayList<>(); 
			 while (rs.next()) { 
				 Guests guest =new Guests(); 
				 int id=rs.getInt("id"); 
				 String firstname=rs.getString("firstname"); 
				 String lastname=rs.getString("lastname"); 
				 String email=rs.getString("email"); 
				 Timestamp time=rs.getTimestamp("reg_date");
				 guest.setId(id); 
				 guest.setFirstname(firstname);
				 guest.setLastname(lastname);
				 guest.setEmail(email); 
				 guest.setReg_date(time);
				 list.add(guest); 
				 } 
			 } catch (SQLException e) { 
				 e.printStackTrace();
				 } 
		 return list;
		 }

	@Override
	public List<Guests> queryAllGuests() {
		String sql="select * from myguests"; 
		List<Object> lobj=C3P0DBUtils.excuteQuery(sql,null);
		List<Guests> list=new ArrayList<>();
		for (int i = 0; i < lobj.size(); i++) {
			Guests guest = new Guests();
			@SuppressWarnings("unchecked")
			Map<String, Object> map=(Map<String, Object>) lobj.get(i);
			guest.setEmail(map.get("email").toString());
			guest.setFirstname(map.get("firstname").toString());
			guest.setLastname(map.get("lastname").toString());
			guest.setReg_date(DateUtils.utilDateToTimestamp(DateUtils.strToDate(map.get("reg_date").toString())));
			guest.setId(Integer.valueOf(map.get("id").toString()));
			list.add(guest);
		}
		return list;
		
	}

	

}
