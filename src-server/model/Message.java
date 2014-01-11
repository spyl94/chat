package model;

import java.io.Serializable;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -152406011673390266L;
	private String content;
	private User author;

	public Message(User author, String content) {
		this.author = author;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}
}
