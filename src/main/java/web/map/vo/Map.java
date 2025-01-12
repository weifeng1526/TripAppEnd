package web.map.vo;

import java.math.BigDecimal;
import java.sql.Time;

import lombok.Data;
@Data
public class Map {
	private Integer schNo;
	private Integer poiNo;
    private String poiAdd;
    private String poiName;
    private BigDecimal poiLng;
    private BigDecimal poiLat;
    private String poiLab;
    private String poiPic;
    private Double poiRat;
    private String poiHno;
    private String poiPhon;
    private Time poiBs;
    private Time poiNbs;
    private String poiBd;
    private Integer poiLike;
    private String dstDate;
    private String dstStart;
    private String dstEnd;
    private String dstInr;
}
