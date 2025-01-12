package web.spending.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;
import web.spending.vo.CostRecd;
import web.spending.vo.Result;

@WebServlet("/spending/addlistController")
public class TripSpendingAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json只有字串沒有日期時間，所以要先轉換格式。
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy/MM/dd HH:mm:ss")
				.create();
		CostRecd costRecd = gson.fromJson(req.getReader(),CostRecd.class);
	
		Result result = new Result();
		
		
		
		try {
			CostRecdService costRecdService =  new CostRecdServiceImpl();
			//呼叫service的方法
			String message = costRecdService.addList(costRecd);
			if (message == null) {
				result.setSuccess(true);
				result.setMessage("新增成功！");
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
