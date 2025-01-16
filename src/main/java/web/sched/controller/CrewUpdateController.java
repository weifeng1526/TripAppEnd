package web.sched.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.sched.dao.impl.CrewDaoImpl;
import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Crew;
import web.sched.vo.Dest;

@WebServlet("/sched/crew/update")
public class CrewUpdateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		CrewDaoImpl crewDaoImpl;
		try {
			crewDaoImpl = new CrewDaoImpl();
			Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Crew crew = gson.fromJson(req.getReader(), Crew.class);
			int isUpdated = crewDaoImpl.update(crew);
			if(isUpdated > 0) {
				System.out.println("PUT: Crew更新成功: " + crew.getCrewNo());
			} else {
				System.out.println("PUT: Crew更新失敗: " + crew.getCrewNo());
			}
			resp.getWriter().write(gson.toJson(crew));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
