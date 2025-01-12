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

@WebServlet("/spending/saveOneTripsSpending")
public class SaveOneTripsSpendingController extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		//CostRecd.class 不能只寫一個類別在這裡，所以.class把它變成物件。
		//我要返回 CostRecd 型態。
		CostRecd costRecd = gson.fromJson(req.getReader(),CostRecd.class);
		
		
Result result = new Result();
		

		try {
			CostRecdService costRecdService =  new CostRecdServiceImpl();
			//呼叫service的方法
			String message = costRecdService.saveList(costRecd);
			if (message == null) {
				result.setSuccess(true);
				result.setMessage("修改成功！");
			}else {
				result.setSuccess(false);
				result.setMessage(message);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		String json  = gson.toJson(result);
		resp.getWriter().write(json);
	}
	
	
	
	
	
	
	

}
