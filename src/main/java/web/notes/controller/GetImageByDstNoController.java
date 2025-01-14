package web.notes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.notes.dao.impl.NotesDaoImpl;
import web.sched.dao.impl.DestDaoImpl;
import web.sched.vo.Dest;
@WebServlet("/notes/getImage")
public class GetImageByDstNoController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("application/json; charset=UTF-8");
	Gson gson = new Gson();
	
	NotesDaoImpl notesDaoImpl;
	try {
		notesDaoImpl = new NotesDaoImpl();
		int getId = Integer.parseInt(req.getParameter("dstNo")); 
		Dest getImageByDstno = notesDaoImpl.getImageByDest(getId);
	
		if(getImageByDstno != null) {
			System.out.printf("GET");
		} else {
			System.out.println("False");
		}
		resp.getWriter().write(gson.toJson(getImageByDstno));
	} catch (NamingException e) {
		e.printStackTrace();
	}
}
}
