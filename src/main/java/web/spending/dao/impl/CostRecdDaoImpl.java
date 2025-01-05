package web.spending.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.spending.dao.CostRecdDao;
import web.spending.vo.CostRecd;

public class CostRecdDaoImpl implements CostRecdDao {
	private DataSource ds;

	public CostRecdDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
	}

	@Override
	public List<CostRecd> findData() {
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
					list.add(costRecd);
				}

				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	//如果要把資料抓過來，那就要先查詢 Select，多表格就用join

	@Override
	public int insert(CostRecd costRecd) {
		String sql = "insert into cost_recd(sch_no, cr_cur_record, cr_cost_price, cr_paid_by, cr_cost_type, cr_cost_item, cr_cost_pex) value(?,?,?,?,?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)

		) {
			pstmt.setInt(1, costRecd.getSchNo());
			pstmt.setString(2, costRecd.getCrCurRecord());
			pstmt.setDouble(3, costRecd.getCostPrice());
			pstmt.setInt(4, costRecd.getPaidBy());
			pstmt.setByte(5, costRecd.getCostType());
			pstmt.setString(6, costRecd.getCostItem());
			pstmt.setBoolean(7, costRecd.getCostPex());
			
		
			//執行
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(CostRecd costRecd) {
		
		//要下條件（where），在寫資料的時候也要去思考使用流程，所需要的資料是什麼！資料的意義。
		String sql = "update cost_recd set cr_cur_record= ?, cr_cost_price= ?, cr_paid_by= ?, cr_cost_type= ?, cr_cost_item= ?, cr_cost_time= ?, cr_cost_pex= ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)

		) {
			pstmt.setString(1, costRecd.getCrCurRecord());
			pstmt.setDouble(2, costRecd.getCostPrice());
			pstmt.setString(3, costRecd.getPaidByName());
			pstmt.setByte(4, costRecd.getCostType());
			pstmt.setByte(5, costRecd.getCostType());
//			pstmt.setTimestamp(6, costRecd.getCostTime());
			pstmt.setBoolean(6, costRecd.getCostPex());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
