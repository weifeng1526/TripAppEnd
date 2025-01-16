package web.spending.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;
import web.spending.vo.CostRecd;

@WebServlet("/spending/findOneTripsSpending")
public class FindOneTripsSpendingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			CostRecdService costRecdService = new CostRecdServiceImpl();
			Gson gson = new Gson();
			Integer costNo = Integer.parseInt(req.getParameter("costNo")) ;
			// 找到是誰
		    CostRecd costRecd = costRecdService.SpendingfindOne(costNo);
		    // 知道有幾個人
			int crewNum = costRecdService.crewNum(costRecd.getSchNo());
			// 把他塞回原本
			costRecd.setCountCrew(crewNum);
			String json = gson.toJson(costRecd);
			
			resp.getWriter().write(json);

		} catch (Exception e) {
			e.printStackTrace();
		}

//	Gson gson = new Gson();
//	CostRecd costRecd = gson.fromJson(req.getReader(), CostRecd.class);
//
////			CostRecd costRecd = new CostRecd();
////			Result result = new Result();
//	CostRecdService costRecdService = (CostRecdService) req.getAttribute("CostRecd");
//	
//	resp.getWriter().write(gson.toJson(costRecd));

//		CostRecd costRecd = new CostRecd();
//		Gson gson = new Gson();
//		String idStr = req.getParameter("costNo");
//		Integer id = Integer.parseInt(idStr);
//		resp.getWriter().write(gson.toJson(costRecd));
//		

	}

}
