package web.sched.dao;

import java.io.InputStream;
import java.util.List;

import web.sched.vo.Sched;

public interface SchedDao {
	
	List<Sched> selectAll();

	List<Sched> selectByContry(String contry);
	
	int insert(Sched sched);

	Sched selectById(Integer Id);

	int update(Sched sched);
	
	int deleteById(int id);
	
	List<Sched> selectByMemId(int id);
	
	int updateImage(String schIdStr, InputStream imageStream);
	
	List<Sched> selectFromCrewByMemId(int id);
}