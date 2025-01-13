package web.map.service;


import web.map.vo.Map;

public interface MapService {
	
Map placeinfoaddcheck(Map map,String address,int addPlanNumber,String planDate, String planStart, String planEnd, String planInr,byte[] planPic);

}
