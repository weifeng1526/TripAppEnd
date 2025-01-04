package web.bag.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	    public List<Item> selectAllItems() {
	        List<Item> items = new ArrayList<>();
	        String sql = "SELECT * FROM item"; // 查詢所有物品
	        
	        try (Connection conn = ds.getConnection(); 
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    Item item = new Item();
	                    item.setItemNo(rs.getInt("item_no"));
	                    item.setItemName(rs.getString("item_name"));
	                    item.setItemType(rs.getInt("item_type"));
	                    items.add(item);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return items;
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
}
