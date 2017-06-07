package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import board.beans.Message;
import board.beans.UserMessage;
import board.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection,String categorybox, String timedate, String finishdate) {



		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM board.user_messages");
			sql.append(" WHERE insert_date");
			sql.append(" BETWEEN");
			sql.append(" ?");
			sql.append(" and");
			sql.append(" ?");
			if(!StringUtils.isEmpty(categorybox)) {
			sql.append(" and");
			sql.append(" category=");
			sql.append(" ?");}
			sql.append(" ORDER BY Insert_date DESC");

			ps = connection.prepareStatement(sql.toString());



			ps.setString(1, timedate);
			ps.setString(2, finishdate);
			if(!StringUtils.isEmpty(categorybox)) {
			ps.setString(3, categorybox);
			}


			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

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
					int	user_id = rs.getInt("user_id");
					String title = rs.getString("title");
					String text = rs.getString("text");
					String category = rs.getString("category");
					Timestamp insert_date = rs.getTimestamp("insert_date");
					String name = rs.getString("name");
					int department_id =rs.getInt("department_id");
					int branch_id =rs.getInt("branch_id");


					UserMessage usermessage = new UserMessage();
					usermessage.setId(id);
					usermessage.setUser_id(user_id);
					usermessage.setTitle(title);
					usermessage.setText(text);
					usermessage.setCategory(category);
					usermessage.setInsertDate(insert_date);
					usermessage.setName(name);
					usermessage.setDepartment_id(department_id);
					usermessage.setBranch_id(branch_id);

					ret.add(usermessage);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
	public List<Message> getCategory(Connection connection) {


		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_messages GROUP BY category ");

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
	private List<Message> toMessageList(ResultSet rs)
		throws SQLException {

		List<Message> ret = new ArrayList<Message>();
		try {
			while(rs.next()) {
				String category = rs.getString("category");

				Message message = new Message();
				message.setCategory(category);

				ret.add(message);

			}
			return ret;
		}finally {
			close(rs);
		}
		}
}
