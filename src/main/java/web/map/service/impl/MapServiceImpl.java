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
	public Map placeinfoaddcheck(Map map, String address) {
		if (!mapDao.checkPlace(address)) {
			mapDao.insert(map);
			return map;
		} else {
			return mapDao.search(address);
		}
	}

}
