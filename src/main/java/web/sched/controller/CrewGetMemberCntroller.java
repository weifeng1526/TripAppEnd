package web.sched.controller;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.sched.dao.impl.CrewDaoImpl;
import web.sched.vo.MemberInCrew;

@WebServlet("/sched/memberInCrew/getBySchId")
public class CrewGetMemberCntroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		CrewDaoImpl crewDaoImpl;
		List<MemberInCrew> list = new ArrayList<MemberInCrew>();
		int schId = Integer.parseInt(req.getParameter("schId"));
		try {
			crewDaoImpl = new CrewDaoImpl();
			list = crewDaoImpl.selectMemberInCrew(schId);
			if (list != null && !list.isEmpty()) {
				System.out.printf("GET member in crew: ok");
				System.out.println();
			} else {
				System.out.printf("GET member in crew: by schId fail %d", schId);
				System.out.println();
			}
			resp.getWriter().write(gson.toJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}