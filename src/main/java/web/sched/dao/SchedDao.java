package web.sched.dao;

import java.io.InputStream;
import java.util.List;

import web.sched.vo.Sched;
import web.sched.vo.SchedInfo;

public interface SchedDao {
	
	List<Sched> selectAll();

	Sched selectById(Integer Id);

	int update(Sched sched);
	
	int deleteById(int id);
	
	List<Sched> selectByMemId(int id);
	
	int updateImage(Integer schId, InputStream imageStream);
	
	/* ------------------------------------------------------ */
	List<Sched> selectFromCrewByMemId(int id);
	
	List<SchedInfo> selectSchedInfo(Integer memId);
	
	List<SchedInfo> selectSchedInfo(Integer memId, Integer limit, Integer offset);
	
	SchedInfo insertSchedInfo(SchedInfo schedInfo);
}