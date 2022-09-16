package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("(from BoardContentAction) Model: execute() 메서더 호출됨");
		
		// 파라메타에서 넘어온,, 전달된,, 정보 (bno, pageNum) 저장
		// => 전달되는 파라메타값의 경우, 
		//    DB 테이블에 저장되는 값이면 ------> DB랑 맞게 형변환 해줘야 함 
		//    DB 테이블에 저장되지 않는 값이면 ->           형변환 X
		int bno = Integer.parseInt(request.getParameter("bno"));  // ... DB에 int로 넘겨야 하니까
		String pageNum = request.getParameter("pageNum"); // 얘는 또 넘기면 String으로 넘어갈텐데 왜 굳이 int로 하나요 ㅎ
		
		// BoardDAO 객체 생성하고,, (for 조회수 증가 메서드 쓸라고,,, DB 쓸라고,,)
		BoardDAO dao = new BoardDAO();
		
		// 조회수 1 증가시키기!!! - updateReadcount() 메서드 만들고 옵시다,,, 
		dao.updateReadcount(bno);
		System.out.println("(from BoardContentAction) Model: 조회수 +1 완");
		
		
		// 게시판 글 1개의 정보를 가져와서 출력 == 멤버 1명 정보 출력했었던 거랑 똑같음!! 그치그치,,
		// getBoard(bno) 메서드 (리턴타입: dto) 만들고 옵시다
		BoardDTO dto = dao.getBoard(bno);
		
		
		// Model에서 객체 정보 출력 X 
		// view에서 정보 출력 O!!!!! 보여주니까 view니까,,
		// so.. ==> model 객체(지금,, 여기,, action 페이지)에 있는 정보를  --> view로 이동시키자!!!!!!!
		
		// request 영역에 저장
		request.setAttribute("dto", dto); 				// 1번 방법 // 지금 이 객체 수정할 때,, 추가하거나 변경하거나,,, 
														//              DB에는 없지만 DTO에는 있는 값이 있을 때,,
//		request.setAttribute("dto", dao.getBoard(bno)); // 2번 방법 // DB 데이터 전달만 할 때
														// 1<< 2번 방법 더 많이 씀!!!
		
		request.setAttribute("pageNum", pageNum); // 출력할 때 사용
		
			// 확인용,, ㅠ
			System.out.println("(from BoardContentAction) request영역에 저장된 dto: " + dto);
		
		
		// 데이터 다 만들었고, request에 저장까지 해놨으니,, 인제 이 페이지에서 할 거 없음.. 나가자~~
		// 출력할 view 페이지로 이동
		//   이동할거니까 통행권 객체 생성 (어디로 갈지, 어떻게 갈지)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false); 
			// redirect로 갈래 forward로 갈래? 
			// forward,, 주소 뭔지 몰라도 상관 없음 ㅋ 글고 주소창에 .jsp 보이면 안 됨,, 숨겨야 해~~~ 막아야 해~~
			// 아 그리고 request 영역에 저장했으니까 이 request 영역 정보 가져가려면 forward뿐이니까!!!!!
			// request 영역에 뭔가 저장한 순간,, 무조건 forward로 이동해야 함!!!!!!!!!!!! ★★
		
		return forward; // execute메서더 리턴타입은 ActionForward^^ 잊지 말자
	}

}
