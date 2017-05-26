package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Message;
import board.beans.UserMessage;
import board.dao.MessageDao;
import board.dao.UserMessageDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

			commit(connection);
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	public List<UserMessage> getUserMessages() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao usermessageDao = new UserMessageDao();
			List<UserMessage> usermessages = usermessageDao.getUserMessages(connection);

			commit(connection);
			return usermessages;
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

}
