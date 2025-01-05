package web.spending.service;

import java.util.List;

import web.spending.vo.CostRecd;

public interface CostRecdService {
	
	List<CostRecd> findAll() throws Exception;
	String addList(CostRecd costRecd);
	String save(CostRecd costRecd);
 
}
