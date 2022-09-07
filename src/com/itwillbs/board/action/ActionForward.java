package com.itwillbs.board.action;

public class ActionForward {
	// 페이지 이동을 위한 정보를 저장하는 객체
	// 통행권임.. 얘가 없으면 못 감.. 이동 못 함
	// 어디로(path), 어떻게 갈건지(isRedirect- t|f) 정보가 있어야 갈 수 있좌나요

	private String path; // 이동할 주소
	private boolean isRedirect; // 이동할 방식
			// true라면, sendRedirect() 방식으로 이동시킬 것임
			// false라면, forward() 방식으로 이동시킬 것임

	// get set
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}