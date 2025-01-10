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
import web.sched.vo.Dest;

@WebServlet("/sched/dest/getDestsSample")
public class DestGetByMemIdController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		List<Dest> dests = new ArrayList<>();
		Gson gson = new Gson();
		
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			int memId = Integer.parseInt(req.getParameter("memId")); 
			int schId = Integer.parseInt(req.getParameter("schId")); 
			dests = destDaoImpl.selectByMemIdAndSchId(memId, schId);
			if(!dests.isEmpty()) {
				System.out.printf("GET: selectByMemIdAndSchId總共%d筆資料\r\n", dests.size());
			} else {
				System.out.println("GET: selectByMemIdAndSchId沒有資料\r\n");
			}
			resp.getWriter().write(gson.toJson(dests));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
