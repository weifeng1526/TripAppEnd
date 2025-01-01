package web.member.service.impl;

import javax.naming.NamingException;

import web.member.dao.TripMemberDao;
import web.member.dao.impl.TripMemberDaoImpl;
import web.member.service.TripMemberService;
import web.member.vo.TripMember;

public class TripMemberServiceImpl implements TripMemberService{
	private TripMemberDao memberDao;
	
	public TripMemberServiceImpl() throws NamingException{
		memberDao = new TripMemberDaoImpl();
	}
	
	@Override
	public String signup(TripMember member) {
		
		String nickname = member.getMemName();
		if (nickname == null || nickname.length()<1 || nickname.length()>30) {
			return "密碼長度必須在1至30個字元之間";
		}
		
		String email = member.getMemEmail();
		if (email == null || email.trim().isEmpty()) {
			return "電子信箱不可為空";
		}
		
		String check = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+(\\.[a-zA-Z]{2,})?$";
		if (!email.matches(check)) {
			return "電子信箱格式不正確";
		}
		
		String password = member.getMemPw();
		if (password == null || password.length()<6 || password.length()>20) {
			return "密碼長度必須在6至20個字元之間";
		}

		if (memberDao.selectByEmail(email) != null) {
			return "此信箱已被註冊";
		}
		
		int count = memberDao.save(member);
		if (count > 0) {
			return null;
		} else {
			return "註冊失敗";			
		}
	}

}
