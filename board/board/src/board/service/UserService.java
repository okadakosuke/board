package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Message;
import board.beans.User;
import board.dao.UserDao;
import board.dao.UserMessageDao;
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


	public User select(String login_id) {

		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User ret = userDao.select(connection, login_id);

			commit(connection);
			return ret;
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

public void stopUser(int user_id, int num) {

	Connection connection = null;
	try {
		connection = getConnection();

		UserDao userDao = new UserDao();
		userDao.stopUser(connection, user_id, num);

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

public void deleteUser(int userId) {

	Connection connection = null;
	try {
		connection = getConnection();

		UserDao userDao = new UserDao();
		userDao.deleteUser(connection, userId);

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


	public List<Message> getCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			List<Message> messages = userMessageDao.getCategory(connection);

			commit(connection);

			return messages;
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
