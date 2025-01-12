package web.map.dao;

import web.map.vo.Map;



public interface MapDao {
	boolean checkPlace(String address);
	int insert(Map map);
	Map search(String address);
	int inseartPlan (Map map,int addPlanNumber,String planDate, String planStart, String planEnd, String planInr);

}
