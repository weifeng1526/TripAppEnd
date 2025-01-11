package web.map.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.map.dao.MapDao;

import web.map.vo.Map;

public class MapDaoImpl implements MapDao {
	private DataSource ds;

	public MapDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

	@Override
	public boolean checkPlace(String address) {
		String sql = "select 1 from poi where poi_add= ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, address);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int insert(Map map) {
		String sql = "insert into poi(poi_add,poi_name,poi_lng,poi_lat,poi_lab,poi_pic,poi_like) values(?,?,?,?,?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			// 填上問號
			pstmt.setString(1, map.getPoiAdd());
			pstmt.setString(2, map.getPoiName());
			pstmt.setBigDecimal(3, map.getPoiLng());
			pstmt.setBigDecimal(4, map.getPoiLat());
			pstmt.setString(5, map.getPoiLab());
			pstmt.setString(5, map.getPoiPic());
			pstmt.setInt(7, map.getPoiLike());
			// 執行寫入
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

	@Override
	public Map search(String address) {
		String sql = "select * from poi where poi_add = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, address);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Map map = new Map();
					map.setPoiNo(rs.getInt("poi_no"));
					map.setPoiAdd(rs.getString("poi_add"));
					map.setPoiName(rs.getString("poi_name"));
					map.setPoiLng(rs.getBigDecimal("poi_lng"));
					map.setPoiLat(rs.getBigDecimal("poi_lat"));
					map.setPoiLab(rs.getString("poi_lab"));
					map.setPoiLike(rs.getInt("poi_like"));

					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int inseartPlan(Map map,int addPlanNumber) {
		String sql = "insert into dest(sch_no,poi_no,dst_name,dst_addr) values(?,?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			// 填上問號
			pstmt.setInt(1,addPlanNumber);
			pstmt.setInt(2, map.getPoiNo());
			pstmt.setString(3, map.getPoiName());
			pstmt.setString(4, map.getPoiAdd());
			
			// 執行寫入
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

}
