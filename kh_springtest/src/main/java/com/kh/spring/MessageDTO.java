package com.kh.spring;

import java.sql.Timestamp;

// domain, Message
public class MessageDTO {
	private int seq;
	private String name;
	private String message;
	private Timestamp writedate;
	
	public MessageDTO() {}

	public MessageDTO(int seq, String name, String message, Timestamp writedate) {
		super();
		this.seq = seq;
		this.name = name;
		this.message = message;
		this.writedate = writedate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	@Override
	public String toString() {
		return "MessageDTO [seq=" + seq + ", name=" + name + ", message=" + message + ", writedate=" + writedate + "]";
	}
	
	
}
