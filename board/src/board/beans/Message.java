package board.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;;
	private String text;
	private String categoly;
	private Date insert_date;
	private int user_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategoly() {
		return categoly;
	}

	public void setCategoly(String categoly) {
		this.categoly = categoly;
	}

	public Date getInsert_Date() {
		return insert_date;
	}

	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}

	public int getUser_id() {
		return id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
