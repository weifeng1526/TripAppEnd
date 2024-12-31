package web.sched.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import web.sched.vo.Sched;

public class SchedTest {
	public static void main(String[] args) throws IOException {
		//實例化SchedDaoImpl
		SchedDaoImpl schedDaoImpl = new SchedDaoImpl();
		//SELECT ALL
		List<Sched> schedList = new ArrayList<>();
		schedList = schedDaoImpl.selectAll();
		System.out.println(schedList.size() + 1);
		//Insert 
		//測試: int = 0, String = "測試欄位", Date = 2000-01-01, Byte[] = null, FK = PK
		Sched sched = new Sched(); 
		//自動編號不用set
		sched.setMemNo(1);
		sched.setSchState(0);
		sched.setSchName("測試名稱");
		sched.setSchCon("測試國家");
		sched.setSchStart("2000-01-01");
		sched.setSchEnd("2000-01-01");
		sched.setSchCur("測試幣別");
		sched.setSchPic(null);
		sched.setSchLastEdit("2020-01-01T11:00:00");
		int isUpdated = schedDaoImpl.insert(sched);
		System.out.println(isUpdated);
		//SELECT BY NO
		sched = schedDaoImpl.selectById(1);
		System.out.println(sched.getSchNo());
//		//UPDATE
		sched.setSchState(2);
		//sched.setSchName("SDASSSS");
//		sched.setSchStart(Date.valueOf("1999-01-01"));
//		sched.setSchEnd(Date.valueOf("1999-02-02"));
		schedDaoImpl.update(sched);
		
	}
}
