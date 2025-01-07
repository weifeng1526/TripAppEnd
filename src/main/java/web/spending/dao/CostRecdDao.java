package web.spending.dao;

import java.sql.SQLException;
import java.util.List;

import web.spending.vo.CostRecd;

public interface CostRecdDao  {
	
	List<CostRecd> findDataAll() ;
//	CostRecd costRecd(Integer costNo) ;
	int insert(CostRecd costRecd);
	int update(CostRecd csostRecd);
	CostRecd findDataOne(Integer costno) throws Exception; 
}
