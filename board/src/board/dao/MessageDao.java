package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Message;
import board.beans.User;
import board.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("title");
			sql.append(", text");
			sql.append(", category");
		//	sql.append(", insert_date");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // categoly
		//	sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", ?"); //user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
		//	ps.setTimestamp(5, message.getInsert_Date());
			ps.setInt(4, message.getUser_id());
		//	ps.setInt(5, message.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public List<Message> getMessages(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM messages ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Message> ret = toMessageList(rs);
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
					String title = rs.getString("title");
					String text = rs.getString("text");
					String category = rs.getString("category");
					String user_id = rs.getString("user_id");

					User message = new User();
					message.setTitle(title);
					message.setText(text);
					message.setCategory(category);
					message.setUser_id(user_id);

					ret.add(message);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}



