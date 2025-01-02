package web.sched.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.dao.SchedDao;
import web.sched.vo.Sched;

public class SchedDaoImpl implements SchedDao {
	private DataSource ds;

    public SchedDaoImpl() throws NamingException {
        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
    }

    @Override
    public int insert(Sched sched) {
        String sql = 
            "INSERT INTO sched"
                + "(mem_no, sch_state, sch_name, sch_con, sch_start, sch_end, sch_cur, sch_pic, sch_last_edit)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            pstmt.setInt(1, sched.getMemNo());
            pstmt.setInt(2, sched.getSchState());
            pstmt.setString(3, sched.getSchName());
            pstmt.setString(4, sched.getSchCon());
            pstmt.setString(5, sched.getSchStart());
            pstmt.setString(6, sched.getSchEnd());
            pstmt.setString(7, sched.getSchCur());
            pstmt.setBytes(8, sched.getSchPic());
            pstmt.setString(9, sched.getSchLastEdit());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                sched.setSchNo(id);
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
        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                Sched sched = new Sched();
                sched.setSchNo(rs.getInt(1));
                sched.setMemNo(rs.getInt(2));
                sched.setSchState(rs.getInt(3));
                sched.setSchName(rs.getString(4));
                sched.setSchCon(rs.getString(5));
                sched.setSchStart(rs.getString(6));
                sched.setSchEnd(rs.getString(7));
                sched.setSchCur(rs.getString(8));
                sched.setSchPic(rs.getBytes(9));
                sched.setSchLastEdit(rs.getString(10));  // 從 ResultSet 中取值
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
                    sched.setSchStart(rs.getString(6));
                    sched.setSchEnd(rs.getString(7));
                    sched.setSchCur(rs.getString(8));
                    sched.setSchPic(rs.getBytes(9));
                    sched.setSchLastEdit(rs.getString(10));  // 從 ResultSet 中取值
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
        String sql = 
            "UPDATE sched "
            + "SET mem_no = ?, "
            + "sch_state = ?, "
            + "sch_name = ?, "
            + "sch_con = ?, "
            + "sch_start = ?, "
            + "sch_end = ?, "
            + "sch_cur = ?, "
            + "sch_pic = ?, "
            + "sch_last_edit = ? "
            + "WHERE sch_no = ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
        	pstmt.setInt(1, sched.getMemNo());
            pstmt.setInt(2, sched.getSchState());
            pstmt.setString(3, sched.getSchName());
            pstmt.setString(4, sched.getSchCon());
            pstmt.setString(5, sched.getSchStart());
            pstmt.setString(6, sched.getSchEnd());
            pstmt.setString(7, sched.getSchCur());
            pstmt.setBytes(8, sched.getSchPic());
            pstmt.setString(9, sched.getSchLastEdit());
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
