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

import web.sched.dao.impl.DestDaoImpl;
import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;

@WebServlet("/sch/dest/copy")
public class DestOfSampleCopyController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		int getSchNo = Integer.parseInt(req.getParameter("schNo"));
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			Boolean isCopy = destDaoImpl.insertByCopy(getSchNo);
			if (isCopy) {
				System.out.printf("GET: sch's %d dest Copy ok", getSchNo);
				System.out.println();
			}
			else {
				System.out.printf("GET: sch's %d dest Copy fail %d", getSchNo);
				System.out.println();
			}
			resp.getWriter().write(isCopy.toString());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
