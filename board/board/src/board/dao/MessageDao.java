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
			sql.append("INSERT INTO messages ( ");
			sql.append("title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(", user_id");
			sql.append(", department_id");
			sql.append(") VALUES (");
			sql.append("?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // categoly
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", ?"); //user_id
			sql.append(", ?");
			sql.append(")");


			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUser_id());
			ps.setInt(5, message.getDepartment_id());

			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void deleteMessage(Connection connection, int message_id) {
		PreparedStatement ps = null;
		try {
			String sql ="DELETE FROM messages WHERE id=?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, message_id);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);

		}
	}

}



