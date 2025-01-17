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
import web.spending.vo.Result;

@WebServlet("/spending/removeOneTripsSpending")
public class RemoveOneTripsSpendingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result result = new Result();
		Gson gson = new Gson();
		try {
			String costNoSt = req.getParameter("costNo");
//			System.out.println("aaastr"+costNoSt);
			// 選起來右鍵 > surround with > try catch block，我要捕捉 Integer 轉型失敗的例外（
			// NumberFormatException e ）。
			
			Integer costNo = Integer.parseInt(costNoSt);
//			System.out.println("bbbint"+costNo);
			// 這行紅紅的地方，可以直接產生 NamingException e 例外。（ NamingException 實例與共時多執行緒存取不同步。）
			CostRecdService costRecdService = new CostRecdServiceImpl();
			result.setSuccess(costRecdService.deleteList(costNo));
			
		} catch (NumberFormatException e) {
			result.setSuccess(false);
			result.setMessage("消費紀錄編號無效，請重新輸入！");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		resp.getWriter().write(gson.toJson(result));
	}

}
