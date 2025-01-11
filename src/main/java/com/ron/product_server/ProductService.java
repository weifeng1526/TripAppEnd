package com.ron.product_server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductService {
	ProductDao productDao;

	// 一定要有沒參數建構式，否則執行會失敗
	public ProductService() {
		productDao = new ProductDao();
	}

	@GET // GET請求
	@Path("/all") // 請求的URL為 "/product/all"
	@Produces(MediaType.APPLICATION_JSON) // 輸出資料轉成JSON
	// 取得所有商品資訊
	public Response findAll() {
		try {
			List<Product> products = productDao.findAll();
			System.out.println("products.size(): " + products.size());
			// 沒有商品資訊
			if (products.isEmpty()) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity(new Result(0, "No products available"))
						.build();
			}
			// 成功取得商品：回傳OK狀態與商品資訊
			return Response.ok(products).build();
		} catch (Exception e) {
			// 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
		}
	}

	@GET // GET請求
	@Path("/{prodno}") // 請求的URL為 "/product/prodno"
	@Produces(MediaType.APPLICATION_JSON) // 輸出資料轉成JSON
	// 取得指定ProdNo的商品資訊
	public Response findBy(@PathParam("prodno") Integer prodNo) {
		try {
			Product product = productDao.findBy(prodNo);
			if (product == null) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity(new Result(0, "Product not found"))
						.build();
			}
			return Response.ok(product).build();
		} catch (Exception e) {
			// 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// 新增一項商品，請求的URL為 "/product"
	public Response add(Product product) {
		try {
			productDao.insert(product);
			return Response.ok(new Result(0, "Product addded successfully")).build();
		} catch (Exception e) {
			// 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// 修改一項商品
	public Response update(Product product) {
		try {
			productDao.update(product);
			return Response.ok(new Result(0, "Product updated successfully")).build();
		} catch (Exception e) {
			// 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
		}
	}

	@DELETE
	@Path("/{prodno}")
	@Produces(MediaType.APPLICATION_JSON)
	// 刪除一項商品
	public Response delete(@PathParam("prodno") Integer prodNo) {
		try {
			productDao.deleteBy(prodNo);
			return Response.ok(new Result(0, "Product deleted successfully")).build();
		} catch (Exception e) {
			// 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
		}
	}
}
