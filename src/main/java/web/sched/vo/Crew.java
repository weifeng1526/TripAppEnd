package web.sched.vo;

import lombok.Data;

@Data
public class Crew {
    private int crewNo;        // 主鍵，自增
    private int schNo;         // 排程編號
    private int memNo;         // 成員編號
    private byte crewPeri;     // 周期
    private byte crewIde;      // 身份標誌
    private String crewName;   // 名稱，長度最多10
    private byte crewInvited;  // 是否已邀請
}