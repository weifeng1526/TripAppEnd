package web.spending.vo;

import java.sql.Timestamp;

import lombok.Data;

//資料庫是底線但java用駝峰
//方法名(小)、屬性名（小）、類別名（大）
@Data
public class CostRecd {
	
//	INSERT INTO cost_recd (
//		    cr_cost_no, sch_no, dst_no, cr_cost_type, cr_cost_item, cr_cost_price, cr_paid_by, cr_cost_time, cr_create_time, cr_cost_loc, cr_cost_pex, cr_cur
//		) VALUES
	
	
	
	// 消費紀錄編號
	private Integer costNo;
	// 行程編號
	private Integer schNo;
	// 行程名稱
	private String schName;
	// 消費類別
	private Byte costType;	
	// 消費項目
	private String costItem;	
	// 消費金額
	private Double costPrice;
	// 付款人
	private Integer paidBy;
	// 會員名稱
	private String paidByName;
	// 公費支出
	private Boolean costPex;
	// 紀錄幣別
	private String crCurRecord;
	
	
	
	
	
	
	
	
	
//	// 消費紀錄編號
//	private Integer costNo;
//	// 行程編號
//	private Integer schNo;
//	// 行程明細編號
//	private Integer dstNo;
//	// 行程名稱
//	private String schName;
//	// 消費類別
//	private Byte costType;	
//	// 消費金額
//	private Double costPrice;
//	// 付款人
//	private Integer paidBy;
//	// 會員名稱
//	private String paidByName;
//	// 消費時間
//	private Timestamp costTime;
//	// 公費支出
//	private Boolean costPex;
//	// 紀錄幣別
//	private String crCurRecord;

	
	
	
	
	
//	// 所有 Table 資料
//	// 消費紀錄編號
//	private Integer crCostNo;
//	// 行程編號
//	private Integer schNo;
//	// 行程明細編號
//	private Integer dstNo;
//	// 消費類別
//	private Byte crCostType;
//	// 消費項目
//	private String crCostItem;
//	// 消費金額
//	private Double crCostPrice;
//	// 付款人
//	private Integer crPaidBy;
//	// 消費時間
//	private Timestamp crCostTime;
//	// 建立時間
//	private Timestamp crCreateTime;
//	// 消費地點
//	private String crCostLoc;
//	// 公費支出
//	private Double crCostPex;
//	// 結算幣別
//	private String crCur;

}
