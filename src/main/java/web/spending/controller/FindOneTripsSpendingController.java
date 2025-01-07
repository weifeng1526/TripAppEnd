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
import web.spending.vo.CostRecd;

@WebServlet("/spending/findOneTripsSpending")
public class FindOneTripsSpendingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			CostRecdService costRecdService = new CostRecdServiceImpl();
			Gson gson = new Gson();
			Integer costRecd = Integer.parseInt(req.getParameter("costNo")) ;
			String json = gson.toJson(costRecdService.SpendingfindOne(costRecd));
			
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
