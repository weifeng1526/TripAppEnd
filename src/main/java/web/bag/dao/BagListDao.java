package web.bag.dao;

import java.util.List;

import javax.naming.NamingException;

import web.bag.vo.BagList;

public interface BagListDao {

    // 查詢所有行李
	List<BagList> selectAllbags() throws NamingException;

	List<BagList> getBagListByMemNoAndSchNo(int memNo, int schNo);

	List<BagList> getBagListBySchNo(int schNo);

	int add(BagList bagList);

	int delete(int memNo, int schNo, int itemNo);


   

}
