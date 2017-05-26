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

import board.beans.Branch;
import board.beans.Department;
import board.beans.User;
import board.service.BranchService;
import board.service.DepartmentService;
import board.service.UserService;



@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartment();
		request.setAttribute("departments", departments);

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
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setDepartment_id(Integer.parseInt(request.getParameter("department")));


			new UserService().register(user);

			response.sendRedirect("./manage");
		}else{

			User newUser = getNewUser(request);
			request.setAttribute("newUser", newUser);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("./signup.jsp").forward(request, response);
		}
	}
	private User getNewUser(HttpServletRequest request)
			throws IOException, ServletException {

		User newUser = new User();

		newUser.setName(request.getParameter("name"));
		newUser.setLogin_id(request.getParameter("login_id"));
		newUser.setPassword(request.getParameter("password"));
		newUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		newUser.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

		return newUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages){
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department"));
		String checkPassword =request.getParameter("checkPassword");

		if(StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}
		if(StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if(StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if(branch_id == 0) {
			messages.add("支店名を選択してください");
		}
		if(department_id == 0) {
			messages.add("部署・役職名を選択してください");
		}
		if(StringUtils.equals(password, checkPassword) ==false) {
			messages.add("パスワードを確認用と同一のものにしてください");
		}
		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
}