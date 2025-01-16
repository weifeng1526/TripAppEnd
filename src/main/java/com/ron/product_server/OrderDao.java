package com.ron.product_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDao implements ODao<Order> {
	private DataSource dataSource;

	public OrderDao() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static List<Order> orders = new ArrayList<>();

	@Override
	public int insert(Order order) throws SQLException {
		String sql = "INSERT INTO orders (mem_no, prod_no, prod_name, prod_price, ord_dt, card_no, exp_date, cvv, is_submitted, prod_pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, order.getMemNo());
			ps.setInt(2, order.getProdNo());
			ps.setString(3, order.getProdName());
			ps.setDouble(4, order.getProdPrice());
			ps.setTimestamp(5, Timestamp.valueOf(order.getOrdDt()));
			ps.setString(6, order.getCardNo());
			ps.setString(7, order.getExpDate());
			ps.setString(8, order.getCvv());
			ps.setBoolean(9, order.getIsSubmitted());
			ps.setString(10, order.getProdPic());

			int affectedRows = ps.executeUpdate(); // 取得受影響的列數
			
			System.out.println("affectedRows: " + affectedRows);

			if (affectedRows > 0) {
				// 獲取自動生成的訂單號
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
	                    System.out.println("generatedId: " + generatedId);
						return generatedId; // 返回生成的訂單編號
					} else {
						throw new SQLException("Failed to retrieve generated order ID.");
					}
				}
			} else {
				throw new SQLException("Failed to insert order, no rows affected."); // 插入失敗
			}
		}
	}

	// 更新訂單
	@Override
	public void update(Order order) throws SQLException {
		String sql = "UPDATE orders SET mem_no = ?, prod_no = ?, prod_name = ?, ord_dt = ?, prod_price = ?, card_no = ?, exp_date = ?, cvv = ?, is_submitted = ?, prod_pic = ? WHERE ord_no = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, order.getMemNo());
			ps.setInt(2, order.getProdNo());
			ps.setString(3, order.getProdName());
			ps.setTimestamp(4, Timestamp.valueOf(order.getOrdDt()));
			ps.setDouble(5, order.getProdPrice());
			ps.setString(6, order.getCardNo());
			ps.setString(7, order.getExpDate());
			ps.setString(8, order.getCvv());
			ps.setBoolean(9, order.getIsSubmitted());
			ps.setString(10, order.getProdPic());
			ps.setInt(11, order.getOrdNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 根據訂單編號刪除訂單
	@Override
	public void deleteBy(int ordNo) throws SQLException {
		String sql = "DELETE FROM orders WHERE ord_no = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, ordNo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 根據訂單編號查找訂單
	@Override
	public Order findBy(int ordNo) throws SQLException {
		String sql = "SELECT ord_no, mem_no, prod_no, prod_name, ord_dt, prod_price, card_no, exp_date, cvv, is_submitted, prod_pic FROM orders WHERE ord_no = ?;";
		Order order = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, ordNo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int memNo = rs.getInt("mem_no");
				int prodNo = rs.getInt("prod_no");
				String prodName = rs.getString("prod_name");
				Timestamp ordDt = rs.getTimestamp("ord_dt");
				double prodPrice = rs.getDouble("prod_price");
				String cardNo = rs.getString("card_no");
				String expDate = rs.getString("exp_date");
				String cvv = rs.getString("cvv");
				boolean isSubmitted = rs.getBoolean("is_submitted");
				String prodPic = rs.getString("prod_pic");
				order = new Order(ordNo, memNo, prodNo, prodName, prodPrice, ordDt.toLocalDateTime(), cardNo, expDate,
						cvv, isSubmitted, prodPic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return order;
	}
	
	// 根據會員編號查找訂單
	public List<Order> findByMemberId(int memNo) throws SQLException {
	    String sql = "SELECT ord_no, mem_no, prod_no, prod_name, ord_dt, prod_price, card_no, exp_date, cvv, is_submitted, prod_pic FROM orders WHERE mem_no = ?;";
	    List<Order> orders = new ArrayList<>();
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, memNo);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int ordNo = rs.getInt("ord_no");
	            int prodNo = rs.getInt("prod_no");
	            String prodName = rs.getString("prod_name");
	            Timestamp ordDt = rs.getTimestamp("ord_dt");
	            double prodPrice = rs.getDouble("prod_price");
	            String cardNo = rs.getString("card_no");
	            String expDate = rs.getString("exp_date");
	            String cvv = rs.getString("cvv");
	            boolean isSubmitted = rs.getBoolean("is_submitted");
	            String prodPic = rs.getString("prod_pic");
	            Order order = new Order(ordNo, memNo, prodNo, prodName, prodPrice, ordDt.toLocalDateTime(), cardNo, expDate,
	                    cvv, isSubmitted, prodPic);
	            orders.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	    return orders;
	}

	// 獲取所有訂單
	@Override
	public List<Order> findAll() throws SQLException {
		String sql = "SELECT ord_no, mem_no, prod_no, prod_name, ord_dt, prod_price, card_no, exp_date, cvv, is_submitted, prod_pic FROM orders;";
		List<Order> orders = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ordNo = rs.getInt("ord_no");
				int memNo = rs.getInt("mem_no");
				int prodNo = rs.getInt("prod_no");
				String prodName = rs.getString("prod_name");
				Timestamp ordDt = rs.getTimestamp("ord_dt");
				double prodPrice = rs.getDouble("prod_price");
				String cardNo = rs.getString("card_no");
				String expDate = rs.getString("exp_date");
				String cvv = rs.getString("cvv");
				boolean isSubmitted = rs.getBoolean("is_submitted");
				String prodPic = rs.getString("prod_pic");
				Order order = new Order(ordNo, memNo, prodNo, prodName, prodPrice, ordDt.toLocalDateTime(), cardNo,
						expDate, cvv, isSubmitted, prodPic);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return orders;
	}
}