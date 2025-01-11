package com.ron.product_server;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {
    private Integer ordNo;         // 訂單編號
    private Integer memNo;         // 會員編號
    private Integer prodNo;        // 產品編號
    private String prodName;       // 產品名稱
    private Double prodPrice;      // 產品價格
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // 指定日期時間格式
    private LocalDateTime ordDt;   // 訂單日期
    private String cardNo;         // 信用卡號
    private String expDate;        // 信用卡到期日
    private String cvv;            // 信用卡 CVV
    private Boolean isSubmitted;   // 是否已提交
    private String prodPic;        // 產品圖片 (以 byte[] 形式表示 BLOB 資料)
    
    // 無參數構造函數
    public Order() {
    }

    // 建構子
    public Order(Integer ordNo, Integer memNo, Integer prodNo, String prodName, Double prodPrice, 
                 LocalDateTime ordDt, String cardNo, String expDate, String cvv, Boolean isSubmitted, String prodPic) {
        this.ordNo = ordNo;
        this.memNo = memNo;
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.ordDt = ordDt;
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.cvv = cvv;
        this.isSubmitted = isSubmitted;
        this.prodPic = prodPic;
    }

    // Getter 和 Setter 方法
    public Integer getOrdNo() { 
        return ordNo; 
    }

    public void setOrdNo(Integer ordNo) { 
        this.ordNo = ordNo; 
    }

    public Integer getMemNo() { 
        return memNo; 
    }

    public void setMemNo(Integer memNo) { 
        this.memNo = memNo; 
    }

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

    public Double getProdPrice() { 
        return prodPrice; 
    }

    public void setProdPrice(Double prodPrice) { 
        this.prodPrice = prodPrice; 
    }

    public LocalDateTime getOrdDt() { 
        return ordDt; 
    }

    public void setOrdDt(LocalDateTime ordDt) { 
        this.ordDt = ordDt; 
    }

    public String getCardNo() { 
        return cardNo; 
    }

    public void setCardNo(String cardNo) { 
        this.cardNo = cardNo; 
    }

    public String getExpDate() { 
        return expDate; 
    }

    public void setExpDate(String expDate) { 
        this.expDate = expDate; 
    }

    public String getCvv() { 
        return cvv; 
    }

    public void setCvv(String cvv) { 
        this.cvv = cvv; 
    }

    public Boolean getIsSubmitted() { 
        return isSubmitted; 
    }

    public void setIsSubmitted(Boolean isSubmitted) { 
        this.isSubmitted = isSubmitted; 
    }

    public String getProdPic() {
        return prodPic;
    }

    public void setProdPic(String prodPic) {
        this.prodPic = prodPic;
    }
}