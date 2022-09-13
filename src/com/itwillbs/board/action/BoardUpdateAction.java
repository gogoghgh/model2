package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardUpdateAction) M: execute() 메서드 호출됨");
		
		// 1. 전달된 정보 저장 먼저!!
		// 수정버튼 눌렀을 때, 욜로 전달해준 게 있느냐??? yes!! bno, pageNum
		int num = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
				// => 전달되는 파라메타값의 경우, 
				//    DB 테이블에 저장되는 값이면 ------> DB랑 맞게 형변환 해줘야 함 
				//    DB 테이블에 저장되지 않는 값이면 ->           형변환 굳이 X
		
		// 2. BoardDAO 객체 생성!!! DB 쓸거니까
		BoardDAO dao = new BoardDAO();
		
		// 3. 전달된 글 번호(bno) 바탕으로 해당 글 정보 모~~두 가져오기.. 작성자, 작성일, 내용, 파일,,, 등등
		// dao.getBoard(num);  // 특정 글 1개의 정보 조회하는 메서드,, 리턴값: dto!!!
		BoardDTO dto = dao.getBoard(num);
		System.out.println("(from BoardUpdateAction) M: 수정할 데이터(기존 데이터): " + dto);
		
		// 4. DB에서 가져온 정보를 request 영역에 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		// 5. 페이지 이동(View 하나 만들어서,, 보여주면 된다! ./board/updateForm.jsp)
		ActionForward forward = new ActionForward(); // 통행권 객체 만들고
		forward.setPath("./board/updateForm.jsp"); // 어디로 갈건지
		forward.setRedirect(false); // .jsp 페이지로 가고,, request 영역에 데이터 담았으니까,,,, 무조건 forward 방식!!!  
		
		return forward;
	}

}
