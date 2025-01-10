package web.sched.dao;

import java.util.List;

import web.sched.vo.Crew;

public interface CrewDao {
	int insert(Crew crew);
	Crew selectByMemId(Integer id);
	List<Crew> selectBySchId(Integer id);
	int deleteByMemId(Integer id);
}

