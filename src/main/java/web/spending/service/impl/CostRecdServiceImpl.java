package web.spending.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import web.spending.dao.CostRecdDao;
import web.spending.dao.impl.CostRecdDaoImpl;
import web.spending.service.CostRecdService;
import web.spending.vo.CostRecd;

public class CostRecdServiceImpl implements CostRecdService {
	private CostRecdDao costRecdDao;

	public CostRecdServiceImpl() throws NamingException {
		costRecdDao = new CostRecdDaoImpl();
	}

	@Override
	public List<CostRecd> SpendingfindAll() throws Exception {
		return costRecdDao.findDataAll();

	}
	
	@Override
	public CostRecd SpendingfindOne(Integer costno) throws Exception {
		
		return costRecdDao.findDataOne(costno);
	}


	
	

	@Override
	public String addList(CostRecd costRecd) {
		Byte CostType = costRecd.getCostType();
		if (CostType == null) {
			return "別懶惰，一定要選類別喔！";
		}

		int count = costRecdDao.insert(costRecd);
		if (count < 0) {
			return "新增失敗，請再試一次！";
		} else {
			return null;
		}

	}

	@Override
	public String save(CostRecd costRecd) {
		Byte CostType = costRecd.getCostType();
		if (CostType == null) {
			return "別懶惰，一定要選類別喔！";
		}

		int count = costRecdDao.insert(costRecd);
		if (count < 0) {
			return "修改失敗，請再試一次！";
		} else {
			return null;
		}
	}

	


}
