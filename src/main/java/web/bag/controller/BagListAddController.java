//package web.bag.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//
//import web.bag.dao.BagListDao;
//import web.bag.dao.impl.BagListDaoImpl;
//import web.bag.vo.BagList;
//
//@WebServlet("baglist/add")
//public class BagListAddController extends HttpServlet{
//	private static final long serialVersionUID = 1L;
//
//	  @Override
//	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//	        try {
//	            // 从请求体中读取 JSON 数据
//	            Gson gson = new Gson();
//	            BagList[] bagLists = gson.fromJson(req.getReader(), BagList[].class);
//
//	            BagListDao dao = new BagListDaoImpl();
//	            for (BagList bagList : bagLists) {
//	                dao.insertBagList(bagList);
//	            }
//
//	            resp.setStatus(HttpServletResponse.SC_OK);
//	            resp.getWriter().write("Items added to bag list successfully!");
//	        } catch (Exception e) {
//	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	            e.printStackTrace();
//	        }
//	    }
//	}