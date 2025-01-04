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

@WebServlet("/sched/dest/create")
public class DestCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Dest dest = gson.fromJson(req.getReader(), Dest.class);
			int isInserted = destDaoImpl.insert(dest);
			if (isInserted > 0) {
				System.out.println("POST: Dest創建成功: " + isInserted);
			} else {
				System.out.println("POST: Dest創建失敗");
			}
			resp.getWriter().write(gson.toJson(dest));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
