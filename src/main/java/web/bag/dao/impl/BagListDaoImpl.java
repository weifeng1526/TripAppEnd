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
	public int add(BagList bagList) {
	    // 檢查外鍵 memNo 是否有效
	    if (!doesMemberExist(bagList.getMemNo())) {
	        System.err.println("Member with memNo " + bagList.getMemNo() + " does not exist.");
	        return 0; // 或拋出異常
	    }

	    String sql = "INSERT INTO bag_list (bl_memno, bl_schno, bl_itemno, bl_ready) VALUES (?, ?, ?, ?)";
	    try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, bagList.getMemNo());
	        stmt.setInt(2, bagList.getSchNo());
	        stmt.setInt(3, bagList.getItemNo());
	        stmt.setBoolean(4, bagList.isReady());
	        return stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0; // 插入失敗
	    }
	}


	@Override
	public int delete(int memNo, int schNo, int itemNo) {
		String sql = "DELETE FROM bag_list WHERE bl_memno = ? AND bl_schno = ? AND bl_itemno = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, memNo);
			stmt.setInt(2, schNo);
			stmt.setInt(3, itemNo);

			// 返回受影響的行數
			return stmt.executeUpdate(); // 若成功刪除一條記錄，返回 1
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // 刪除失敗時返回 0
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
				baglist.setMemNo(rs.getInt("bl_memno"));
				baglist.setSchNo(rs.getInt("bl_schno"));
				baglist.setItemNo(rs.getInt("bl_itemno"));
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
				bag.setMemNo(rs.getInt("bl_memno"));
				bag.setSchNo(rs.getInt("bl_schno"));
				bag.setItemNo(rs.getInt("bl_itemno"));
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
		String sql = "SELECT bl.bl_memno, bl.bl_schno, bl.bl_itemno, bl.bl_ready, i.item_name " + "FROM bag_list bl "
				+ "JOIN item i ON bl.bl_itemno = i.item_no " + // 聯接條件：bag_list.bl_itemno = item.item_no
				"WHERE bl.bl_schno = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, schNo); // 只設定 schNo
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BagList bag = new BagList();
				bag.setMemNo(rs.getInt("bl_memno"));
				bag.setSchNo(rs.getInt("bl_schno"));
				bag.setItemNo(rs.getInt("bl_itemno"));
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
	
	
	private boolean doesMemberExist(int memNo) {
	    String sql = "SELECT COUNT(*) FROM member WHERE mem_no = ?";
	    try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, memNo);  // 設定會員號
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;  // 如果查詢結果大於 0，表示會員存在
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();  // 日誌記錄錯誤
	    }
	    return false;  // 會員不存在
	}


}
