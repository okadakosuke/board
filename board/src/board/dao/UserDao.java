package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.User;
import board.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String login_id, String password) {
		PreparedStatement ps =null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps =connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			}else {
				return userList.get(0);
			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int user){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, user);

			ResultSet rs =ps.executeQuery();
			List<User> userList = toUserList(rs);
			if(userList.isEmpty() == true) {
				return null;
			} else if(2<= userList.size()) {
				throw new IllegalStateException("2<= userList.size()");
			} else {
				return userList.get(0);

			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}


	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users (");
			sql.append("name");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(")VALUES (");
			sql.append("?");  //name
			sql.append(", ?");  //login_id
			sql.append(", ?");  //password
			sql.append(", ?");  //branch_id
			sql.append(", ?");  //department_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin_id());
			ps.setString(3, user.getPassword());
			ps.setInt(4, Integer.parseInt(user.getBranch_id()));
			ps.setInt(5, Integer.parseInt(user.getDepartment_id()));
		//	ps.setInt(6, 0);


		ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);

		}
	}


	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("name =?");
			sql.append(", login_id =?");
			sql.append(", password =?");
			sql.append(", branch_id =?");
			sql.append(", department_id =?");


			sql.append(" WHERE");
			sql.append(" id =?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin_id());
			ps.setString(3, user.getPassword());
			ps.setInt(4, Integer.parseInt(user.getBranch_id()));
			ps.setInt(5, Integer.parseInt(user.getDepartment_id()));
			ps.setInt(6, user.getId());


		ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);

		}
	}

	public List<User> getUsers(Connection connection) {


		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}
	private List<User> toUserList(ResultSet rs)
		throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String login_id = rs.getString("login_id");
				String branch_id = rs.getString("branch_id");
				String department_id = rs.getString("department_id");

				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setLogin_id(login_id);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);

				ret.add(user);

			}
			return ret;
		}finally {
			close(rs);
		}
	}
}
