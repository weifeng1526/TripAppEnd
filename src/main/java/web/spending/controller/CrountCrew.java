package web.spending.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.sched.vo.Sched;
import web.spending.service.CostRecdService;
import web.spending.service.impl.CostRecdServiceImpl;
import web.spending.vo.CostRecd;
import web.spending.vo.Crew;
import web.spending.vo.Result;

@WebServlet("/spending/countCrew")
public class CrountCrew extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Crew crew = gson.fromJson(req.getReader(), Crew.class);

		try {
			String schNoStr = req.getParameter("schNo");
			Integer schNo = Integer.parseInt(schNoStr);
			CostRecdService costRecdService = new CostRecdServiceImpl();
			int crewCount = costRecdService.crewNum(schNo);
			System.out.println("count--controller--" + crewCount);
			//write 只能輸出字串，print 可以支援比較多類型。
			resp.getWriter().print(crewCount);
			resp.getWriter().write(String.valueOf(crewCount));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
