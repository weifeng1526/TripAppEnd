package web.spending.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.vo.Sched;
import web.spending.dao.CostRecdDao;
import web.spending.vo.CostRecd;
import web.spending.vo.Crew;

public class CostRecdDaoImpl implements CostRecdDao {
	private DataSource ds;

	public CostRecdDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

	@Override
	public List<CostRecd> findDataAll() {
		CostRecd costRecd;
		String sql = "select * from cost_recd c join member m join sched s";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			try (ResultSet rs = pstmt.executeQuery()) {
				List<CostRecd> list = new ArrayList<CostRecd>();
				while (rs.next()) {
					costRecd = new CostRecd();
					costRecd.setCostNo(rs.getInt("cr_cost_no"));
					costRecd.setSchNo(rs.getInt("sch_no"));
					costRecd.setSchName(rs.getString("sch_name"));
					costRecd.setCostType(rs.getByte("cr_cost_type"));
					costRecd.setCostPrice(rs.getDouble("cr_cost_price"));
					costRecd.setPaidBy(rs.getInt("cr_paid_by"));
					costRecd.setPaidByName(rs.getString("mem_name"));
					costRecd.setCostPex(rs.getBoolean("cr_cost_pex"));
					costRecd.setCrCurRecord(rs.getString("cr_cur_record"));
					costRecd.setSchCur(rs.getString("sch_cur"));
					costRecd.setCrCur(rs.getString("cr_cur"));
					costRecd.setCrCostTime(rs.getString("cr_cost_time"));
					list.add(costRecd);
				}

				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// 如果要把資料抓過來，那就要先查詢 Select，多表格就用join

	@Override
	public Integer insert(CostRecd costRecd) {
		String sql = "insert into cost_recd(sch_no, cr_cur_record, cr_cost_price, cr_paid_by, cr_cost_type, cr_cost_item, cr_cost_pex,cr_cost_time, cr_cost_no) value(?,?,?,?,?,?,?,?,?)";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)

		) {
			pstmt.setInt(1, costRecd.getSchNo());
			pstmt.setString(2, costRecd.getCrCurRecord());
			pstmt.setDouble(3, costRecd.getCostPrice());
			pstmt.setInt(4, costRecd.getPaidBy());
			pstmt.setByte(5, costRecd.getCostType());
			pstmt.setString(6, costRecd.getCostItem());
			pstmt.setBoolean(7, costRecd.getCostPex());
			pstmt.setString(8, costRecd.getCrCostTime());
			pstmt.setInt(9, costRecd.getCostNo());

			// 執行
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer update(CostRecd costRecd) {

		// 要下條件（where），在寫資料的時候也要去思考使用流程，所需要的資料是什麼！資料的意義。
//		String sql = "update cost_recd set cr_cur_record = ?,  cr_cost_price=?, cr_paid_by=?, cr_cost_type=?, cr_cost_item=?, cr_cost_pex=?,cr_cost_time=?  where cr_cost_no=?";
//		String sql ="update cost_recd set cr_cur_record = "TWD", cr_cost_price=8000888, cr_paid_by=10, cr_cost_type=5, cr_cost_item="泡泡", cr_cost_pex=0,cr_cost_time="2023-11-23 17:00:00"  where cr_cost_no=1";

		String sql = "update cost_recd set cr_cur_record = ?, cr_cost_price=?, cr_paid_by=?, cr_cost_type=?, cr_cost_item=?, cr_cost_pex=?,cr_cost_time=?  where cr_cost_no=?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)

		) {
//			pstmt.setInt(1, costRecd.getSchNo());
			pstmt.setString(1, costRecd.getCrCurRecord());
			pstmt.setDouble(2, costRecd.getCostPrice());
			pstmt.setInt(3, costRecd.getPaidBy());
			pstmt.setByte(4, costRecd.getCostType());
			pstmt.setString(5, costRecd.getCostItem());
			pstmt.setBoolean(6, costRecd.getCostPex());
			pstmt.setString(7, costRecd.getCrCostTime());
			pstmt.setInt(8, costRecd.getCostNo());

			// 執行
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public CostRecd findDataOne(Integer costno) throws Exception {
		System.out.println("testRuby" + costno);
		String sql = "select * from cost_recd cr join member m join sched s where cr_cost_no = ? ";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			System.out.println("Database connection established.");
			pstmt.setInt(1, costno);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					CostRecd costRecd = new CostRecd();
//					costRecd.setCostNo(rs.getInt("cr_cost_no"));
//					costRecd.setSchNo(rs.getInt("sch_no"));
//					costRecd.setCostType(rs.getByte("cr_cost_type"));
//					costRecd.setCostPrice(rs.getDouble("cr_cost_price"));
//					costRecd.setPaidBy(rs.getInt("cr_paid_by"));
//					costRecd.setCostPex(rs.getBoolean("cr_cost_pex"));
//					costRecd.setCrCurRecord(rs.getString("cr_cur_record"));
//					costRecd.setCrCostTime(rs.getString("cr_cost_time"));
					
					costRecd.setCostNo(rs.getInt("cr_cost_no"));
					costRecd.setSchNo(rs.getInt("sch_no"));
					costRecd.setSchName(rs.getString("sch_name"));
					costRecd.setCostType(rs.getByte("cr_cost_type"));
					costRecd.setCostPrice(rs.getDouble("cr_cost_price"));
					costRecd.setPaidBy(rs.getInt("cr_paid_by"));
					costRecd.setPaidByName(rs.getString("mem_name"));
					costRecd.setCostPex(rs.getBoolean("cr_cost_pex"));
					costRecd.setCrCurRecord(rs.getString("cr_cur_record"));
					costRecd.setSchCur(rs.getString("sch_cur"));
					costRecd.setCrCur(rs.getString("cr_cur"));
					costRecd.setCrCostTime(rs.getString("cr_cost_time"));
					
					System.out.println("Data found: " + costRecd); // 日誌輸出
					return costRecd;
				} else {
					System.out.println("No data found for costNo: " + costno); // 無資料提示
				}

			} catch (Exception e) {
				System.err.println("Database error: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer costNo) {
		String sql = "delete from cost_recd where cr_cost_no=?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, costNo);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	
	

	@Override
	public List<Crew> findcrew(Integer schNo) throws Exception {
		Crew crew ;
//		String sql = "select * from crew c join member m  where sch_no = ? ";
		String sql = "select * from crew c join member m on c.mem_no = m.mem_no where c.sch_no = ?";
		

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
		
			pstmt.setInt(1, schNo);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Crew> list = new ArrayList<Crew>();
				
				while (rs.next()) {
					crew = new Crew();
//					crew.setCrewNo(rs.getInt("crew_no"));
					crew.setSchNo(rs.getInt("sch_no"));
					crew.setCrewName(rs.getString("crew_name"));
					crew.setMemNo(rs.getInt("mem_no"));
//					crew.setMemName(rs.getString("mem_name"));
					crew.setMemIcon(rs.getString("mem_icon"));
					list.add(crew);
				} 
				return list;
			} catch (Exception e) {
				System.err.println("Database error: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	
	
	@Override
	public Integer crewCount(Integer schNo) {
		String sql = "SELECT COUNT(*) total_count FROM crew WHERE sch_no = ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);

		) {
			pstmt.setInt(1, schNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("count--DAO--"+rs.getInt("total_count"));
					return rs.getInt("total_count"); // 取出 total_count 的值
				} else {
					// 沒有結果，就返回0或拋出錯誤。
					return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
//	@Override
//	public Integer crewCount(Crew crew) {
//		//查詢整個表
//		String sql  = "SELECT COUNT(*) FROM crew";
//
//		try(Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();) {
//			
//			if (rs.next()) {
//				// 取出第一欄的值，即 COUNT(*) 的結果(算出整個表有幾筆資料)
//				System.out.println("算有多少人：" + rs.getInt(1));
//				return rs.getInt(1);
//			}else {
//				// 沒有結果，就返回0或拋出錯誤。
//				return 0;
//			}
//		} catch (Exception e) {
//			// 處理 SQL 異常，例如預設記錄錯誤、拋出自定義錯誤。
//			e.printStackTrace();
//		}
//		return -1;
//
//	}

}
