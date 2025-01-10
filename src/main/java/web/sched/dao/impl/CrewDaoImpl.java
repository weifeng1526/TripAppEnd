package web.sched.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.dao.CrewDao;
import web.sched.vo.Crew;

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
	public Crew selectByMemId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByMemId(Integer id) {
		// TODO Auto-generated method stub
		return 0;
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
}
