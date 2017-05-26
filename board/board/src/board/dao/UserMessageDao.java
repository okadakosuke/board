package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import board.beans.UserMessage;
import board.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM board.user_messages ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			System.out.println(ret.size());

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

			List<UserMessage> ret = new ArrayList<UserMessage>();
			try {
				while(rs.next()) {
					int	id = rs.getInt("id");
					String title = rs.getString("title");
					String text = rs.getString("text");
					String category = rs.getString("category");
					Timestamp insert_date = rs.getTimestamp("insert_date");
					String name = rs.getString("name");


					UserMessage usermessage = new UserMessage();
					usermessage.setId(id);
					usermessage.setTitle(title);
					usermessage.setText(text);
					usermessage.setCategory(category);
					usermessage.setInsertDate(insert_date);
					usermessage.setName(name);

					ret.add(usermessage);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}
