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

import web.sched.dao.impl.CrewDaoImpl;
import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Crew;
import web.sched.vo.DeleteResult;
import web.sched.vo.Dest;

@WebServlet("/sched/crew/delete")
public class CrewDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		CrewDaoImpl crewDaoImpl;
		DeleteResult result = new DeleteResult();;
		try {
			crewDaoImpl = new CrewDaoImpl();
			Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Crew crew = gson.fromJson(req.getReader(), Crew.class);
			int id = Integer.parseInt(req.getParameter("id"));
			int isDeleted = crewDaoImpl.deleteByMemId(id);
			if(isDeleted > 0) {
				System.out.println("DELETE: crew刪除成功");
				result.setMessage("DELETE: crew刪除成功");
				result.setSucesses(true);
			} else {
				result.setMessage("DELETE: crew刪除失敗");
				result.setSucesses(false);
			}
			resp.getWriter().write(gson.toJson(result));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSucesses(false);
		}
	}
}
