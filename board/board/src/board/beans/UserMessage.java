package board.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String text;
	private String category;
	private Date insert_date;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id =id;
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

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

