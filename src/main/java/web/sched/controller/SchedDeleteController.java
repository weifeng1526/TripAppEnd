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

@WebServlet("/sched/delete")
public class SchedDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		SchedDaoImpl schedDaoImpl = new SchedDaoImpl();
		Gson gson= new GsonBuilder().setDateFormat("yy-MM-dd").create();
		Sched sched = gson.fromJson(req.getReader(), Sched.class);
		JsonObject jsonObject = new JsonObject();
		int id = Integer.parseInt(req.getParameter("id"));
		int isDeleted = schedDaoImpl.deleteById(id);
		if(isDeleted > 0) {
			System.out.println("DELETE: Sched刪除成功");
			jsonObject.addProperty("isDelete", true);
		} else {
			System.out.println("DELETE: Sched刪除失敗");
			jsonObject.addProperty("isDelete", false);
		}
		resp.getWriter().write(gson.toJson(jsonObject));
	}
}
