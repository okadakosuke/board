package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.User;
import board.dao.UserDao;
import board.utils.CipherUtil;

public class UserService {

	public void register (User user) {

		Connection connection = null;
		try{
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
		}catch(RuntimeException e) {
			rollback(connection);
			throw e;
		}catch(Error e) {
			rollback(connection);
			throw e;
		}finally {
			close(connection);
		}
	}


public void update(User user) {

	Connection connection = null;
	try {
		connection = getConnection();

		String encPassword = CipherUtil.encrypt(user.getPassword());
		user.setPassword(encPassword);

		UserDao userDao = new UserDao();
		userDao.update(connection, user);

		commit(connection);
	} catch (RuntimeException e) {
		rollback(connection);
		throw e;
	} catch (Error e) {
		rollback(connection);
		throw e;
	} finally {
		close(connection);
	}
}

	public User getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, userId);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<User> getUsers() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> users = userDao.getUsers(connection);

			commit(connection);

			return users;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
