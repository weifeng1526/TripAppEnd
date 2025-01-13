package web.bag.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;

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

@WebServlet("/bag/add")
public class BagListAddItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        
        // 解析請求內容並轉換為 BagList 物件
        BagList bagList = gson.fromJson(req.getReader(), BagList.class);

        // 嘗試新增資料到資料庫
        try {
            // 使用 BagListDaoImpl 類別中的 add 方法
            BagListDao bagListDao = new BagListDaoImpl();
            bagListDao.add(bagList);
            
            // 設定回應狀態碼和訊息
            resp.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
            resp.getWriter().write("{\"message\": \"Bag item added successfully.\"}");
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

