package web.sched.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.sched.vo.Sched;

import web.sched.dao.impl.SchedDaoImpl;

@WebServlet("/sched/get_all")
public class SchedGetAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Sched> allSched = new ArrayList<>();
		
		SchedDaoImpl schedDaoImpl = new SchedDaoImpl();
		allSched = schedDaoImpl.selectAll();
	
		if(!allSched.isEmpty()) {
			System.out.printf("GET: Sched表總共%d筆資料\r\n", allSched.size());
		} else {
			System.out.println("GET: Sched表沒有資料\r\n");
		}
		resp.getWriter().write(gson.toJson(allSched));
	}
}
