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

import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;

@WebServlet("/sched/getLastId")
public class SchedGetLastIdController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		String getMemId = req.getParameter("memId");
		Integer lastSchId = null;
		SchedDaoImpl schedDaoImpl;
		try {
			schedDaoImpl = new SchedDaoImpl();
			lastSchId = schedDaoImpl.selectLastSchId(Integer.parseInt(getMemId));
			resp.getWriter().write(lastSchId);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
