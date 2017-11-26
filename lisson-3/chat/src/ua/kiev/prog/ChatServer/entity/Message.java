package ua.kiev.prog.ChatServer.entity;

import java.util.Date;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;

	Message(){

	}

	public static class Builder {

		Message instance = new Message();

		public Message.Builder setFrom(String from) {
			instance.from = from;
			return this;
		}

		public Message.Builder setTo(String to) {
			instance.to = to;
			return this;
		}

		public Message.Builder setText(String text) {
			instance.text = text;
			return this;
		}

		public Message build() {
			return instance;
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date)
				.append(", From: ").append(from).append(", To: ").append(to)
				.append("] ").append(text)
                .toString();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
