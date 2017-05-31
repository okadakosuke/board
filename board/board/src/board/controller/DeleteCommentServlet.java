package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.CommentService;



@WebServlet(urlPatterns = { "/delete_comment" })
public class DeleteCommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commentid = request.getParameter("comment_id");
		int comment_id = Integer.parseInt(commentid);;
		new CommentService().deleteComment(comment_id);


		response.sendRedirect("./");



	}
}