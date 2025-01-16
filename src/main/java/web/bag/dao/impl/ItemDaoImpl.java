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

//	查詢資料庫所有物品
	@Override
	public List<Item> selectAllItems() throws NamingException {

		String sql = "SELECT * FROM item"; // SQL 查询所有物品
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			List<Item> items = new ArrayList<>();
			while (rs.next()) {
				Item item = new Item();
				item.setItemNo(rs.getInt("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setItemType(rs.getInt("item_type"));
				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			throw new NamingException("Database error: " + e.getMessage());
		}
	}

//	查詢資料庫所有物品(根據type)
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

//	not yet查詢資料庫所有物品(根據itemno)
	@Override
	public Item selectByItemno(Integer itemno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> selectIfExist(Integer memNo, Integer schNo) {
	    List<Item> items = new ArrayList<>();

	    // 檢查 memNo 和 schNo 是否為 null
	    if (memNo == null || schNo == null) {
	        System.out.println("Error: memNo or schNo cannot be null");
	        return items;  // 返回空的列表，或可以拋出異常
	    }

	    String sql = "SELECT " +
	                 "    i.item_no AS itemId, " +
	                 "    i.item_name AS itemName, " +
	                 "    CASE " +
	                 "        WHEN bl.bl_itemno IS NOT NULL THEN 'exist' " +
	                 "        ELSE 'nonexist' " +
	                 "    END AS status " +
	                 "FROM " +
	                 "    item i " +
	                 "LEFT JOIN " +
	                 "    bag_list bl " +
	                 "ON " +
	                 "    i.item_no = bl.bl_itemno " +
	                 "    AND bl.bl_memno = ? " +
	                 "    AND bl.bl_schno = ?;";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        // 設定 SQL 的參數
	        statement.setInt(1, memNo);  // 設定第一個參數 (bl_memno)
	        statement.setInt(2, schNo);  // 設定第二個參數 (bl_schno)

	        // 執行查詢
	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                Item item = new Item();
	                item.setItemNo(rs.getInt("itemId"));
	                item.setItemName(rs.getString("itemName"));
	                // 根據你的需求處理 `status`，比如記錄是否存在
	                String status = rs.getString("status");
	                if ("exist".equals(status)) {
	                    // 處理存在的物品（已打勾）
	                    item.setItemExist(true);  // 假設你有 itemExist 屬性
	                } else {
	                    // 處理不存在的物品（未打勾）
	                    item.setItemExist(false); // 假設你有 itemExist 屬性
	                }
	                items.add(item);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return items;
	}

}
