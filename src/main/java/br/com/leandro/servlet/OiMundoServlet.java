package br.com.leandro.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/oi-mundo")
public class OiMundoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921114818104976947L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<body><h1>Oi Mundo</h1></body>");
		out.print("</html>");
	}
}
