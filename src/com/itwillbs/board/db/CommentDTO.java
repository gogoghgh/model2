package com.itwillbs.board.db;

import java.sql.Timestamp;

public class CommentDTO {
	private int bno;
	private int b_bno;
	private String content;
	private String name;
	private Timestamp date;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getB_bno() {
		return b_bno;
	}
	public void setB_bno(int b_bno) {
		this.b_bno = b_bno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [bno=" + bno + ", b_bno=" + b_bno + ", content=" + content + ", name=" + name + ", date="
				+ date + "]";
	}
	
	
}
