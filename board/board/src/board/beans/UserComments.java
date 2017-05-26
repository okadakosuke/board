package board.beans;

import java.io.Serializable;
import java.util.Date;

public class UserComments implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	private Date insert_date;
	private int message_id;
	private String name;

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

