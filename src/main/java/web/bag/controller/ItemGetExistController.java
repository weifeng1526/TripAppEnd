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

@WebServlet("/item/getexist")
public class ItemGetExistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// 從請求中獲取 memNo 和 schNo 參數
			String memNoParam = req.getParameter("memNo");
			String schNoParam = req.getParameter("schNo");

			// 檢查 memNo 和 schNo 是否為 null 或無效
			if (memNoParam == null || schNoParam == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().write("{\"error\":\"Error: memNo and schNo cannot be null\"}");
				return;
			}

			// 將字符串參數轉換為整數
			int memNo = Integer.parseInt(memNoParam);
			int schNo = Integer.parseInt(schNoParam);

			// 使用 ItemDao 來根據 memNo 和 schNo 獲取物品
			ItemDao dao = new ItemDaoImpl();
			List<Item> existItems = dao.selectIfExist(memNo, schNo); // 根據 memNo 和 schNo 獲取物品
			Gson gson = new Gson();
			resp.getWriter().write(gson.toJson(existItems)); // 返回物品數據
		} catch (NumberFormatException e) {
			// 如果 memNo 或 schNo 不是有效的整數，返回 400 錯誤
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("{\"error\":\"Error: memNo and schNo must be integers\"}");
		} catch (NamingException e) {
			// 處理任何資料庫或 DAO 層的錯誤
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
