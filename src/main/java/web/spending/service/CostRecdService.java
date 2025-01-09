package web.spending.service;

import java.util.List;

import web.spending.vo.CostRecd;
import web.spending.vo.Crew;

public interface CostRecdService {
	
	List<CostRecd> SpendingfindAll() throws Exception;
	List<Crew> FindTripCrew(Integer schNo) throws Exception;
	CostRecd SpendingfindOne(Integer costno) throws Exception;
	String addList(CostRecd costRecd);
	
//	Integer saveList(Integer schNo, String crCurRecord, Double costPrice, Integer paidBy,Byte costType,String costItem,Boolean costPex,String crCostTime );
	//因為這些東西 CostRecd 裡面都有拉！
	String saveList(CostRecd costRecd);
	Boolean deleteList(Integer costNo);
	Integer crewNum(Integer schNo);

 
}
