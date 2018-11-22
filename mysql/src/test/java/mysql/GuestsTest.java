package mysql;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.GuestsDao;
import daoimpl.GuestsDaoImpl;
import entity.Guests;
import util.DateUtils;

public class GuestsTest {
	
	GuestsDao dao = new GuestsDaoImpl();
	@Test
	public void insert() {
		Guests guest = new Guests();
		guest.setFirstname("三丰");
		guest.setLastname("张");
		guest.setEmail("zhang@123.com");
		guest.setReg_date(DateUtils.utilDateToTimestamp(new Date()));
		dao.insertGuests(guest);

	}
	@Test
	public void update() {
		Guests guest = new Guests();
		guest.setLastname("独孤");
		guest.setId(4);
		dao.updateGuestsName(guest);

	}
	@Test
	public void delete() {
		
		dao.deleteGuests(4);
	}
	
	@Test
	public void selectAll() {
		List<Guests> list=dao.selectAllGuests();
		for (int i = 0; i < list.size(); i++) {
			Guests guest=list.get(i);
			System.out.println(guest.toString());
		}
	}
	
	@Test
	public void queryAll() {
		List<Guests> list=dao.queryAllGuests();
		for (int i = 0; i < list.size(); i++) {
			Guests guest=list.get(i);
			System.out.println(guest.toString());
		}
	}
	
}
