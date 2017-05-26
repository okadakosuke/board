package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.UserService;



@WebServlet(urlPatterns = { "/stop" })
public class StopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("manage.jsp").forward(request, response);
	}

		@Override
		protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException {


		String userid = request.getParameter("id");
		int user_id = Integer.parseInt(userid);
		int num = Integer.parseInt(request.getParameter("num"));
		 new UserService().stopUser(user_id, num);

			response.sendRedirect("./manage");
	}
}
