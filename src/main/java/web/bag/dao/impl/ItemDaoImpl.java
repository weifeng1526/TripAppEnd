package web.bag.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.bag.dao.ItemDao;
import web.bag.vo.Item;

public class ItemDaoImpl implements ItemDao {
	private DataSource ds;

	public ItemDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");

	}

	@Override
	public List<List<Item>> selectItemsGroupedByType() {
		List<List<Item>> groupedItems = new ArrayList<>();
		String sql = "SELECT * FROM item"; // 查詢所有物品，您可以根據需要加上 GROUP BY

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				// 先創建一個 map 存儲每個類型的物品列表
				Map<Integer, List<Item>> itemGroups = new HashMap<>();

				while (rs.next()) {
					Item item = new Item();
					item.setItemNo(rs.getInt("item_no"));
					item.setItemName(rs.getString("item_name"));
					item.setItemType(rs.getInt("item_type"));

					// 將物品根據 item_type 分組
					itemGroups.computeIfAbsent(item.getItemType(), k -> new ArrayList<>()).add(item);
				}

				// 將每個類型的物品列表添加到最終結果中
				groupedItems.addAll(itemGroups.values());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupedItems;
	}

	@Override
	public boolean insertItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteItem(Integer itemno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item selectByItemno(Integer itemno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> selectAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
