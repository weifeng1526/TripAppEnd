package web.sched.vo;

import lombok.Data;

@Data
public class Crew {
    private int crewNo;       
    private int schNo;         
    private int memNo;         
    private byte crewPeri;     
    private byte crewIde;      
    private String crewName;   
    private byte crewInvited;  
}