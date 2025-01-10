package web.sched.vo;

import lombok.Data;

@Data
public class MemberInCrew {
	private int crewNo;
	private int schNo;
	private int memNo;
	private byte[] memIcon;
	private String memName;
	private byte crewPeri;
	private byte crewIde;
	private String crewName;
	private byte crewInvited;
}