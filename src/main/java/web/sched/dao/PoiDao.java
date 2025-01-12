package web.sched.dao;

import java.util.List;

import web.sched.vo.Poi;

public interface PoiDao {
	public List<Poi> selectAll();
}
