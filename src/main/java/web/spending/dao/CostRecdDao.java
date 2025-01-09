package web.spending.dao;

import java.sql.SQLException;
import java.util.List;

import web.spending.vo.CostRecd;
import web.spending.vo.Crew;

public interface CostRecdDao  {
	
	List<CostRecd> findDataAll() ;
	List<Crew> findcrew(Integer schNo) throws Exception; 
//	CostRecd costRecd(Integer costNo) ;
	Integer insert(CostRecd costRecd);
	Integer update(CostRecd csostRecd);
	Integer delete(Integer costNo);
	Integer crewCount(Integer schNo);
	CostRecd findDataOne(Integer costno) throws Exception; 
	
}
