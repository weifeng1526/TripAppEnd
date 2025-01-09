package web.sched.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.dao.DestDao;
import web.sched.vo.Dest;

public class DestDaoImpl implements DestDao {
	private DataSource ds;

    public DestDaoImpl() throws NamingException {
        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
    }
	@Override
	public List<Dest> selectAllBySchId(int id) {
		String sql = "SELECT * FROM dest WHERE sch_no = ?";
		List<Dest> list = new ArrayList<Dest>();
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Dest dest = new Dest();
				dest.setDstNo(rs.getInt(1));
				dest.setSchNo(rs.getInt(2));
				dest.setPoiNo(rs.getInt(3));
				dest.setDstName(rs.getString(4));
				dest.setDstAddr(rs.getString(5));
				dest.setDstPic(rs.getBytes(6));
				dest.setDstDep(rs.getString(7));
				dest.setDstDate(rs.getString(8));
				dest.setDstStart(rs.getString(9));
				dest.setDstEnd(rs.getString(10));
				dest.setDstInr(rs.getString(11));
				list.add(dest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Dest> selectAllByDate(Date date) {
		String sql = "SELECT * FROM dest WHERE dst_date = ?";
		List<Dest> list = new ArrayList<Dest>();
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, date.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Dest dest = new Dest();
				dest.setDstNo(rs.getInt(1));
				dest.setSchNo(rs.getInt(2));
				dest.setPoiNo(rs.getInt(3));
				dest.setDstName(rs.getString(4));
				dest.setDstAddr(rs.getString(5));
				dest.setDstPic(rs.getBytes(6));
				dest.setDstDep(rs.getString(7));
				dest.setDstDate(rs.getString(8));
				dest.setDstStart(rs.getString(9));
				dest.setDstEnd(rs.getString(10));
				dest.setDstInr(rs.getString(11));
				list.add(dest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Dest dest) {
		String sql = "INSERT INTO dest (sch_no, poi_no, dst_name, dst_addr, dst_pic, dst_dep, dst_date, dst_start, dst_end, dst_inr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setInt(1, dest.getSchNo()); // 設定 schNo
			pstmt.setInt(2, dest.getPoiNo()); // 設定 poiNo
			pstmt.setString(3, dest.getDstName()); // 設定 dstName
			pstmt.setString(4, dest.getDstAddr()); // 設定 dstAddr
			pstmt.setBytes(5, dest.getDstPic()); // 設定 dstPic
			pstmt.setString(6, dest.getDstDep()); // 設定 dstDep
			pstmt.setString(7, dest.getDstDate()); // 設定 dstDate
			pstmt.setString(8, dest.getDstStart()); // 設定 dstStart
			pstmt.setString(9, dest.getDstEnd()); // 設定 dstEnd
			pstmt.setString(10, dest.getDstInr()); // 設定 dstInr
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				dest.setDstNo(id);
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Dest selectLastOne() {
		String sql = "SELECT * FROM dest ORDER BY dst_no DESC LIMIT 1";
		Dest dest = new Dest();
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dest.setDstNo(rs.getInt(1));
				dest.setSchNo(rs.getInt(2));
				dest.setPoiNo(rs.getInt(3));
				dest.setDstName(rs.getString(4));
				dest.setDstAddr(rs.getString(5));
				dest.setDstPic(rs.getBytes(6));
				dest.setDstDep(rs.getString(7));
				dest.setDstDate(rs.getString(8));
				dest.setDstStart(rs.getString(9));
				dest.setDstEnd(rs.getString(10));
				dest.setDstInr(rs.getString(11));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dest;
	}

	@Override
	public int update(Dest dest) {
		String sql = "UPDATE dest SET sch_no = ?, poi_no = ?, dst_name = ?, dst_addr = ?, dst_pic = ?, dst_dep = ?, dst_date = ?, dst_start = ?, dst_end = ?, dst_inr = ? WHERE dst_no = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement pstmt = connection.prepareStatement(sql);
		) {
			pstmt.setInt(1, dest.getSchNo());
			pstmt.setInt(2, dest.getPoiNo());
			pstmt.setString(3, dest.getDstName());
			pstmt.setString(4, dest.getDstAddr());
			pstmt.setBytes(5, dest.getDstPic());
			pstmt.setString(6, dest.getDstDep());
			pstmt.setString(7, dest.getDstDate());
			pstmt.setString(8, dest.getDstStart());
			pstmt.setString(9, dest.getDstEnd());
			pstmt.setString(10, dest.getDstInr());
			pstmt.setInt(11, dest.getDstNo());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//會員編號-1 行程編號n = 範本 
	@Override
	public List<Dest> selectByMemIdAndSchId(int memId, int schId) {
		String sql = "SELECT d.* FROM dest d JOIN sched s ON d.sch_no = s.sch_no WHERE s.mem_no = ? && d.sch_no = ?";
		List<Dest> list = new ArrayList<Dest>();
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, memId);
			pstmt.setInt(2, schId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Dest dest = new Dest();
				dest.setDstNo(rs.getInt(1));
				dest.setSchNo(rs.getInt(2));
				dest.setPoiNo(rs.getInt(3));
				dest.setDstName(rs.getString(4));
				dest.setDstAddr(rs.getString(5));
				dest.setDstPic(rs.getBytes(6));
				dest.setDstDep(rs.getString(7));
				dest.setDstDate(rs.getString(8));
				dest.setDstStart(rs.getString(9));
				dest.setDstEnd(rs.getString(10));
				dest.setDstInr(rs.getString(11));
				list.add(dest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insertByCopy(int schId, int schIdOfSample) {
		String sql = "INSERT INTO dest (sch_no, poi_no, dst_name, dst_addr, dst_dep, dst_date, dst_start, dst_end, dst_inr) "
				+ "SELECT ?, poi_no, dst_name, dst_addr, dst_dep, dst_date, dst_start, dst_end, dst_inr "
				+ "FROM dest "
				+ "WHERE sch_no = ?";
		
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setInt(1, schId);
			pstmt.setInt(2, schIdOfSample);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM dest WHERE sch_no = ?";
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