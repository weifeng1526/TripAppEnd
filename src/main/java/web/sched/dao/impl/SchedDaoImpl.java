package web.sched.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.dao.SchedDao;
import web.sched.vo.Sched;
import web.sched.vo.SchedInfo;

public class SchedDaoImpl implements SchedDao {
	private DataSource ds;

	public SchedDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

	@Override
	public List<Sched> selectAll() {
		String sql = "SELECT * FROM sched";
		List<Sched> list = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Sched sched = new Sched();
				sched.setSchNo(rs.getInt(1));
				sched.setMemNo(rs.getInt(2));
				sched.setSchState(rs.getInt(3));
				sched.setSchName(rs.getString(4));
				sched.setSchCon(rs.getString(5));
				sched.setSchStart(rs.getLong(6));
				sched.setSchEnd(rs.getLong(7));
				sched.setSchCur(rs.getString(8));
				sched.setSchPic(rs.getBytes(9));
				sched.setSchLastEdit(rs.getLong(10)); // 從 ResultSet 中取值
				list.add(sched);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Sched selectById(Integer id) {
		String sql = "SELECT * FROM sched WHERE sch_no = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Sched sched = new Sched();
					sched.setSchNo(rs.getInt(1));
					sched.setMemNo(rs.getInt(2));
					sched.setSchState(rs.getInt(3));
					sched.setSchName(rs.getString(4));
					sched.setSchCon(rs.getString(5));
					sched.setSchStart(rs.getDate(6).getTime());
					sched.setSchEnd(rs.getDate(7).getTime());
					sched.setSchCur(rs.getString(8));
					sched.setSchPic(rs.getBytes(9));
					sched.setSchLastEdit(rs.getTimestamp(10).getTime()); // 從 ResultSet 中取值
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
		String sql = "UPDATE sched " + "SET mem_no = ?, " + "sch_state = ?, " + "sch_name = ?, " + "sch_con = ?, "
				+ "sch_start = ?, " + "sch_end = ?, " + "sch_cur = ?, " + "sch_pic = ?, " + "sch_last_edit = ? "
				+ "WHERE sch_no = ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, sched.getMemNo());
			pstmt.setInt(2, sched.getSchState());
			pstmt.setString(3, sched.getSchName());
			pstmt.setString(4, sched.getSchCon());
			pstmt.setLong (5, sched.getSchStart());
			pstmt.setLong (6, sched.getSchEnd());
			pstmt.setString(7, sched.getSchCur());
			pstmt.setBytes(8, sched.getSchPic());
			pstmt.setLong (9, sched.getSchLastEdit());
			pstmt.setInt(10, sched.getSchNo());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteById(int id) {
		String sql = "DELETE FROM sched WHERE sch_no = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			int isDeleted = pstmt.executeUpdate();
			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Sched> selectByMemId(int id) {
		String sql = "SELECT * FROM sched WHERE mem_no = ? AND sch_state = 1";
		List<Sched> list = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sched sched = new Sched();
				sched.setSchNo(rs.getInt(1));
				sched.setMemNo(rs.getInt(2));
				sched.setSchState(rs.getInt(3));
				sched.setSchName(rs.getString(4));
				sched.setSchCon(rs.getString(5));
				sched.setSchStart(rs.getLong(6));
				sched.setSchEnd(rs.getLong(7));
				sched.setSchCur(rs.getString(8));
				sched.setSchPic(rs.getBytes(9));
				sched.setSchLastEdit(rs.getLong(10)); // 從 ResultSet 中取值
				list.add(sched);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateImage(Integer schId, InputStream imageStream) {
		int updatedId = 0; 
		String sql = "UPDATE sched SET sch_pic = ? WHERE sch_no = ?";
		try (
			Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(sql)
		) {
			stmt.setBlob(1, imageStream);
			stmt.setInt(2, schId);
			int isUpdated = stmt.executeUpdate();
			if(isUpdated > 0) updatedId = schId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedId;
	}

	@Override
	public List<Sched> selectFromCrewByMemId(int id) {
		String sql = "SELECT * FROM sched s JOIN crew c ON s.sch_no = c.sch_no WHERE c.mem_no = ? AND c.crew_ide = 1";
		List<Sched> list = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sched sched = new Sched();
				sched.setSchNo(rs.getInt(1));
				sched.setMemNo(rs.getInt(2));
				sched.setSchState(rs.getInt(3));
				sched.setSchName(rs.getString(4));
				sched.setSchCon(rs.getString(5));
				sched.setSchStart(rs.getLong(6));
				sched.setSchEnd(rs.getLong(7));
				sched.setSchCur(rs.getString(8));
				sched.setSchPic(rs.getBytes(9));
				sched.setSchLastEdit(rs.getLong(10)); // 從 ResultSet 中取值
				list.add(sched);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SchedInfo> selectSchedInfo(Integer memId) {
		String sql = "SELECT sch_no, mem_no, sch_state, sch_name, sch_con, sch_start, sch_end, sch_cur, sch_last_edit FROM sched WHERE mem_no = ?";
		List<SchedInfo> list = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);

		) {
			pstmt.setInt(1, memId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SchedInfo schedInfo = new SchedInfo();
				schedInfo.setSchNo(rs.getInt("sch_no"));
				schedInfo.setMemNo(rs.getInt("mem_no"));
				schedInfo.setSchState(rs.getInt("sch_state"));
				schedInfo.setSchName(rs.getString("sch_name"));
				schedInfo.setSchCon(rs.getString("sch_con"));
				Date startDate = rs.getDate("sch_start");
				schedInfo.setSchStart(startDate.getTime());
				Date endDate = rs.getDate("sch_end");
				schedInfo.setSchEnd(endDate.getTime());
				schedInfo.setSchCur(rs.getString("sch_cur"));
				Timestamp lastEditTime = rs.getTimestamp("sch_end");
				schedInfo.setSchLastEdit(lastEditTime.getTime());
				list.add(schedInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SchedInfo> selectSchedInfo(Integer memId, Integer limit, Integer offset) {
		String sql = "SELECT sch_no, mem_no, sch_state, sch_name, sch_con, sch_start, sch_end, sch_cur, sch_last_edit FROM sched WHERE mem_no = ? LIMIT ? OFFSET ?";
		List<SchedInfo> list = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);

		) {
			pstmt.setInt(1, memId);
			pstmt.setInt(2, limit);
			pstmt.setInt(3, offset);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SchedInfo schedInfo = new SchedInfo();
				schedInfo.setSchNo(rs.getInt("sch_no"));
				schedInfo.setMemNo(rs.getInt("mem_no"));
				schedInfo.setSchState(rs.getInt("sch_state"));
				schedInfo.setSchName(rs.getString("sch_name"));
				schedInfo.setSchCon(rs.getString("sch_con"));
				Date startDate = rs.getDate("sch_start");
				schedInfo.setSchStart(startDate.getTime());
				Date endDate = rs.getDate("sch_end");
				schedInfo.setSchEnd(endDate.getTime());
				schedInfo.setSchCur(rs.getString("sch_cur"));
				Timestamp lastEditTime = rs.getTimestamp("sch_end");
				schedInfo.setSchLastEdit(lastEditTime.getTime());
				list.add(schedInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SchedInfo insertSchedInfo(SchedInfo schedInfo) {
		String sql = "INSERT INTO sched"
				+ "(mem_no, sch_state, sch_name, sch_con, sch_start, sch_end, sch_cur, sch_last_edit)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
			pstmt.setInt(1, schedInfo.getMemNo());
			pstmt.setInt(2, schedInfo.getSchState());
			pstmt.setString(3, schedInfo.getSchName());
			pstmt.setString(4, schedInfo.getSchCon());
			Date startDate = new Date(schedInfo.getSchStart());
			pstmt.setDate(5, startDate);
			Date endDate = new Date(schedInfo.getSchStart());
			pstmt.setDate(6, endDate);
			pstmt.setString(7, schedInfo.getSchCur());
			Timestamp lastEdiTime = new Timestamp(schedInfo.getSchLastEdit());
			pstmt.setTimestamp(8, lastEdiTime);
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				schedInfo.setSchNo(id);
				return schedInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
