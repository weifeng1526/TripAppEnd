package web.spending.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;

@WebServlet("/spending/findTripName")
public class FindTripNameController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("來來來");
			CostRecdService costRecdService = new CostRecdServiceImpl();
			Gson gson = new Gson();
			Integer memNo = Integer.parseInt(req.getParameter("memNo")) ;
			String json = gson.toJson(costRecdService.FindTripName(memNo));
			System.out.println(json);
			
			resp.getWriter().write(json);
			System.out.println("出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
