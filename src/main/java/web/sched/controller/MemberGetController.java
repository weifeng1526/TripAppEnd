package web.sched.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.vo.TripMember;
import web.sched.dao.impl.CrewDaoImpl;
import web.sched.dao.impl.PoiDaoImpl;
import web.sched.vo.Poi;

@WebServlet("/sched/member/get_all")
public class MemberGetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<TripMember> list = new ArrayList<>();
		CrewDaoImpl crewDaoImpl;
		try {
			crewDaoImpl = new CrewDaoImpl();
			list = crewDaoImpl.selectMembers();
			if(!list.isEmpty()) {
				System.out.printf("GET: member表總共%d筆資料\r\n", list.size());
			} else {
				System.out.println("GET: member表沒有資料\r\n");
			}
			resp.getWriter().write(gson.toJson(list));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}