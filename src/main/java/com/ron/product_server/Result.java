package com.ron.product_server;

public class Result {
    private int code;        // 狀態碼
    private String message;  // 訊息
    private Order order;     // 包含完整訂單資料

    // 只有訊息和狀態碼的建構函數
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 包含訂單物件的建構函數
    public Result(int code, String message, Order order) {
        this.code = code;
        this.message = message;
        this.order = order;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
