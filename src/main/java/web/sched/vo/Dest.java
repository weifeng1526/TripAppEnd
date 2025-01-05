package web.sched.vo;

public class Dest {
    private int dstNo;       // 景點編號
    private int schNo;       // 行程編號
    private int poiNo;       // 地點編號
    private String dstName;  // 景點名稱
    private String dstAddr;  // 景點地址
    private byte[] dstPic;   // 景點圖片
    private String dstDep;   // 描述
    private String dstDate;  // 日期
    private String dstStart; // 開始時間
    private String dstEnd;   // 結束時間
    private String dstInr;     // 間隔時間

    // Getter and Setter methods
    public int getDstNo() {
        return dstNo;
    }

    public void setDstNo(int dstNo) {
        this.dstNo = dstNo;
    }

    public int getSchNo() {
        return schNo;
    }

    public void setSchNo(int schNo) {
        this.schNo = schNo;
    }

    public int getPoiNo() {
        return poiNo;
    }

    public void setPoiNo(int poiNo) {
        this.poiNo = poiNo;
    }

    public String getDstName() {
        return dstName;
    }

    public void setDstName(String dstName) {
        this.dstName = dstName;
    }

    public String getDstAddr() {
        return dstAddr;
    }

    public void setDstAddr(String dstAddr) {
        this.dstAddr = dstAddr;
    }

    public byte[] getDstPic() {
        return dstPic;
    }

    public void setDstPic(byte[] dstPic) {
        this.dstPic = dstPic;
    }

    public String getDstDep() {
        return dstDep;
    }

    public void setDstDep(String dstDep) {
        this.dstDep = dstDep;
    }

    public String getDstDate() {
        return dstDate;
    }

    public void setDstDate(String dstDate) {
        this.dstDate = dstDate;
    }

    public String getDstStart() {
        return dstStart;
    }

    public void setDstStart(String dstStart) {
        this.dstStart = dstStart;
    }

    public String getDstEnd() {
        return dstEnd;
    }

    public void setDstEnd(String dstEnd) {
        this.dstEnd = dstEnd;
    }

	public String getDstInr() {
		return dstInr;
	}

	public void setDstInr(String dstInr) {
		this.dstInr = dstInr;
	}

}
