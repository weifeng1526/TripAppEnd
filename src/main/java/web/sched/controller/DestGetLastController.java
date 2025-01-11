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

import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Dest;

@WebServlet("/sched/dest/get_last")
public class DestGetLastController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		DestDaoImpl destdaoImpl;
		try {
			destdaoImpl = new DestDaoImpl();
			Dest dest = destdaoImpl.selectLastOne();
			if (dest != null)
				resp.getWriter().write(gson.toJson(dest));
			else {
				System.out.println("n");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
}
