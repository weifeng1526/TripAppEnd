package web.member.vo;

public class TripMember {
	
	private Integer memNo; // 會員編號
    private String memEmail; // 會員電子郵件
    private String memName; // 會員名稱
    private String memPw; // 會員密碼
    private Byte memSta; // 會員狀態
    private byte[] memIcon; // 會員圖標

    // Getter and Setter methods
    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemPw() {
        return memPw;
    }

    public void setMemPw(String memPw) {
        this.memPw = memPw;
    }

    public Byte getMemSta() {
        return memSta;
    }

    public void setMemSta(Byte memSta) {
        this.memSta = memSta;
    }

    public byte[] getMemIcon() {
        return memIcon;
    }

    public void setMemIcon(byte[] memIcon) {
        this.memIcon = memIcon;
    }

}
