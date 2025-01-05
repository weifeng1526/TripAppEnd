package web.member.vo;

import lombok.Data;

@Data
public class TripMember {
	
	private Integer memNo; // 會員編號
    private String memEmail; // 會員電子郵件
    private String memName; // 會員名稱
    private String memPw; // 會員密碼
    private Byte memSta; // 會員狀態
    private String memIcon; // 會員圖標

}
