//package web.bag.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//import com.google.gson.Gson;
//
//import web.bag.dao.BagListDao;
//import web.bag.vo.BagList;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//
//@WebServlet("/bag/get")
//public class BagGetController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    
//    private BagListDao bagListDao;
//
//    @Override
//    public void init() throws ServletException {
//        // 假設資料源已經在應用程式中配置
//        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
//        bagListDao = new BagListDao(ds); // 初始化 DAO
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int memNo = Integer.parseInt(req.getParameter("memNo"));
//        int schNo = Integer.parseInt(req.getParameter("schNo"));
//
//        // 查詢 bag_list 資料並返回 JSON 格式
//        List<BagList> bagLists = bagListDao.findByMemAndSchedule(memNo, schNo);
//        Gson gson = new Gson();
//        String json = gson.toJson(bagLists);
//
//        // 設置響應格式
//        resp.setContentType("application/json");
//        resp.getWriter().write(json);
//    }
//}