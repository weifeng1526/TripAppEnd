package web.bag.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.bag.dao.BagListDao;
import web.bag.dao.impl.BagListDaoImpl;

@WebServlet("/bag/delete")
public class BagListDeleteItemController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // 解析 URL 中的 query 參數
        String memNoParam = req.getParameter("memNo");
        String schNoParam = req.getParameter("schNo");
        String itemNoParam = req.getParameter("itemNo");

        if (memNoParam == null || schNoParam == null || itemNoParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
            resp.getWriter().write("{\"error\": \"Missing parameters\"}");
            return;
        }

        // 將參數轉換為數字
        int memNo = Integer.parseInt(memNoParam);
        int schNo = Integer.parseInt(schNoParam);
        int itemNo = Integer.parseInt(itemNoParam);

        // 嘗試刪除資料庫中的資料
        try {
            BagListDao bagListDao = new BagListDaoImpl();
            int rowsAffected = bagListDao.delete(memNo, schNo, itemNo);

            if (rowsAffected > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);  // 200 OK
                resp.getWriter().write("{\"message\": \"Bag item deleted successfully.\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404 Not Found
                resp.getWriter().write("{\"error\": \"Bag item not found.\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
            resp.getWriter().write("{\"error\": \"Failed to delete bag item: " + e.getMessage() + "\"}");
        }
	}
}
