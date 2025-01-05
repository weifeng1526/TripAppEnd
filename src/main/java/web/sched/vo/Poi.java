package web.sched.vo;

import java.math.BigDecimal;
import java.sql.Time;

public class Poi {
    private Integer poiNo;          // 景點編號
    private String poiAdd;      // 景點地址
    private String poiName;     // 景點名稱
    private BigDecimal poiLng;  // 經度
    private BigDecimal poiLat;  // 緯度
    private String poiLab;      // 景點標籤
    private String poiPic;      // 景點圖片路徑
    private Double poiRat;      // 評分
    private String poiHno;      // 景點電話
    private String poiPhon;     // 景點手機
    private Time poiBs;         // 開店時間
    private Time poiNbs;        // 關店時間
    private String poiBd;       // 景點描述
    private Integer poiLike;        // 收藏數量

    // Getter 和 Setter
    public int getPoiNo() {
        return poiNo;
    }

    public void setPoiNo(int poiNo) {
        this.poiNo = poiNo;
    }

    public String getPoiAdd() {
        return poiAdd;
    }

    public void setPoiAdd(String poiAdd) {
        this.poiAdd = poiAdd;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public BigDecimal getPoiLng() {
        return poiLng;
    }

    public void setPoiLng(BigDecimal poiLng) {
        this.poiLng = poiLng;
    }

    public BigDecimal getPoiLat() {
        return poiLat;
    }

    public void setPoiLat(BigDecimal poiLat) {
        this.poiLat = poiLat;
    }

    public String getPoiLab() {
        return poiLab;
    }

    public void setPoiLab(String poiLab) {
        this.poiLab = poiLab;
    }

    public String getPoiPic() {
        return poiPic;
    }

    public void setPoiPic(String poiPic) {
        this.poiPic = poiPic;
    }

    public double getPoiRat() {
        return poiRat;
    }

    public void setPoiRat(double poiRat) {
        this.poiRat = poiRat;
    }

    public String getPoiHno() {
        return poiHno;
    }

    public void setPoiHno(String poiHno) {
        this.poiHno = poiHno;
    }

    public String getPoiPhon() {
        return poiPhon;
    }

    public void setPoiPhon(String poiPhon) {
        this.poiPhon = poiPhon;
    }

    public Time getPoiBs() {
        return poiBs;
    }

    public void setPoiBs(Time poiBs) {
        this.poiBs = poiBs;
    }

    public Time getPoiNbs() {
        return poiNbs;
    }

    public void setPoiNbs(Time poiNbs) {
        this.poiNbs = poiNbs;
    }

    public String getPoiBd() {
        return poiBd;
    }

    public void setPoiBd(String poiBd) {
        this.poiBd = poiBd;
    }

    public int getPoiLike() {
        return poiLike;
    }

    public void setPoiLike(int poiLike) {
        this.poiLike = poiLike;
    }
}
