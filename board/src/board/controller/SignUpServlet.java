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

import board.beans.User;
import board.service.UserService;



@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if(isValid(request, messages) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(request.getParameter("branch_id"));
			user.setDepartment_id(request.getParameter("department_id"));

			new UserService().register(user);

			response.sendRedirect("./");
		}else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages){
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String branch_id = request.getParameter("branch_id");
		String department_id = request.getParameter("department_id");

		if(StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}
		if(StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if(StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if(StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店名を入力してください");
		}
		if(StringUtils.isEmpty(department_id) == true) {
			messages.add("部署・役職名を入力してください");
		}
		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
}