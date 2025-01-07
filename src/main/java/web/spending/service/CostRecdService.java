package web.spending.service;

import java.util.List;

import web.spending.vo.CostRecd;

public interface CostRecdService {
	
	List<CostRecd> SpendingfindAll() throws Exception;
	CostRecd SpendingfindOne(Integer costno) throws Exception;
	String addList(CostRecd costRecd);
	String save(CostRecd costRecd);
 
}
