package web.notes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.notes.dao.impl.NotesDaoImpl;
import web.notes.vo.Notes;
import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Dest;

@WebServlet("/notes/getnotes")
public class NotesContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
	try {
		NotesDaoImpl notesDaoImpl = new NotesDaoImpl();
		List<Notes> notes = notesDaoImpl.getAllNotes();
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(notes));
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		e.printStackTrace();
		}
		}
	}