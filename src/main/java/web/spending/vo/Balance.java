package web.spending.vo;

import lombok.Data;

@Data
public class Balance {
	// 消費紀錄編號
	private Integer cost_no;
	// 行程編號
	private Integer sch_no;
	// 會員編號
	private Integer mem_no;
	// 每人消費紀錄
	private Double cr_cost_splip;

}
