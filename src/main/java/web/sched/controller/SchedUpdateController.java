package web.sched.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;

@WebServlet("/sched/update")
public class SchedUpdateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		SchedDaoImpl schedDaoImpl = new SchedDaoImpl();
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Sched sched = gson.fromJson(req.getReader(), Sched.class);
		int isUpdated = schedDaoImpl.update(sched);
		if(isUpdated > 0) {
			System.out.println("PUT: Sched更新成功: " + sched.getSchNo());
		} else {
			System.out.println("PUT: Sched更新失敗: " + sched.getSchNo());
		}
		resp.getWriter().write(gson.toJson(sched));
	}
	
}
