package web.sched.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Sched {
    private int schNo;               
    private int memNo;
    private int schState;            
    private String schName;          
    private String schCon;           
    private String schStart;           
    private String schEnd;             
    private String schCur;           
    private byte[] schPic;
    private String schLastEdit;
}

