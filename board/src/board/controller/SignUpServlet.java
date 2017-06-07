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

		User differentUser = new UserService().select(request.getParameter("login_id"));

		if(isValid(request, messages, differentUser) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setDepartment_id(Integer.parseInt(request.getParameter("department")));


			new UserService().register(user);

			response.sendRedirect("./manage");
		}else{

			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches", branches);

			List<Department> departments = new DepartmentService().getDepartment();
			request.setAttribute("departments", departments);

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
		newUser.setDepartment_id(Integer.parseInt(request.getParameter("department")));

		return newUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages, User differentUser){
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department"));
		String checkPassword =request.getParameter("checkPassword");

		if(StringUtils.isBlank(name) == true) {
			messages.add("名前を入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10字以下で入力してください");
		}
		if(StringUtils.isBlank(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}else if((login_id.matches("\\w{6,20}")) != true) {
			messages.add("ログインIDは半角英数字の6～20文字で入力してください");
		}
		if(differentUser != null){
			messages.add("ログインIDが重複しています");
		}
		if(StringUtils.isBlank(password) == true) {
			messages.add("パスワードを入力してください");
		}else if((password.matches("\\w{6,255}")) !=true) {
			messages.add("パスワードは半角英数字の6～255文字で入力してください");
		}
		if(StringUtils.equals(password, checkPassword) ==false) {
			messages.add("パスワードを確認用と同一のものにしてください");
		}
		if(branch_id == 0) {
			messages.add("支店名を選択してください");
		}
		if(department_id == 0) {
			messages.add("部署・役職名を選択してください");
		}

		if((branch_id !=1 && department_id ==1 || branch_id !=1 && department_id ==2) ==true){
			messages.add("支店名と部署・役職の組み合わせが正しくありません");
		}
		if((branch_id ==1 && department_id ==3 || branch_id ==1 && department_id ==4) ==true){
			messages.add("支店名と部署・役職の組み合わせが正しくありません");
		}

		if(messages.size() ==0) {
			return true;
		}else {
			return false;
		}
	}
}