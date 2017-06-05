package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.beans.Message;
import board.beans.UserComments;
import board.beans.UserMessage;
import board.service.CommentService;
import board.service.MessageService;
import board.service.UserService;

	@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {

			String categorybox = request.getParameter("categorybox");
			String timedate = request.getParameter("timedate");
			String finishdate = request.getParameter("finishdate");

			List<UserMessage> usermessages = new MessageService().getUserMessages(categorybox, timedate, finishdate);
			request.setAttribute("usermessages", usermessages);

			List<UserComments> usercomments = new CommentService().getUserComments();
			request.setAttribute("usercomments", usercomments);

			List<Message> categorys = new UserService().getCategory();
			request.setAttribute("categorys", categorys);
			request.setAttribute("categorybox", categorybox);
			request.setAttribute("timedate", timedate);
			request.setAttribute("finishdate", finishdate);

			request.getRequestDispatcher("./top.jsp").forward(request,response);

		}


}
