package web.spending.vo;

import lombok.Data;

@Data
public class Payment {
	// 會員編號
	private Integer memNo;
	// 行程編號
	private Integer schNo;
	// 結算金額
	private Double crTotalSum;

}
