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

import web.bag.dao.BagListDao;
import web.bag.vo.BagList;
import web.bag.vo.Item;

public class BagListDaoImpl implements BagListDao {
	private DataSource ds;

	public BagListDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

//	新增物品進入行李清單
	@Override
	public void insertBagList(BagList bagList) throws Exception {
		String sql = "INSERT INTO bag_list (bl_memno, bl_schno, bl_itemno, bl_ready) VALUES (?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, bagList.getMemno());
			stmt.setInt(2, bagList.getSchno());
			stmt.setInt(3, bagList.getItemno());
			stmt.setBoolean(4, bagList.isReady());
			stmt.executeUpdate();
		}
	}

//	查詢資料庫所有行李
	@Override
	public List<BagList> selectAllbags() throws NamingException {
		String sql = "SELECT * FROM bag_list";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			List<BagList> bagLists = new ArrayList<>();
			while (rs.next()) {
				BagList baglist = new BagList();
				baglist.setMemno(rs.getInt("bl_memno"));
				baglist.setSchno(rs.getInt("bl_schno"));
				baglist.setItemno(rs.getInt("bl_itemno"));
				baglist.setReady(rs.getBoolean("bl_ready"));
				bagLists.add(baglist);
			}
			return bagLists;
		} catch (SQLException e) {
			throw new NamingException("Database error: " + e.getMessage());
		}
	}

	@Override
	public List<BagList> getBagListByMemNoAndSchNo(int memNo, int schNo) {
		List<BagList> bagList = new ArrayList<>();
		String sql = "SELECT * FROM bag_list WHERE bl_memno = ? AND bl_schno = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, memNo);
			ps.setInt(2, schNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BagList bag = new BagList();
				bag.setMemno(rs.getInt("bl_memno"));
				bag.setSchno(rs.getInt("bl_schno"));
				bag.setItemno(rs.getInt("bl_itemno"));
				bag.setReady(rs.getBoolean("bl_ready"));
				bagList.add(bag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bagList;
	}

	@Override
	public List<BagList> getBagListBySchNo(int schNo) {
		List<BagList> bagList = new ArrayList<>();
		 String sql = "SELECT bl.bl_memno, bl.bl_schno, bl.bl_itemno, bl.bl_ready, i.item_name " +
                 "FROM bag_list bl " +
                 "JOIN item i ON bl.bl_itemno = i.item_no " +  // 聯接條件：bag_list.bl_itemno = item.item_no
                 "WHERE bl.bl_schno = ?";		
		 try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, schNo); // 只設定 schNo
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BagList bag = new BagList();
				bag.setMemno(rs.getInt("bl_memno"));
				bag.setSchno(rs.getInt("bl_schno"));
				bag.setItemno(rs.getInt("bl_itemno"));
				bag.setReady(rs.getBoolean("bl_ready"));
				 // 新增 item_name
	            bag.setItemName(rs.getString("item_name"));
				bagList.add(bag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bagList;
	}
}
