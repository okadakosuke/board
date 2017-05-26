package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import board.beans.Comment;
import board.beans.User;
import board.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

			request.getRequestDispatcher("top.jsp").forward(request, response);
		}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> comments = new ArrayList<String>();

		if (isValid(request, comments) == true) {

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");
			int message_id = Integer.parseInt(request.getParameter("message_id"));

			Comment comment = new Comment();
			comment.setText(request.getParameter("text"));
			comment.setUser_id(user.getId());
			comment.setMessage_id(message_id);

			new CommentService().register(comment);

			response.sendRedirect("./");


		} else {
			request.setAttribute("errorMessages", comments);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String text = request.getParameter("text");
		if (StringUtils.isEmpty(text) == true) {
			comments.add("コメントを入力してください");
		}
		if (500 < text.length()) {
			comments.add("500文字以下で入力してください");
		}

		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
