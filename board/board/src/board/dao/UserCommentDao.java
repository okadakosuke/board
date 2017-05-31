package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import board.beans.UserComments;
import board.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComments> getUserComments(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM board.user_comments ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComments> ret = toUserCommentList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private List<UserComments> toUserCommentList(ResultSet rs)
			throws SQLException {

			List<UserComments> ret = new ArrayList<UserComments>();
			try {
				while(rs.next()) {
					int id = rs.getInt("id");
					int	user_id = rs.getInt("user_id");
					String text = rs.getString("text");
					Timestamp insert_date = rs.getTimestamp("insert_date");
					int message_id = rs.getInt("message_id");
					String name = rs.getString("name");
					int department_id =rs.getInt("department_id");
					int branch_id =rs.getInt("branch_id");

					UserComments usercomment = new UserComments();
					usercomment.setId(id);
					usercomment.setUser_id(user_id);
					usercomment.setText(text);
					usercomment.setInsertDate(insert_date);
					usercomment.setMessage_id(message_id);
					usercomment.setName(name);
					usercomment.setDepartment_id(department_id);
					usercomment.setBranch_id(branch_id);

					ret.add(usercomment);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}
