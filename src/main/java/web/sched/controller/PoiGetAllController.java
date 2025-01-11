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

import web.sched.dao.impl.PoiDaoImpl;
import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Poi;
import web.sched.vo.Sched;

@WebServlet("/sched/poi/get_all")
public class PoiGetAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Poi> allPoi = new ArrayList<>();
		PoiDaoImpl poiDaoImpl;
		try {
			poiDaoImpl = new PoiDaoImpl();
			allPoi = poiDaoImpl.selectAll();
			if(!allPoi.isEmpty()) {
				System.out.printf("GET: allPoi表總共%d筆資料\r\n", allPoi.size());
			} else {
				System.out.println("GET: allPoi表沒有資料\r\n");
			}
			resp.getWriter().write(gson.toJson(allPoi));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
