package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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

	public List<UserMessage> getUserMessages(String categorybox, String timedate, String finishdate) {

		Connection connection = null;
		try {
			connection = getConnection();

			if(StringUtils.isEmpty(timedate)){
				timedate = "2015-05-25 00:00:00";
			}
			if(StringUtils.isEmpty(finishdate)) {
				 Date date = new Date();
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				finishdate = sdf.format(date).toString();
			}

			UserMessageDao usermessageDao = new UserMessageDao();
			List<UserMessage> usermessages = usermessageDao.getUserMessages(connection, categorybox, timedate, finishdate);

			commit(connection);
			return usermessages;
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}
	public void deleteMessage(int message_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.deleteMessage(connection, message_id);

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

}
