package web.bag.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	@Override
	public List<BagList> selectAllbags() {
		List<BagList> bagLists = new ArrayList<>();
		String sql = "SELECT * FROM BAG_List";
		
		try (Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					BagList baglist = new BagList();
					baglist.setMemno(rs.getInt("mem_no"));
					baglist.setSchno(rs.getInt("sch_no"));
					baglist.setItemno(rs.getInt("item_no"));
					baglist.setReady(rs.getBoolean("ready"));
					bagLists.add(baglist);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bagLists;
	}
}
