package com.itwillbs.board.db;

import java.sql.Timestamp;

public class RecommentDTO {
	private int bno;
	private int b_c_bno;
	private String content;
	private String name;
	private Timestamp date;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getB_c_bno() {
		return b_c_bno;
	}
	public void setB_c_bno(int b_c_bno) {
		this.b_c_bno = b_c_bno;
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
		return "RecommentDTO [bno=" + bno + ", b_c_bno=" + b_c_bno + ", content=" + content + ", name=" + name
				+ ", date=" + date + "]";
	}
	
}
