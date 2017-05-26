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

import board.beans.Message;
import board.beans.User;
import board.service.MessageService;

@WebServlet(urlPatterns = { "/message" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

			request.getRequestDispatcher("message.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");

			Message message = new Message();
			message.setTitle(request.getParameter("title"));
			message.setText(request.getParameter("text"));
			message.setCategory(request.getParameter("category"));
			message.setId(user.getId());


			new MessageService().register(message);

			response.sendRedirect("./");
		} else {
			request.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(title) ==true) {
			messages.add("タイトルを入力してください");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("メッセージを入力してください");
		}
		if (1000 < text.length()) {
			messages.add("1000文字以下で入力してください");
		}
		if (StringUtils.isEmpty(category) ==true) {
			messages.add("カテゴリを入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
