package web.sched.controller;

import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.sched.dao.impl.SchedDaoImpl;
import web.sched.vo.Sched;
import web.sched.vo.SchedInfo;

@WebServlet({ "/schedule", "/schedule/info" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String uri = req.getRequestURI();
		Integer memId = getIntParameter(req, "memId");
		Integer limit = getIntParameter(req, "limit");
		Integer offset = getIntParameter(req, "offset");
		List<SchedInfo> list = new ArrayList<>();
		SchedDaoImpl schedDaoImpl;
		try {
			schedDaoImpl = new SchedDaoImpl();
			if (uri.equals("/TripAppEnd/schedule/info")) {
				if (memId != null && limit == null && offset == null) {
					list = schedDaoImpl.selectSchedInfo(memId);
				} else if (memId != null && limit != null && offset != null) {
					list = schedDaoImpl.selectSchedInfo(memId, limit, offset);
				} else
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				if (!list.isEmpty()) {
					System.out.printf("GET %s: 總共%d筆資料\r\n", req.getRequestURI(), list.size());
				} else {
					System.out.printf("GET %s: 沒有資料\r\n", req.getRequestURI());
				}
				resp.getWriter().write(gson.toJson(list));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String uri = req.getRequestURI();
		SchedInfo reqSchedInfo;
		SchedDaoImpl schedDaoImpl;
		InputStream imageStream = null;
		if (uri.equals("/TripAppEnd/schedule")) {
			try {
				schedDaoImpl = new SchedDaoImpl();
				int updatedId = 0;
				Part planInfoPart = req.getPart("planInfo");
				Part planPicturePart = req.getPart("planImage");
				InputStream planInfoInputStream = null;
				InputStream planImgInputStream = null;
				SchedInfo schedInfo = null;
				SchedInfo result = null;
				if (planInfoPart != null && planPicturePart != null) {
					String line;
					planInfoInputStream = planInfoPart.getInputStream();
					planImgInputStream = planPicturePart.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(planInfoInputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					StringBuilder jsonString = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null) {
						jsonString.append(line);
					}
					schedInfo = gson.fromJson(jsonString.toString(), SchedInfo.class);
					result = schedDaoImpl.insertSchedInfo(schedInfo);
					if (result != null) {
						updatedId = schedDaoImpl.updateImage(result.getSchNo(), planImgInputStream);
					}
				}
				if (result.getSchNo() != 0 && updatedId > 0) {
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(String.valueOf(updatedId));
					System.out.printf("POST %s: 成功 sch_no=%d\r\n", req.getRequestURI(), updatedId);
				} else {
					System.out.printf("POST %s: 失敗\r\n", req.getRequestURI());
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	private Integer getIntParameter(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);
		if (paramValue != null && !paramValue.isEmpty()) {
			try {
				return Integer.parseInt(paramValue);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}
}
