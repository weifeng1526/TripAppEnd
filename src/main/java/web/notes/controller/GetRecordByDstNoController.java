package web.notes.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.notes.dao.impl.NotesDaoImpl;
import web.notes.vo.Notes;

@WebServlet("/notes/dstnotes")
public class GetRecordByDstNoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		Integer getDstNo = Integer.parseInt(req.getParameter("dstNo"));
		Integer getMemNo = Integer.parseInt(req.getParameter("memNo"));
		System.out.println("doGet");
		try {
			NotesDaoImpl notesDaoImpl = new NotesDaoImpl();
		Notes notes = notesDaoImpl.getRecordByDstNo(getDstNo, getMemNo);
		if (notes != null) {
			resp.getWriter().write(gson.toJson(notes));
			System.out.printf("GET: Notes表總共%d筆資料\r\n", notes);
		}else {
			System.out.printf("GET: Notes表總共%d筆資料\r\n");
			getDstNo = 0;
			resp.getWriter().write(gson.toJson(notes));
			resp.setStatus(resp.SC_NO_CONTENT);
		}
		resp.getWriter().write(gson.toJson(notes));
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}
