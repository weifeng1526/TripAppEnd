package web.sched.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
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

import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Dest;

@WebServlet("/sched/getDestByDate")
public class DestGetByDateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, IllegalArgumentException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		List<Dest> list = new ArrayList<>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String getDate = req.getParameter("date");
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date date = Date.valueOf(getDate);
		DestDaoImpl destDaoImpl;
		try {
			destDaoImpl = new DestDaoImpl();
			list = destDaoImpl.selectAllByDate(date);
			if(!list.isEmpty()) {
				System.out.printf("GET: Dest表的日期%s總共有%d筆資料\r\n",date, list.size());
			} else {
				System.out.printf("GET: Dest表的日期%s總共有%d筆資料\r\n",date, list.size());
			}
			resp.getWriter().write(gson.toJson(list));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
