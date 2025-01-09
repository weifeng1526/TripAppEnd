package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.member.service.TripMemberService;
import web.member.service.impl.TripMemberServiceImpl;
import web.member.vo.MemberResult;
import web.member.vo.TripMember;

@WebServlet("/member/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		TripMember member = gson.fromJson(req.getReader(), TripMember.class);
		
		MemberResult result = new MemberResult();
		System.out.println("doPost");

		if (member == null) {
			System.out.println("請將會員資料填寫完整");
			result.setMessage("請將會員資料填寫完整");
			result.setSucess(false);
		} else {
			try {
				TripMemberService sevice = new TripMemberServiceImpl();
				// 建立用戶
				// 回傳用戶資料，查到用戶資料
				System.out.println(member);
				String message = sevice.signup(member);
				result.setMessage(message);
//				System.out.println(message);
//				result.setSucess(message == null);
				resp.getWriter().write(gson.toJson(message));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String json = gson.toJson(result);
			System.out.println(member);
//			resp.getWriter().write(gson.toJson(member));
		}
	}
}
