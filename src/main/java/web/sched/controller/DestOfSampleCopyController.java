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
		int getSchId = Integer.parseInt(req.getParameter("schId"));
		int getSchIdOfSample= Integer.parseInt(req.getParameter("schSampleId"));
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			boolean isCopy = destDaoImpl.insertByCopy(getSchId, getSchIdOfSample);
			if (isCopy) {
				System.out.printf("GET: sch's dest %d Copy ok", getSchId);
				System.out.println();
			}
			else {
				System.out.printf("GET: sch's dest %d Copy fail %d", getSchId);
				System.out.println();
			}
			resp.getWriter().write(Boolean.toString(isCopy));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
