package com.itwillbs.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardListAction) Model: execute 메서드를 호출하셨나요^^");
		
		// DB에 대한 처리 할 거니까 DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 게시판에 작성되어 있는 전체 글 개수 (<- DAO 가서 getBoardCount 메서드 만들고 오기)
		// cnt 변수에 저장
		int cnt = dao.getBoardCount();
		
		// 페이징 처리 -----------------------------------------------------------------------------
		// 모르겠으면 외우기~~ ^_ㅠ

		// http://localhost:8080/Model2/BoardList.bo?pageNum=2&pageSize=7
												// 2페이지      한 페이지에 글 7개씩 보여도
		
		// 한 페이지에 보여줄 글의 개수 설정
		String urlpageSize = request.getParameter("pageSize"); // 네이버 쇼핑처럼 ㅎ
			if(urlpageSize == null) {
				urlpageSize = "20"; // 기본값은 10개 -> 2
			}
		
	  //int pageSize = 5; // 내가 개수 딱 정해놓는거    // 구
		int pageSize = Integer.parseInt(urlpageSize);   // 신
		
		// 현 페이지가 몇 번째 페이지인지 계산...
			// 기존 페이지 정보가 없을 경우에는 항상 1페이지!로 설정할 거
		String pageNum = request.getParameter("pageNum"); // 파라메타로 받아오네??? 페이지 움직일 때 페이지 넘 파라메타 보내야한다~
									// http://localhost:8080/Model2/BoardList.bo?pageNum=2 하면 2페이지 보이네?? 우와
									// 쇼핑몰 20개씩 보기, 40개씩 보기,, 이것도 파라메타 이렇게 변함
		if(pageNum == null){
			pageNum = "1";
		}
		
		// 시작행 번호 계산
			// 첫 페이지 젤 첫번째 글 = 1번, 
			// 두번째 페이지 젤 첫번째 글 = 11번,  
			// 세번째 페이지 젤 첫번째 글 = 21번,  
			// 네번째 페잉지 젤 첫번째 글 = 31번,, 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		int currentPage = Integer.parseInt(pageNum); // String 타입인 pageNum -> int로 바꿈
		
		int startRow = (currentPage -1) * pageSize + 1;
					//  ( 1page - 1 ) * 10 + 1 === 1
					//  ( 2page - 1 ) * 10 + 1 === 11
					//  ( 3page - 1 ) * 10 + 1 === 21 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		
		
		// 끝행 번호 계산
			// 첫 페이지 젤 막 글 = 10번, 
			// 두번째 페이지 젤 막 글 = 20번,  
			// 세번째 페이지 젤 막 글 = 30번,  
			// 네번째 페잉지 젤 막 글 = 40번,, 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		int endRow = currentPage * pageSize;
					// 1page * 10 === 10
					// 2page * 10 === 20
					// 3page * 10 === 30 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		
		// DB에서 들고올 때,, 한방에 들고 오는 거 vs 10개씩 나눠서 따로 따로 들고 오는 거
		//                                       <<<< 
		
		
		
		
		// 페이징 처리 -----------------------------------------------------------------------------
		
		
		
		
		
		// dao 메서드 중에서 게시판 글 정보 모두 가져오는 메서드 호출
//		List<BoardDTO> boardList = dao.getBoardList();
		
		// 모두 가져오지 말고,, 나눠서 가져오기 위한 메서드
		List<BoardDTO> boardList = dao.getBoardList(startRow, pageSize); // 메서드 오버로딩!! 매개변수 다르게 하나 더 만들고 오자
		
		System.out.println("(from BoardListAction) Model: 게시판 글 정보 저장 완"); ///???????????
		
		
		
		
		// 페이징 처리2 (하단 페이지 링크... 이전, 다음,, 1 2 3페이지ㅡ,,,,,) ----------------------------
		
		// 전체 페이지 수 계산
		// ex. 전체 글 50개 -> 한 페이지에 10개씩 출력하면?  5개 페이지 필요함
		//     전체 글 55개 -> 한 페이지에 10개씩 출력하면?  6개 페이지 필요함 50 + 5
		
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1 ); // js에서 사과 박스 구하던 로직,,이랑 똑같
											// 나머지 없으면 0 더하고 : 나머지 있으면 1페이지 더하고
		
		// 한 화면에 보여줄 페이지 수(= 페이지 블럭) 몇 개 할거냐??? 정하기     이전 1 2 3 4 5.. 9 10 다음
		int pageBlock = 5; // 10 -> 3개로 줄임,,,,, 이전 다음 때메 
		
		// 페이지 블럭의 시작 번호.. 1~10번 페이지블럭     11~20 페이지 블럭    21~30 페이지 블럭
								    //  시작 번호: 1          11                   21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
					//       1  -       1  /     10     *     10    + 1  === 1
					//       3  -       1  /     10     *     10    + 1  === 1     int니까 나머지 버려서,, 1~10까지는 다 0
					//      11  -       1  /     10     
		
		// 페이지 블럭의 끝번호 
			// 1~10 페이지블럭 끝번호: 10
			// 11~20 페이지블럭 끝번호: 20
			// 21~30 페이지블럭 끝번호: 30
		int endPage = startPage + pageBlock -1;
		
		// 총 페이지 수와 ---  페이지 블럭 끝번호 비교 ??????????
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		// 페이징 처리2 (하단 페이지 링크... 이전, 다음,, 1 2 3페이지ㅡ,,,,,) ---------------------------- 끝
		
		
		// 댓글 개수

		List<Integer> cmtList = new ArrayList<>();
		
		for(int i = 0; i < boardList.size(); i++){
			int bno = boardList.get(i).getBno();
			System.out.println(bno);
			System.out.println(dao.getCommentCount(bno));
			cmtList.add(dao.getCommentCount(bno));
		}
		
		request.setAttribute("cmtList", cmtList);

		
		
		// Model(지금 여기.. Action) -> view 페이지로 boardList 정보 전달을 위해, request 영역에 저장
		request.setAttribute("boardList", boardList);
		System.out.println("(from BoardListAction) Model: BoardList 정보 request 영역에 저장 완");
		
		// + 페이징 처리 정보 전달을 위해 request 영역에 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		System.out.println("(from BoardListAction) Model: 페이징 처리 정보 request 영역에 저장 완");
		
		
		
		
		
		// 받아온 list.. 화면에 출력하기!!  <<????????????
		// 페이지 이동(화면 전환)하기 위해 ActionForward 객체 생성
		ActionForward forward = new ActionForward();
//		forward.setPath("./board/boardListAll.jsp"); // 갈 곳, 어떻게 갈지    구
		forward.setPath("./board/boardList.jsp"); // 갈 곳, 어떻게 갈지       신
						// .jsp 찐주소니까 WebContent/board/boardListAll.jsp
		forward.setRedirect(false); // 화면만 바뀌는 forward 방식으로 갈거니까 false
		
		
		return forward;
	}
	
}
