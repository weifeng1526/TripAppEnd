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
}