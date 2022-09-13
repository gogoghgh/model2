package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardWriteAction implements Action {
					// == pro 페이지
								// Action 인터페이스 상속받도록!!!
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 이 안에서만,, 구현하도록!!!!!!! 
		// 왜욤?? java에서는 request란 객체 못 씀,, jsp가 아니좌나요
		// 근데 이 메서드에선 매개변수로 request, response를 받아오니까,,, 사용 가넝한!!
		// jsp 페이지에서 -> java한테 request, response를 줄 수 있나?? 예스,,,, 
		// how? 컨트롤러를 통해서!!!!!!!
		// 컨트롤러!!!(=서블릿)가  jsp   -> 컨트롤러   -> java(class) 둘이를 연결해줌
		//                        View     Controller   Model(action...)
		// doProcess 메서드(in BoardFrontController!!!)에도 매개변수로 request, response 있음,, 
		
		System.out.println("(from BoardWriteAction) Model: execute() 호출 완");
		// 1. 폼태그(writeForm.jsp)에서 글 쓰고,, submit 했으니 한글 데이터 넘어옴 -> 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달된 정보 저장(제목, 비밀번호, 이름, 내용)
		//    ㄴ하기 위해,, DB 테이블 만들고 -> Bean(DTO) 만들고,,, 올게요,,,
		// BoardDTO 객체 생성 & dto 필통에 form에서 넘어온 애들 담아주기
		BoardDTO dto = new BoardDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		// IP 주소 추가
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("(from BoardWriteAction) Model: " + dto);
		
		// 3. DB에 정보 저장
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// DB에 글 정보 저장
		dao.boardWrite(dto);
		
		// 4. 페이지 이동 정보 저장 + forward 리턴 .... 여기서 이동하는 게 XXX !! 이동은 컨트롤러에서
		ActionForward forward = new ActionForward();
		// forward 객체 만들고 어디로 갈건지, 어떻게 갈건지 정해주기
		forward.setPath("./BoardList.bo"); // 글 다 썼으니, 게시판 목록으로 이동하것다,, 가상주소 적어주고
		forward.setRedirect(true); 
						// true -> sendRedirect()   /  false -> forward()
						//  주소 바뀜+화면도 바뀜   /  주소는 그대로 + 화면만 바뀜
		return forward;
	}
	
}
