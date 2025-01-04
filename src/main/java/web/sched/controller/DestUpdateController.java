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
import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Dest;
import web.sched.vo.Sched;

@WebServlet("/sched/dest/update")
public class DestUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Dest dest = gson.fromJson(req.getReader(), Dest.class);
			int isUpdated = destDaoImpl.update(dest);
			if(isUpdated > 0) {
				System.out.println("PUT: Dest更新成功: " + dest.getDstNo());
			} else {
				System.out.println("PUT: Dest更新失敗: " + dest.getDstNo());
			}
			resp.getWriter().write(gson.toJson(dest));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
