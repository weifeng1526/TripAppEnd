package web.bag.dao;

import java.util.List;

import javax.naming.NamingException;

import web.bag.vo.Item;

public interface ItemDao {
   
    // 根據物品編號查詢物品
    Item selectByItemno(Integer itemNo);
    
    // 插入一個新的物品 (如果需要)
    boolean insertItem(Item item);

    // 更新物品資料 (如果需要)
    boolean updateItem(Item item);

    // 刪除物品 (如果需要)
    boolean deleteItem(Integer itemNo);

    // 查詢所有物品
    List<Item> selectAllItems() throws NamingException;
    
    // 查詢所有物品ByType
	List<List<Item>> selectItemsGroupedByType();

	List<Item> selectIfExist(Integer memNo,Integer schNo) throws NamingException;

}