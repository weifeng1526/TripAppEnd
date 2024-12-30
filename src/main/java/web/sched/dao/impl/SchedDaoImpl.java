package web.sched.dao.impl;

import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import core.util.DsConstant;
import core.util.JdbcConstant;
import web.sched.dao.SchedDao;
import web.sched.vo.Sched;
import core.util.*;

public class SchedDaoImpl implements SchedDao {
	DsConstant dsConstant = new DsConstant();
	HikariDataSource ds = dsConstant.DsConfig();
	@Override
	public int insert(Sched sched) {
		String sql = 
	            "INSERT INTO sched"
	                    + "("	          
	                    + "mem_no,"
	                    + "sch_state,"
	                    + "sch_name,"
	                    + "sch_con,"
	                    + "sch_start,"
	                    + "sch_end,"
	                    + "sch_cur,"
	                    + "sch_pic"
	                    + ") "
	                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);			
		) {
			pstmt.setInt(1, sched.getMemNo());
			pstmt.setInt(2, sched.getSchState());
			pstmt.setString(3, sched.getSchName());
			pstmt.setString(4, sched.getSchCon());
			pstmt.setDate(5, sched.getSchStart());
			pstmt.setDate(6, sched.getSchEnd());
			pstmt.setString(7, sched.getSchCur());
			pstmt.setBytes(8, sched.getSchPic());
			pstmt.executeUpdate();
			// 在excute之後get編號
			ResultSet generatedKeys = pstmt.getGeneratedKeys();  
			if (generatedKeys.next()) {
			   int id = generatedKeys.getInt(1);
			   sched.setSchNo(id);
			   return id;
			} else {
				int id = 0;
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Sched> selectAll() {
		String sql = "SELECT * FROM sched";
		List<Sched> list = new ArrayList<>();
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt =  conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				Sched sched = new Sched();
				sched.setSchNo(rs.getInt(1));
				sched.setMemNo(rs.getInt(2));
				sched.setSchState(rs.getInt(3));
				sched.setSchName(rs.getString(4));
				sched.setSchCon(rs.getString(5));
				sched.setSchStart(rs.getDate(6));
				sched.setSchEnd(rs.getDate(7));
				sched.setSchCur(rs.getString(8));
				sched.setSchPic(rs.getBytes(9));
				list.add(sched);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Sched selectById(Integer id) {
		String sql = "SELECT * FROM sched WHERE sch_no = ?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Sched sched = new Sched();
					sched.setSchNo(rs.getInt(1));
					sched.setMemNo(rs.getInt(2));
					sched.setSchState(rs.getInt(3));
					sched.setSchName(rs.getString(4));
					sched.setSchCon(rs.getString(5));
					sched.setSchStart(rs.getDate(6));
					sched.setSchEnd(rs.getDate(7));
					sched.setSchCur(rs.getString(8));
					sched.setSchPic(rs.getBytes(9));
					return sched;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Sched sched) {
		//最好是打成一行...換行容易忽略空格
		String sql = 
				"UPDATE sched "
				+ "SET sch_state = ?, "
				+ "sch_name = ?, "
				+ "sch_con = ?, "
				+ "sch_start = ?, "
				+ "sch_end = ?, "
				+ "sch_cur = ?, "
				+ "sch_pic = ? "
				+ "WHERE sch_no = ?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setInt(1, sched.getSchState());
			pstmt.setString(2, sched.getSchName());
			pstmt.setString(3, sched.getSchCon());
			pstmt.setDate(4, sched.getSchStart());
			pstmt.setDate(5, sched.getSchEnd());			
			pstmt.setString(6, sched.getSchCur());			
			pstmt.setBytes(7, sched.getSchPic());
			pstmt.setInt(8, sched.getSchNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int deleteById(int id) {
		String sql = "DELETE FROM sched WHERE sch_no = ?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, id);
			int isDeleted = pstmt.executeUpdate();
			return isDeleted; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
