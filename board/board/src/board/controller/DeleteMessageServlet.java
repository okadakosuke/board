package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.MessageService;



@WebServlet(urlPatterns = { "/delete_message" })
public class DeleteMessageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String messageid = request.getParameter("message_id");
		int message_id = Integer.parseInt(messageid);;
		new MessageService().deleteMessage(message_id);


		response.sendRedirect("./");



	}
}