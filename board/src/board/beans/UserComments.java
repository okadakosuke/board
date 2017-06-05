package board.beans;

import java.io.Serializable;
import java.util.Date;

public class UserComments implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private String text;
	private Date insert_date;
	private int message_id;
	private String name;
	private int department_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	private int branch_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id =id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}

	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

