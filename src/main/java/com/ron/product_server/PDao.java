package com.ron.product_server;

import java.util.List;

public interface PDao<T> {
	void insert(T item) throws Exception;

	void update(T item) throws Exception;

	void deleteBy(int prod_no) throws Exception;

	T findBy(int prodNo) throws Exception;

	List<T> findAll() throws Exception;

}
