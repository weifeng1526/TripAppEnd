package web.notes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.notes.dao.impl.NotesDaoImpl;
import web.notes.vo.Notes;

@WebServlet("/notes/update")
public class NotesUpdateCotroller extends HttpServlet{
	private static final long serialVersionUID = 1L;
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("application/json; charset=UTF-8");
	NotesDaoImpl notesDaoImpl;
	Gson gson = new Gson();
	try {
		notesDaoImpl = new NotesDaoImpl();
		Notes notes = gson.fromJson(req.getReader(), Notes.class);
		int Updated = notesDaoImpl.update(notes);
		resp.getWriter().write(gson.toJson(notes));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
}
