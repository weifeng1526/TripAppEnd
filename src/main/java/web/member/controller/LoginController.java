package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.member.service.TripMemberService;
import web.member.service.impl.TripMemberServiceImpl;
import web.member.vo.MemberResult;
import web.member.vo.TripMember;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		TripMember member = gson.fromJson(req.getReader(), TripMember.class);
		MemberResult result = new MemberResult();
		System.out.println("doPost");
		
		// 移除 Session 功能
		// 拿帳號密碼,找到用戶的資料

		if (member == null || member.getMemEmail() == null || member.getMemPw() == null) {
			System.out.println("null");
			result.setMessage("請填寫信箱密碼");
			result.setSucess(false);
		} else {
			try {
				TripMemberService sevice = new TripMemberServiceImpl();
				member = sevice.login(member);
				if (member != null) {
					System.out.println("not Null");
					System.out.println(member);
					result.setSucess(true);
					resp.getWriter().write(gson.toJson(member));
				} else {
					System.out.println("信箱或密碼錯誤");
					result.setSucess(false);
					result.setMessage("信箱或密碼錯誤");
					String json = gson.toJson(result);
					resp.getWriter().write(json);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
