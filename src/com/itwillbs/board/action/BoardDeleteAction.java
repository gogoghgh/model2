package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardDeleteAction) M: BoardDeleteAction 호출됨");
		
		// 전달된 데이터 저장 (pass, bno, pageNum) 먼저!!!!!!!!!!!!!!
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
		// dto에 저장하기,, 메서드 부를 때 dto에 담아서 그거 보낼거라소
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setPass(pass);

		// BoardDAO 객체 만들고(DB 쓰니까) -> 글삭 메서드 실행^^ 
		BoardDAO dao = new BoardDAO();
		
		int result = dao.deleteBoard(dto);
		
		// 삭제 결과 (-1, 0, 1) 에 따른~~~
		// 페이지 이동 (not 컨트롤러 이동 ~  but js 통한 이동 !!!)
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (result == -1) {
			out.println("<script>");
			out.println("alert('게시판 글 없음..👼');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return null; // 왜 return null 하남? 자스로 동작하고, 여기서 끝나도록!! 
						// 밑에 else if랑,, 최종 성공 시 result 결과,,,이렇게 안 내려가도록^^ 
		} else if (result == 0 ) {
			out.println("<script>");
			out.println("alert('비번 틀림..👼 try again~~');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		} else {
			// result == 1 == 삭제 성공
			out.println("<script>");
			out.println("alert('삭제 완 🤴🤶');");
			out.println("location.href='./BoardList.bo&pageNum=" + pageNum + "';");
			out.println("</script>");
			out.close();
			
			return null;
			
		}
		
	}// execute

}
