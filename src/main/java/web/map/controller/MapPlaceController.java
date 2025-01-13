package web.map.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.map.service.MapService;
import web.map.service.impl.MapServiceImpl;

import web.map.vo.Map;

@WebServlet("/map/place")
public class MapPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MapPlaceController");
		Gson gson = new Gson();
		Map map = gson.fromJson(req.getReader(), Map.class);
		String address = map.getPoiAdd();
		String planDate =map.getDstDate();
		String planStart = map.getDstStart();
		String planEnd = map.getDstEnd();
		String planInr = map.getDstInr();
		int addPlanNumber = map.getSchNo();
		byte[] planPic=map.getDstPic();
		Map selectPlace = new Map();
		System.out.println("行程表編號:"+addPlanNumber);
		System.out.println("收藏狀態:"+map.getPoiLike());
		try {
			MapService service = new MapServiceImpl();
			selectPlace = service.placeinfoaddcheck(map, address,addPlanNumber,planDate,planStart,planEnd,planInr,planPic);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		resp.getWriter().write(gson.toJson(selectPlace));
	}

}
