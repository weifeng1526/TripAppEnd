package web.bag.dao;

import java.util.List;

import web.bag.vo.BagList;

public interface BagListDao {
    void insertBagList(BagList bagList) throws Exception;

	List<BagList> selectAllbags();

}
