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
import board.exception.NoRowsUpdatedRuntimeException;
import board.service.UserService;



@WebServlet(urlPatterns = { "/setting" })
public class SettingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(session.getAttribute("editUser") == null) {
			User editUser = new UserService().getUser(loginUser.getId());
			session.setAttribute("editUser", editUser);
		}
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		List<String> users = new ArrayList<String>();
		HttpSession session = request.getSession();

		User editUser = getEditUser(request);
		session.setAttribute("editUser",editUser);

		if(isValid(request, users) == true) {


			try{
				new UserService().update(editUser);
			} catch(NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				users.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", users);
				response.sendRedirect("setting");
			}

			session.setAttribute("loginUser", editUser);
			session.removeAttribute("editUser");

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", users);
			response.sendRedirect("setting");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setName(request.getParameter("name"));
		editUser.setLogin_id(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranch_id(request.getParameter("branch_id"));
		editUser.setDepartment_id(request.getParameter("department_id"));

		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {
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


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}