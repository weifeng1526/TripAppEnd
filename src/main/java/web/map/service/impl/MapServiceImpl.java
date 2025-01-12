package web.map.service.impl;

import javax.naming.NamingException;

import web.map.dao.MapDao;
import web.map.dao.impl.MapDaoImpl;
import web.map.service.MapService;

import web.map.vo.Map;

public class MapServiceImpl implements MapService {
	private MapDao mapDao;

	public MapServiceImpl() throws NamingException {
		mapDao = new MapDaoImpl();
	}

	@Override
	public Map placeinfoaddcheck(Map map, String address,int addPlanNumber,String planDate, String planStart, String planEnd, String planInr) {
		if (!mapDao.checkPlace(address)) {
			int addPlace=mapDao.insert(map);
			
			
			if (addPlace>0 && addPlanNumber>0) {
				mapDao.inseartPlan(mapDao.search(address),addPlanNumber,planDate,planStart,planEnd,planInr);
			}
			return mapDao.search(address);
		} else {
			if (addPlanNumber>0) {
				
				mapDao.inseartPlan(mapDao.search(address),addPlanNumber,planDate,planStart,planEnd,planInr);
			}
			
		}
			return mapDao.search(address);
		}
			
		

	}

