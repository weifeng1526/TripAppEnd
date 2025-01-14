package web.spending.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;
import web.spending.vo.CostRecd;

@WebServlet("/spending/findTripsSpendingAll")
public class FindTripsSpendingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		CostRecdService costRecdService = new CostRecdServiceImpl();
		
		Gson gson = new Gson();
		Integer memNo = Integer.parseInt(req.getParameter("memNo")) ;
		List<CostRecd> costRecdList = costRecdService.SpendingfindAll(memNo);
		
		for (CostRecd costRecd : costRecdList) {
			costRecd.getCostNo();
			int crewNum = costRecdService.crewNum(costRecd.getSchNo());
			costRecd.setCountCrew(crewNum);
		}
		
		String json = gson.toJson(costRecdList);
//		String json = gson.toJson(costRecdService.SpendingfindAll(memNo));
		resp.getWriter().write(json);
		
		
	} catch (Exception e) {
		e.printStackTrace();
		
		
	}
}


}
