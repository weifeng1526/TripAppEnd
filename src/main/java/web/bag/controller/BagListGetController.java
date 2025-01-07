package web.bag.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.bag.dao.BagListDao;
import web.bag.dao.impl.BagListDaoImpl;
import web.bag.vo.BagList;

@WebServlet("/bag/get")
public class BagListGetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BagListDao dao = new BagListDaoImpl();
			List<BagList> allBagLists = dao.selectAllbags();
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(allBagLists));
		} catch (NamingException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
