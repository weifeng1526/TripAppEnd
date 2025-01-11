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

import web.bag.dao.ItemDao;
import web.bag.dao.impl.ItemDaoImpl;
import web.bag.vo.Item;

@WebServlet("/item/getbytype")
public class ItemGetByTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			ItemDao dao = new ItemDaoImpl();
            List<List<Item>> groupedItems = dao.selectItemsGroupedByType();
		    Gson gson = new Gson();
		    resp.getWriter().write(gson.toJson(groupedItems));
		} catch (NamingException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}