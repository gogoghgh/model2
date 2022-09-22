package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.CommentDTO;


public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from CommentWriteAction) Model: execute() 호출 완");
		
		// 댓글 입력 폼에서 넘어온 값이 한글일 수 있으니 한글 처리 먼저
		request.setCharacterEncoding("UTF-8");
		
//		// 사용자 응답 웹페이지 문자 인코딩
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		
		// 전달된 정보 저장(이름, 내용, bno.. pageNum)
		String pageNum = request.getParameter("pageNum");
		
		CommentDTO cdto = new CommentDTO();
		
		
		cdto.setBno(Integer.parseInt(request.getParameter("bno")));
		cdto.setName(request.getParameter("name"));
		cdto.setContent(request.getParameter("content"));
		
		System.out.println("(from CommentWriteAction) M: 저장할 댓글 정보:" + cdto);
		
		// DB에 댓글 입력 정보 저장
		BoardDAO dao = new BoardDAO();
		dao.insertComment(cdto);
		
		// BoardContent.bo 가려니까 bno== null이라고 자꾸 오류나네 ㄱ- 세션에 넣을까,,,, 주소줄에 붙ㅌ여서 보내야 하나
		// bno 변수에 넣어서 주소줄에 같에 보내자
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 페이지 이동 정보 저장, forward 리턴
		ActionForward forward = new ActionForward();
		
		forward.setPath("./BoardContent.bo?bno=" + bno + "&pageNum=" + pageNum); // 되긴 되네????
		forward.setRedirect(true); // 가상주소로 이동하니까 sendRedirect 방식으로 이동
		
		return forward;
	}

}
