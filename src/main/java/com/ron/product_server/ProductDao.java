package com.ron.product_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDao implements PDao<Product> {
	private DataSource dataSource;

	public ProductDao() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insert(Product product) throws SQLException {
		String sql = "insert into product (prodNo, prodName, prodDesc, prodPrice, prodSta, prodPic) values(?, ?, ?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, product.getProdNo());
			ps.setString(2, product.getProdName());
			ps.setString(3, product.getProdDesc());
			ps.setInt(4, product.getProdPrice());
			ps.setBoolean(5, product.getProdSta());
			ps.setString(4, product.getProdPic());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(Product product) throws SQLException {
		String sql = "update product set prod_name = ?, prod_desc = ?, prod_price = ?, prod_sta = ?, prod_pic = ? where prod_no = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, product.getProdNo());
			ps.setString(2, product.getProdName());
			ps.setString(3, product.getProdDesc());
			ps.setInt(4, product.getProdPrice());
			ps.setBoolean(5, product.getProdSta());
			ps.setString(4, product.getProdPic());
			ps.executeUpdate();
		}
	}

	@Override
	public void deleteBy(int prod_no) throws SQLException {
		String sql = "delete from product where prod_no = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, prod_no);
			ps.executeUpdate();
		}
	}

	@Override
	public Product findBy(int prodNo) throws SQLException {
		String sql = "select prod_name = ?, prod_desc = ?, prod_price = ?, prod_sta = ?, prod_pic = ? where prod_no = ?;";
		Product product = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, prodNo);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String prodName  = rs.getString(1);
				String prodDesc = rs.getString(2);
				int prodPrice = rs.getInt(3);
				Boolean prodSta = rs.getBoolean(4);
				String prodPic = rs.getString(5);
				product = new Product(prodNo, prodName, prodDesc, prodPrice, prodSta, prodPic);
			}
		}
		return product;
	}

	@Override
	public List<Product> findAll() throws SQLException {
		String sql = "select prod_no, prod_name, prod_desc, prod_price, prod_sta, prod_pic from product;";
		List<Product> products = new ArrayList<Product>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int prodNo = rs.getInt(1);
				String prodName  = rs.getString(2);
				String prodDesc = rs.getString(3);
				int prodPrice = rs.getInt(4);
				Boolean prodSta = rs.getBoolean(5);
				String prodPic = rs.getString(6);
				Product product = new Product(prodNo, prodName, prodDesc, prodPrice, prodSta, prodPic);
				products.add(product);
			}
		} 
		return products;
	}
}