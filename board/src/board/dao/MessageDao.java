package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.beans.Message;
import board.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO message ( ");
			sql.append("title");
			sql.append(", text");
			sql.append(", categoly");
			sql.append(", insert_date");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // categoly
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", ?"); //user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUser_id());
			ps.setString(2, message.getText());


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
