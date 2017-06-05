package board.dao;

import static board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.beans.Department;
import board.exception.SQLRuntimeException;

public class DepartmentDao {

	public List<Department> getDepartment(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM board.departments ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Department> ret = toDepartmentList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private List<Department> toDepartmentList(ResultSet rs)
			throws SQLException {

			List<Department> ret = new ArrayList<Department>();
			try {
				while(rs.next()) {
					int	id = rs.getInt("id");
					String name = rs.getString("name");


					Department departments = new Department();
					departments.setId(id);
					departments.setName(name);

					ret.add(departments);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}
