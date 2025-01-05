package web.spending.dao;

import java.util.List;

import web.spending.vo.CostRecd;

public interface CostRecdDao  {
	
	List<CostRecd> findData() ;
	int insert(CostRecd costRecd);
	int update(CostRecd costRecd);
}
