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
import board.exception.NoRowsUpdatedRuntimeException;
import board.service.BranchService;
import board.service.DepartmentService;
import board.service.UserService;



@WebServlet(urlPatterns = { "/setting" })
public class SettingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		List<String> messages = new ArrayList<String>();
		List<String> submitmessages = new ArrayList<String>();
		HttpSession session = request.getSession();

		List<User> users = new UserService().getUsers();
		String userid = request.getParameter("id");



		if(userid == null ){

			messages.add("存在しないユーザーです");
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("./manage");

		}

		else {
			for(User Users:users){
				if(userid.matches(String.valueOf(Users.getId()))&& !StringUtils.isEmpty(userid)){

					int user_id = Integer.parseInt(userid);
					User editUser = new UserService().getUser(user_id);


					List<Branch> branches = new BranchService().getBranch();
					request.setAttribute("branches", branches);

					List<Department> departments = new DepartmentService().getDepartment();
					request.setAttribute("departments", departments);
					request.setAttribute("editUser", editUser);
					request.getRequestDispatcher("setting.jsp").forward(request, response);


					return;
				}
			}

			messages.add("存在しないユーザーです");
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("./manage");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		User editUser = getEditUser(request);
		request.setAttribute("editUser", editUser);

		User differentUser = new UserService().select(request.getParameter("login_id"));

		if(isValid(request, messages, differentUser) == true) {

			try{
				new UserService().update(editUser);

				response.sendRedirect("manage");

			} catch(NoRowsUpdatedRuntimeException e) {
				request.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("setting");
			}

			request.setAttribute("user", editUser);
			request.removeAttribute("editUser");


		} else {
			request.setAttribute("newUser", editUser);
			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches", branches);

			List<Department> departments = new DepartmentService().getDepartment();
			request.setAttribute("departments", departments);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("./setting.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request) {

		String userid = request.getParameter("user_id");
		int user_id = Integer.parseInt(userid);
		User editUser = new UserService().getUser(user_id);

		editUser.setName(request.getParameter("name"));
		editUser.setLogin_id(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setDepartment_id(Integer.parseInt(request.getParameter("department")));

		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages, User differentUser) {
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department"));
		String checkPassword =request.getParameter("checkPassword");

		User editUser = getEditUser(request);
		request.setAttribute("editUser", editUser);
		editUser.setLogin_id(request.getParameter("login_id"));

		if(StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10字以下で入力してください");
		}
		if(StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}else if((login_id.matches("\\w{6,20}"))!=true) {
			messages.add("ログインIDは半角英数字の6～20字で入力してください");
		}

		if(differentUser != null && differentUser.getId() != editUser.getId()){
			messages.add("ログインIDが他のユーザーと重複しています");
		}
		if(!StringUtils.isEmpty(password)) {
			if((password.matches("\\w{6,255}" )) !=true) {
				messages.add("パスワードは半角英数字の6～255字で入力してください");
			}
		}
		if(StringUtils.equals(password, checkPassword) !=true) {
			messages.add("パスワードを確認用と同一のものにしてください");
		}
		if(branch_id ==0) {
			messages.add("支店名を選択してください");
		}
		if(department_id ==0) {
			messages.add("部署・役職名を選択してください");
		}

		if((branch_id !=1 && department_id <=2) ==true){
			messages.add("支店名と部署・役職の組み合わせが正しくありません");
		}
		if((branch_id ==1 && department_id >=3) ==true){
			messages.add("支店名と部署・役職の組み合わせが正しくありません");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
