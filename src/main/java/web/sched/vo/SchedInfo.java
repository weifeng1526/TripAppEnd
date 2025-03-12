package web.sched.vo;

import lombok.Data;

@Data
public class SchedInfo {
    private int schNo;               
    private int memNo;
    private int schState;            
    private String schName;          
    private String schCon;           
    private Long schStart;           
    private Long schEnd;             
    private String schCur;
    private Long schLastEdit;
}
