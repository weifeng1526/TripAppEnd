package web.spending.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import web.spending.dao.CostRecdDao;
import web.spending.dao.impl.CostRecdDaoImpl;
import web.spending.service.CostRecdService;
import web.spending.vo.CostRecd;
import web.spending.vo.Crew;

public class CostRecdServiceImpl implements CostRecdService {
	private CostRecdDao costRecdDao;

	public CostRecdServiceImpl() throws NamingException {
		costRecdDao = new CostRecdDaoImpl();
	}

	@Override
	public List<CostRecd> SpendingfindAll(Integer memNo) throws Exception {
		System.out.println("service--"+memNo);
		return costRecdDao.findDataAll(memNo);

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
			return "新增失敗，再檢查一下喔！";
		} else {
			return null;
		}

	}
//
//	@Override
//	public String save(CostRecd costRecd) {
//		Byte CostType = costRecd.getCostType();
//		if (CostType == null) {
//			return "別懶惰，一定要選類別喔！";
//		}
//
//		int count = costRecdDao.insert(costRecd);
//		if (count < 0) {
//			return "修改失敗，請再試一次！";
//		} else {
//			return null;
//		}
//	}

	@Override
	public String saveList(CostRecd costRecd) {
		Byte CostType = costRecd.getCostType();
		if (CostType == null) {
			return "別懶惰，一定要選類別喔！";
		}

		if (costRecdDao.update(costRecd) < 0) {
			return "修改失敗，再檢查一下喔！";
		} else {
			return null;
		}

	}

	@Override
	public Boolean deleteList(Integer costNo) {
		
//		if ( costRecdDao.delete(costNo)<0) {
//			return false;
//		}else {
//			return true;
//		}
		
		return costRecdDao.delete(costNo) > 0 ; 
		
	}

	@Override
	public Integer crewNum(Integer schNo) {
		System.out.println("count--Service--" + costRecdDao.crewCount(schNo));
		return costRecdDao.crewCount(schNo)  ; 
		
	}

	@Override
	public List<Crew> FindTripCrew(Integer schNo) throws Exception {
		return costRecdDao.findcrew(schNo);
	}

	@Override
	public List<Crew> FindTripName(Integer memNo) throws Exception {
		return costRecdDao.findTripName(memNo);
	}
	@Override
	public List<Crew> findTripCur(Integer schNo) throws Exception {
		return costRecdDao.findTripCur(schNo);
	}


}
