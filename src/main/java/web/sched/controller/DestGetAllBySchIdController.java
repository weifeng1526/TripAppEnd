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

import web.sched.dao.impl.DestDaoImpl;
import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Dest;
import web.sched.vo.Sched;

@WebServlet("/sched/get_dests")
public class DestGetAllBySchIdController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		List<Dest> destsBySchId = new ArrayList<>();
		Gson gson = new Gson();
		
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			int getId = Integer.parseInt(req.getParameter("id")); 
			destsBySchId = destDaoImpl.selectAllBySchId(getId);
		
			if(!destsBySchId.isEmpty()) {
				System.out.printf("GET: Dest表總共%d筆資料\r\n", destsBySchId.size());
			} else {
				System.out.println("GET: Dest表沒有資料\r\n");
			}
			resp.getWriter().write(gson.toJson(destsBySchId));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
