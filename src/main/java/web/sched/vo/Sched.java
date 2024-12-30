package web.sched.vo;

import java.sql.Date;

public class Sched {
    private int schNo;               
    private int memNo;
    private int schState;            
    private String schName;          
    private String schCon;           
    private Date schStart;           
    private Date schEnd;             
    private String schCur;           
    private byte[] schPic;

    // Getter & Setter
    public int getSchNo() {
        return schNo;
    }

    public void setSchNo(int schNo) {
        this.schNo = schNo;
    }

    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public int getSchState() {
        return schState;
    }

    public void setSchState(int schState) {
        this.schState = schState;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getSchCon() {
        return schCon;
    }

    public void setSchCon(String schCon) {
        this.schCon = schCon;
    }

    public Date getSchStart() {
        return schStart;
    }

    public void setSchStart(Date schStart) {
        this.schStart = schStart;
    }

    public Date getSchEnd() {
        return schEnd;
    }

    public void setSchEnd(Date schEnd) {
        this.schEnd = schEnd;
    }

    public String getSchCur() {
        return schCur;
    }

    public void setSchCur(String schCur) {
        this.schCur = schCur;
    }

    public byte[] getSchPic() {
        return schPic;
    }

    public void setSchPic(byte[] schPic) {
        this.schPic = schPic;
    }
}

