package board.service;

import static board.utils.CloseableUtil.*;
import static board.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import board.beans.Department;
import board.dao.DepartmentDao;

public class DepartmentService {

	public List<Department> getDepartment() {

		Connection connection = null;
		try {
			connection = getConnection();

			DepartmentDao departmentDao = new DepartmentDao();
			List<Department> departments= departmentDao.getDepartment(connection);

			commit(connection);
			return departments;
		} catch(Error e) {
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

}