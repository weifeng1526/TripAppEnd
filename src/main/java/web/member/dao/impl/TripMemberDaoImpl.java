package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.dao.TripMemberDao;
import web.member.vo.TripMember;

public class TripMemberDaoImpl implements TripMemberDao{
	private DataSource ds;
	
	public TripMemberDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/trip");
	}

	@Override
	public TripMember selectByEmail(String email) {
		String sql = "select * from MEMBER where MEM_EMAIL = ?";
		try (
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					TripMember member = new TripMember();
					member.setMemNo(rs.getInt(1));
					member.setMemEmail(rs.getString("MEM_EMAIL"));
					member.setMemName(rs.getString("MEM_NAME"));
					member.setMemPw(rs.getString("MEM_PW"));
					member.setMemSta(rs.getByte((byte)1));
					member.setMemIcon(rs.getBytes(null));
					return member;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int save(TripMember member) {
		String sql = 
				"insert into MEMBER"
				+ "(MEM_EMAIL, MEM_NAME, MEM_PW) "
				+ "values(?, ?, ?)";
		try (
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)
			){
			pstmt.setString(1, member.getMemEmail());
			pstmt.setString(2, member.getMemName());
			pstmt.setString(3, member.getMemPw());
			return pstmt.executeUpdate();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
