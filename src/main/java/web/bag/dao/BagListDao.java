package web.bag.dao;

import java.util.List;

import javax.naming.NamingException;

import web.bag.vo.BagList;

public interface BagListDao {
    void insertBagList(BagList bagList) throws Exception;

    // 查詢所有行李
	List<BagList> selectAllbags() throws NamingException;

	List<BagList> getBagListByMemNoAndSchNo(int memNo, int schNo);

	List<BagList> getBagListBySchNo(int schNo);


   

}
