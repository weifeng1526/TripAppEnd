package web.sched.dao;

import java.util.List;

import web.member.vo.TripMember;
import web.sched.vo.Crew;
import web.sched.vo.MemberInCrew;

public interface CrewDao {
	int insert(Crew crew);
	List<Crew> selectBySchId(Integer id);
	List<MemberInCrew> selectMemberInCrew(Integer id);
	List<TripMember> selectMembers();
	int deleteByMemId(Integer id);
}
