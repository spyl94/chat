package model;

public class Message {
	
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
