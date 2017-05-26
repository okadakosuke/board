package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Branch;
import board.exception.SQLRuntimeException;

public class BranchDao {

	public List<Branch> getBranch(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM board.branches ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchList(rs);
			System.out.println(ret.size());

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private List<Branch> toBranchList(ResultSet rs)
			throws SQLException {

			List<Branch> ret = new ArrayList<Branch>();
			try {
				while(rs.next()) {
					int	id = rs.getInt("id");
					String name = rs.getString("name");


					Branch branches = new Branch();
					branches.setId(id);
					branches.setName(name);

					ret.add(branches);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}
