package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommentUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from CommentUpdateAction) M: execute() 메서드 호출됨");
		
		// 1. 전달된 정보 저장.. 수정버튼 눌렀을 때 일로 전달해준거: bno, 
		
		
		
		return null;
	}

}
