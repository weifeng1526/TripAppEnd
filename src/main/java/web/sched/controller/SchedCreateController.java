package web.sched.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;

@WebServlet("/sched/create")
public class SchedCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		SchedDaoImpl schedDaoImpl = new SchedDaoImpl();
		Gson gson= new GsonBuilder().setDateFormat("yy-MM-dd").create();
		Sched sched = gson.fromJson(req.getReader(), Sched.class);
		int isInserted = schedDaoImpl.insert(sched);
		if(isInserted > 0) {
			System.out.println("POST: Sched創建成功: " + isInserted);
		} else {
			System.out.println("POST: Sched創建失敗");
		}
		resp.getWriter().write(gson.toJson(sched));
	}
}
