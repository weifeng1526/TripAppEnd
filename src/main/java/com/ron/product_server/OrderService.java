package com.ron.product_server;

import java.time.LocalDateTime;
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

@Path("/order")
public class OrderService {
    OrderDao orderDao;

    // 一定要有沒參數建構式，否則執行會失敗
    public OrderService() {
        orderDao = new OrderDao();
    }

    // 取得所有訂單資訊
    @GET
    @Path("/all") // 請求的URL為 "/order/all"
    @Produces(MediaType.APPLICATION_JSON) // 輸出資料轉成JSON
    public Response findAll() {
        try {
            List<Order> orders = orderDao.findAll();
            System.out.println("orders.size(): " + orders.size());
            // 沒有訂單
            if (orders.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new Result(0, "No orders available"))
                        .build();
            }
            // 成功取得訂單：回傳OK狀態與訂單資訊
            return Response.ok(orders).build();
        } catch (Exception e) {
            // 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
        }
    }

    // 取得指定訂單編號的訂單資訊
    @GET
    @Path("/{ordno}") // 請求的URL為 "/order/{ordno}"
    @Produces(MediaType.APPLICATION_JSON) // 輸出資料轉成JSON
    public Response findBy(@PathParam("ordno") Integer ordNo) {
        try {
            Order order = orderDao.findBy(ordNo);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new Result(0, "Order not found"))
                        .build();
            }
            return Response.ok(order).build();
        } catch (Exception e) {
            // 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
        }
    }

 // 新增一項訂單
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Order order) {
        try {
            // 確保 ordNo 由後端生成
            order.setOrdNo(null);

            // 如果 ordDt 為空，設置為當前時間
            if (order.getOrdDt() == null) {
                order.setOrdDt(LocalDateTime.now());
            }

            if (order.getIsSubmitted() == null) {
                order.setIsSubmitted(false);
            }

            // 插入訂單並獲取生成的 ordNo
            int generatedId = orderDao.insert(order);
            System.out.println("generatedId: " + generatedId);

            // 確保訂單編號已生成，並回傳成功訊息和訂單編號
            if (generatedId > 0) {
                order.setOrdNo(generatedId); // 設置生成的訂單編號到訂單物件中
                Result result = new Result(1, "Order added successfully", order); // 返回完整的訂單資料
                return Response.ok(result).build(); // 確保返回訂單物件
            } else {
                // 如果插入失敗，回傳錯誤訊息
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new Result(0, "Failed to add order")).build();
            }
        } catch (Exception e) {
            // 錯誤處理，輸出例外訊息
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, "Error adding order: " + e.getMessage()))
                    .build();
        }
    }
    
    // 更新一項訂單
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Order order) {
        try {
            orderDao.update(order);
            return Response.ok(new Result(0, "Order updated successfully")).build();
        } catch (Exception e) {
            // 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
        }
    }

    // 刪除一項訂單
    @DELETE
    @Path("/{ordno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("ordno") Integer ordNo) {
        try {
            orderDao.deleteBy(ordNo);
            return Response.ok(new Result(0, "Order deleted successfully")).build();
        } catch (Exception e) {
            // 處理執行錯誤
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result(0, e.getMessage()))
                    .build();
        }
    }
}