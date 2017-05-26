package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.UserService;



@WebServlet(urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userid = request.getParameter("id");
		int user_id = Integer.parseInt(userid);
		new UserService().deleteUser(user_id);

		response.sendRedirect("./manage");



	}
}