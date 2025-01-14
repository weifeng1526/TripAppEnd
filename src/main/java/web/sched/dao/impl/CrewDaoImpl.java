package web.sched.dao.impl;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.vo.TripMember;
import web.sched.dao.CrewDao;
import web.sched.vo.Crew;
import web.sched.vo.MemberInCrew;

public class CrewDaoImpl implements CrewDao {
	private DataSource ds;

	public CrewDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

	@Override
	public int insert(Crew crew) {

		String sql = "INSERT INTO crew (sch_no, mem_no, crew_peri, crew_ide, crew_name, crew_invited) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setInt(1, crew.getSchNo());
			pstmt.setInt(2, crew.getMemNo());
			pstmt.setByte(3, crew.getCrewPeri());
			pstmt.setByte(4, crew.getCrewIde());
			pstmt.setString(5, crew.getCrewName());
			pstmt.setByte(6, crew.getCrewInvited());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				crew.setCrewNo(id);
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<MemberInCrew> selectMemberInCrew(Integer id) {
		String sql = "SELECT c.crew_no, c.sch_no, m.mem_no, m.mem_icon, m.mem_name, m.mem_email, c.crew_peri, c.crew_ide, c.crew_name, c.crew_invited FROM member m JOIN crew c ON m.mem_no = c.mem_no WHERE c.sch_no = ?";
		List<MemberInCrew> list = new ArrayList<MemberInCrew>(); // 修正清單類型為 Crew
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 將資料從 ResultSet 轉換為 Crew 實例
				MemberInCrew memberInCrew = new MemberInCrew();
				memberInCrew.setCrewNo(rs.getInt("crew_no"));
				memberInCrew.setSchNo(rs.getInt("sch_no"));
				memberInCrew.setMemNo(rs.getInt("mem_no"));
				memberInCrew.setMemIcon(rs.getBytes("m.mem_icon"));
				memberInCrew.setMemName(rs.getString("m.mem_name"));
				memberInCrew.setMemEmail(rs.getString("m.mem_email"));
				memberInCrew.setCrewPeri(rs.getByte("crew_peri"));
				memberInCrew.setCrewIde(rs.getByte("crew_ide"));
				memberInCrew.setCrewName(rs.getString("crew_name"));
				memberInCrew.setCrewInvited(rs.getByte("crew_invited"));
				list.add(memberInCrew); // 添加到清單
			}
		} catch (Exception e) {
			e.printStackTrace(); // 打印例外訊息
		}
		return list;
	}

	@Override
	public int deleteByMemId(Integer id) {
		String sql = "DELETE FROM crew WHERE crew_no = ?";
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
	public List<Crew> selectBySchId(Integer id) {
		String sql = "SELECT * FROM crew WHERE sch_no = ?";
		List<Crew> list = new ArrayList<Crew>(); // 修正清單類型為 Crew
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id); // 設定參數
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 將資料從 ResultSet 轉換為 Crew 實例
				Crew crew = new Crew();
				crew.setCrewNo(rs.getInt("crew_no"));
				crew.setSchNo(rs.getInt("sch_no"));
				crew.setMemNo(rs.getInt("mem_no"));
				crew.setCrewPeri(rs.getByte("crew_peri"));
				crew.setCrewIde(rs.getByte("crew_ide"));
				crew.setCrewName(rs.getString("crew_name"));
				crew.setCrewInvited(rs.getByte("crew_invited"));
				list.add(crew); // 添加到清單
			}
		} catch (Exception e) {
			e.printStackTrace(); // 打印例外訊息
		}
		return list;
	}

	@Override
	public List<TripMember> selectMembers() {
		String sql = "SELECT * FROM member WHERE mem_no > 0";
		List<TripMember> list = new ArrayList<TripMember>(); // 修正清單類型為 Crew
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				TripMember tripMember = new TripMember();
				tripMember.setMemNo(rs.getInt(1));
				tripMember.setMemEmail(rs.getString(2));
				tripMember.setMemName(rs.getString(3));
				tripMember.setMemPw(rs.getString(4));
				tripMember.setMemSta(rs.getByte(5));
				tripMember.setMemIcon(rs.getString(6));
				list.add(tripMember);
			}
		} catch (Exception e) {
			e.printStackTrace(); // 打印例外訊息
		}
		return list;
	}
}
