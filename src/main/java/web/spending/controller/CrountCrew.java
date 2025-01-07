package web.spending.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;
import web.spending.vo.Crew;

@WebServlet("/spending/crountCrew")
public class CrountCrew extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Crew crew = new Crew();
		Gson gson = new Gson();
		try {
			CostRecdService costRecdService = new CostRecdServiceImpl();
			Integer crewNum = costRecdService.crewNum(crew);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().write(7);
	}
	

}
