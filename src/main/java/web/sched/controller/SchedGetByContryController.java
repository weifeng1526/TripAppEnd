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

import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;

@WebServlet("/sched/get_all/sch_con")
public class SchedGetByContryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String getName = req.getParameter("name");
		SchedDaoImpl schedDaoImpl;
		List<Sched> list = new ArrayList<>();
		try {
			schedDaoImpl = new SchedDaoImpl();
			list = schedDaoImpl.selectByContry(getName);
			if (list != null && !list.isEmpty())
				resp.getWriter().write(gson.toJson(list));
			else {
				resp.getWriter().write(gson.toJson(getName));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
