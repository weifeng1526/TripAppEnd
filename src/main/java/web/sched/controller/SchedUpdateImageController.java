package web.sched.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import web.sched.dao.impl.SchedDaoImpl;

@WebServlet("/sched/image")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 5,  // 5MB
	    maxRequestSize = 1024 * 1024 * 10 // 10MB
	)
public class SchedUpdateImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // 在控制台打印操作信息
	    System.out.println("Put!!");
	    // 設置請求的字符編碼為 UTF-8，以正確處理非英文字符
	    req.setCharacterEncoding("UTF-8");
	    SchedDaoImpl schedDaoImpl;
	    try {
	    	schedDaoImpl = new SchedDaoImpl();
	        // 初始化圖片流和圖片 ID
	        InputStream imageStream = null;
	    	String schIdStr = null;
            Part schIdPart = req.getPart("schId"); // 從請求中獲取schId
	        Part imagePart = req.getPart("image"); // 貼文圖片部分
	        if (schIdPart != null) {
	            // 獲取 schIdPart 的輸入流並轉換為 String
	            schIdStr = new String(schIdPart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
	            System.out.println(schIdStr);
	        }
	        int imageId = 0;
	        // 如果有上傳圖片，處理圖片數據
	        if (imagePart != null && imagePart.getSize() > 0 && !schIdStr.isEmpty() && schIdStr != null) {
	            imageStream = imagePart.getInputStream();                // 獲取圖片的輸入流
	            imageId = schedDaoImpl.updateImage(schIdStr, imageStream); // 將圖片保存到資料庫，返回圖片 ID
	        }
	        // 設置響應狀態為成功並回應成功信息
	        resp.setStatus(HttpServletResponse.SC_OK);
	        resp.getWriter().write("Post added ok");
	    } catch (Exception e) {
	        // 如果發生異常，打印堆棧跟蹤信息
	        e.printStackTrace();
	        // 設置響應狀態為內部服務器錯誤並回應錯誤信息
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        resp.getWriter().write("Error: " + e.getMessage());
	    }
	}
	
}
