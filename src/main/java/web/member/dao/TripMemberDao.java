package web.member.dao;

import web.member.vo.TripMember;

public interface TripMemberDao {
	int save(TripMember member);
	
	TripMember selectByEmail(String email);

	TripMember selectByMemEmailAndMemPw(TripMember member);
}
