package web.sched.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql ,PreparedStatement.RETURN_GENERATED_KEYS);
		) {
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

}
