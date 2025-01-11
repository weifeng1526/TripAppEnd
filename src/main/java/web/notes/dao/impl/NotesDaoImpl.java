package web.notes.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.notes.dao.NotesDao;
import web.notes.vo.Notes;
import web.sched.vo.Dest;

public class NotesDaoImpl implements NotesDao {
	private DataSource ds;

    public  NotesDaoImpl() throws NamingException {
        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
    }
    @Override
	public Notes getRecordByDstNo(int dstNo, int memNo) {
		String sql = "SELECT * FROM dst_record where dst_no = ? and mem_no = ?";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, dstNo); // 使用方法參數 dstNo 設置佔位符
			pstmt.setInt(2, memNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Notes notes = new Notes();
				notes.setDrNo(rs.getInt(1));
				notes.setMemNo(rs.getInt(2));
				notes.setDstNo(rs.getInt(3));
				notes.setDrText(rs.getString(4));
				return notes;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Notes notes) {
		String sql = "update dst_record set dr_text = ? where dr_no = ?";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
				){
			pstmt.setString(1, notes.getDrText());
			pstmt.setInt(2, notes.getDrNo());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public List<Notes> getAllNotes() {
		String sql = "SELECT * FROM dst_record";
		List<Notes> list = new ArrayList<Notes>();
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Notes notes = new Notes();
				notes.setDrNo(rs.getInt(1));
				notes.setMemNo(rs.getInt(2));
				notes.setDstNo(rs.getInt(3));
				notes.setDrText(rs.getString(4));
				list.add(notes);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int create(Notes notes) {
		String sql = "INSERT INTO dst_record (mem_no, dst_no) VALUES (? ,?)";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
				pstmt.setInt(1, notes.getMemNo());
				pstmt.setInt(2, notes.getDstNo());
				pstmt.executeUpdate();
				  ResultSet generatedKeys = pstmt.getGeneratedKeys();
		            if (generatedKeys.next()) {
		                int id = generatedKeys.getInt(1);
		                notes.setDrNo(id);
		                return id;
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return 0;
		    }
	}


