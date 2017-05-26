package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.beans.UserComments;
import board.beans.UserMessage;
import board.service.CommentService;
import board.service.MessageService;

	@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {

			List<UserMessage> usermessages = new MessageService().getUserMessages();
			request.setAttribute("usermessages", usermessages);

			List<UserComments> usercomments = new CommentService().getUserComments();
			request.setAttribute("usercomments", usercomments);


			request.getRequestDispatcher("top.jsp").forward(request,response);

		}


}
