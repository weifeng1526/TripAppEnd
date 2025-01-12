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

@WebServlet("/bag/getitemsbyschno")
public class BagListGetBySchNoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 從請求中獲取 memNo 和 schNo 參數
			String schNoStr = req.getParameter("schNo");

			// 如果沒有收到這些參數，可以返回 400 錯誤
			if (schNoStr == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().write("Missing parameter: schNo");
				return;
			}
			// 解析參數為整數
			int schNo = Integer.parseInt(schNoStr);
			BagListDao dao = new BagListDaoImpl();
			List<BagList> oneBagLists = dao.getBagListBySchNo(schNo);
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(oneBagLists));
		} catch (NamingException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("錯誤格式 for schNo");
		}
	}
}
