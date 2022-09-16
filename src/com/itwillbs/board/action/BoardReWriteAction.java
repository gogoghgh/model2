package com.itwillbs.board.action;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;


public class BoardReWriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardReWriteAction) M: execute() 실행됨");
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2.전달되는 파라미터 정보저장 ->MemberDTO생성
		BoardDTO dto = new BoardDTO();
		//hidden으로 가져온 거 저장
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		//답글 작성한,, 입력값 저장
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		//3.BoardDAO객체생성
		BoardDAO dao = new BoardDAO();
		dao.reInsertBoard(dto);
		
		//4.페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}