package web.member.service;

import web.member.vo.TripMember;

public interface TripMemberService {
	
	String signup(TripMember member);

	TripMember login(TripMember member);

}
