package web.bag.dao;

import java.util.List;

import web.bag.vo.Item;

public interface ItemDao {
    // 查詢所有物品
    List<Item> selectAllItems();

    // 根據物品編號查詢物品
    Item selectByItemno(Integer itemno);
    
    // 插入一個新的物品 (如果需要)
    boolean insertItem(Item item);

    // 更新物品資料 (如果需要)
    boolean updateItem(Item item);

    // 刪除物品 (如果需要)
    boolean deleteItem(Integer itemno);


	List<List<Item>> selectItemsGroupedByType();

}