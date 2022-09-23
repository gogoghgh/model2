package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from FileBoardWriteAction) execute 메서드 호출됨");
		
		// 첨부파일 처리(업로드)
		// WebContent > upload 폴더 생성 ,, 실제 서버에서 올린 파일은 다른 데에 저장됨,,,,,
		// 파일의 실제 위치(서버의 어디에 저장되는가?)
		String realPath = request.getRealPath("/upload");
		System.out.println("realPath:" + realPath);
		
		// 업로드 파일의 크기 지정 << HDD 용량은 무제한이 아니닉하^^
		int maxSize = 10 * 1024 * 1024; // 10MB
				//   10byte   KB    MB
		
		// 파일 업로드 --> MultipartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(
									request, // fWriteForm에서.. request -> multipart로 바꿨고, (enctype="multipart/form-data")
											// request를 여기에 넣었기 때문에
											// request 대신 multi로 받을 수 있다~~~
									realPath, // 여기에 받아올거고
									maxSize, // 파일 크기
									"UTF-8", // 인코딩
									new DefaultFileRenamePolicy() // 파일 이름 재정의,, 
									);
		
		System.out.println("(from FileBoardWriteAction) 파일 업로드 성공쓰");
		
		// 한글 처리 (생략^^ 안 해도 됨)
		
		// 전달된 파라메타 인제야~~ 저장(DTO에)
		// DTO 생성 -> 정보들 저장
		BoardDTO dto = new BoardDTO();
		
		// request로 못 받음!! request ->  multipart로 바까버려서!!! 
/*		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setFile(request.getParameter("file"));*/
		
		dto.setName(multi.getParameter("name"));
		dto.setPass(multi.getParameter("pass"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setIp(request.getRemoteAddr()); // 얘는 왜 request 써요? ㄱ- ㅇㅒ는 파라메타에서 받아오는 애가 아니잖아 ㅋ
//		dto.setFile(multi.getParameter("file")); file은 multi.getPara로 못 받습니데이~
		dto.setFile(multi.getFilesystemName("file"));
		// ㄴ1. 서버에 올라가는 파일명
//		dto.setFile(multi.getOriginalFileName("file"));
		// ㄴ2. 파일 고유의 이름
		// 두 개 같지 않나요??  
		// 노우노우 1.서버에 올라가는 파일명은 중복된 것까지 다 고려해서^^  
		
		
		// DB에 첨부파일 글쓰기 메서드 boardWrite(dto) 호출
		// 실제 파일은 서버!에 저장(그 긴~~~ 주소), 파일의 이름만 DB에 저장~~
		BoardDAO dao = new BoardDAO();
		dao.boardWrite(dto);
		
		// 페이지 이동 정보 저장 + forward 리턴
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
