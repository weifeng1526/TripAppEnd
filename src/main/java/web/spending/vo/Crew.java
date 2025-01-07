package web.spending.vo;

import lombok.Data;

@Data
public class Crew {

	// 群組會員明細編號
	private Integer crewNo;
	// 行程編號
	private Integer schNo;
	// 會員編號
	private Integer memNo;
	// 會員權限
	private Byte crewPeri;
	// 群組身分(會員)
	private Byte crewIde;
	// 群組名稱
	private String crewName;
	// 會員回應邀請狀態
	private Byte crewInvited;

}
