package web.bag.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet("/bag/update")
public class BagListUpdateItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BagListDao bagListDao;

    @Override
    public void init() throws ServletException {
        try {
            bagListDao = new BagListDaoImpl();
        } catch (NamingException e) {
            throw new ServletException("Error initializing Dao", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        try {
            // 直接從請求體中解析 JSON 數據
            BagList request = gson.fromJson(req.getReader(), BagList.class);

            int memNo = request.getMemNo();
            int schNo = request.getSchNo();
            int itemNo = request.getItemNo();
            boolean ready = request.isReady();

            // 打印出接收到的參數
            System.out.println("memNo: " + memNo);
            System.out.println("schNo: " + schNo);
            System.out.println("itemNo: " + itemNo);
            System.out.println("ready: " + ready);

            // 使用 DAO 層的 updateReady 方法來更新資料
            boolean updateSuccess = bagListDao.updateReady(memNo, schNo, itemNo, ready);

            if (updateSuccess) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Update successful\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"No matching record found\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\": \"Invalid input parameters\"}");
        }
    }
}
