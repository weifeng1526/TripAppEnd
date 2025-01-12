package web.notes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.notes.dao.impl.NotesDaoImpl;
import web.notes.vo.Notes;	

@WebServlet("/notes/creat")
public class NotesCreateController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		NotesDaoImpl notesDaoImpl;
		try {
			notesDaoImpl = new NotesDaoImpl();
			Gson gson = new Gson();
			Notes notes = gson.fromJson(req.getReader(), Notes.class);
			int isCreated = notesDaoImpl.create(notes);
			if (isCreated> 0) {
				System.out.println("POST: notes創建成功: " + isCreated);
			} else {
				System.out.println("POST: notes創建失敗");
				resp.setStatus(resp.SC_BAD_REQUEST);
			}
			resp.getWriter().write(gson.toJson(notes));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
