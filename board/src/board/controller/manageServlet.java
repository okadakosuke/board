package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.beans.User;
import board.service.UserService;

@WebServlet(urlPatterns = { "/manage" })
public class manageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
ServletException{


		List<User> users = new UserService().getUsers();

		request.setAttribute("users", users);
		request.getRequestDispatcher("./manage.jsp").forward(request, response);
	}

}
