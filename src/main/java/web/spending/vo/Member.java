package web.spending.vo;

import lombok.Data;

@Data
public class Member {

	// 會員編號
	private Integer memNo;
	// 會員信箱
	private String memEmail;
	// 會員暱稱
	private String memName;
	// 會員密碼
	private String memPw;
	// 會員狀態
	private Byte memSta;
	// 會員頭像
	private String memIcon;

}
