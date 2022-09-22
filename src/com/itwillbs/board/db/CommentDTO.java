package com.itwillbs.board.db;

import java.sql.Timestamp;

public class CommentDTO {
	private int c_bno; // pk
	private int bno; // board Table의 pk
	private String content;
	private String name;
	private Timestamp date;
	
	public int getC_bno() {
		return c_bno;
	}
	public void setC_bno(int c_bno) {
		this.c_bno = c_bno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
		return "CommentDTO [c_bno=" + c_bno + ", bno=" + bno + ", content=" + content + ", name=" + name + ", date="
				+ date + "]";
	}
	
	
}
