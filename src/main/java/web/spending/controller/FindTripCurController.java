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

@WebServlet("/spending/findTripCur")
public class FindTripCurController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CostRecdService costRecdService = new CostRecdServiceImpl();
			Gson gson = new Gson();
			Integer getschNo = Integer.parseInt(req.getParameter("schNo")) ;
			String json = gson.toJson(costRecdService.findTripCur(getschNo));
			System.out.println("test"+json);
			
			resp.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
