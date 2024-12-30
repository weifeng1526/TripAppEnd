package web.sched.dao;

import java.util.List;

import web.sched.vo.Sched;

public interface SchedDao {
	
	List<Sched> selectAll();

	int insert(Sched sched);

	Sched selectById(Integer Id);

	int update(Sched sched);
	
	int deleteById(int id);
}
