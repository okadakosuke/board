package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Comment;
import board.beans.UserComments;
import board.dao.CommentDao;
import board.dao.UserCommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	public List<UserComments> getUserComments() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao usercommentDao = new UserCommentDao();
			List<UserComments> usercomments = usercommentDao.getUserComments(connection);

			commit(connection);
			return usercomments;
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}
	public void deleteComment(int comment_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.deleteComment(connection, comment_id);

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