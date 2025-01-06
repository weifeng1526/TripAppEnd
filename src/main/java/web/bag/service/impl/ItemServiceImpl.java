package web.bag.service.impl;

import java.util.List;

import web.bag.dao.ItemDao;
import web.bag.vo.Item;

public class ItemServiceImpl {
	private ItemDao itemDao;

    public void ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<List<Item>> getItemsGroupedByType() {
        return itemDao.selectItemsGroupedByType();
    }
}
