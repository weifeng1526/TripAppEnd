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

import web.sched.dao.CrewDao;
import web.sched.dao.impl.CrewDaoImpl;
import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Crew;
import web.sched.vo.Dest;

@WebServlet("/sched/crew/create")
public class CrewCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		try {
			CrewDaoImpl crewDaoImpl = new CrewDaoImpl();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Crew crew = gson.fromJson(req.getReader(), Crew.class);
			int isInserted = crewDaoImpl.insert(crew);
			if (isInserted > 0) {
				System.out.println("POST: Dest創建成功: " + isInserted);
			} else {
				System.out.println("POST: Dest創建失敗");
			}
			resp.getWriter().write(gson.toJson(crew));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
