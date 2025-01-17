package com.ron.product_server;

public class Product {

    private Integer prodNo;     // 產品編號
    private String prodName;    // 產品名稱
    private String prodDesc;    // 產品描述
    private Integer prodPrice;  // 產品價格
    private Boolean prodSta;    // 產品狀態 (使用 Boolean，取代 TINYINT)
    private String prodPic;     // 產品圖片 (以 byte[] 形式表示 BLOB 資料)

    // 無參數建構子
    public Product() {
    }

    // 參數化建構子
    public Product(Integer prodNo, String prodName, String prodDesc, Integer prodPrice, Boolean prodSta, String prodPic) {
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodSta = prodSta;
        this.prodPic = prodPic;
    }

    // Getters 和 Setters
    public Integer getProdNo() {
        return prodNo;
    }

    public void setProdNo(Integer prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Boolean getProdSta() {
        return prodSta;
    }

    public void setProdSta(Boolean prodSta) {
        this.prodSta = prodSta;
    }

    public String getProdPic() {
        return prodPic;
    }

    public void setProdPic(String prodPic) {
        this.prodPic = prodPic;
    }

    // toString 方法方便輸出物件資訊
    @Override
    public String toString() {
        return "Product{" +
                "prodNo=" + prodNo +
                ", prodName='" + prodName + '\'' +
                ", prodDesc='" + prodDesc + '\'' +
                ", prodPrice=" + prodPrice +
                ", prodSta=" + prodSta +
                '}';
    }
}
