package web.notes.vo;

import lombok.Data;

@Data
public class Notes {
	    private Integer drNo;          // 主鍵，自動增長
	    private Integer memNo;
	    private Integer dstNo;         // 目標編號
	    private String drText;     // 描述文字
}
