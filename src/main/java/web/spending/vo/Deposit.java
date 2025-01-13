package web.spending.vo;

import lombok.Data;

@Data
public class Deposit {
	// 行程編號
	private Integer schNo;
	// 儲值紀錄編號
	private Integer dpNo;
	// 公費儲值紀錄
	private Double dpRecord;
}
