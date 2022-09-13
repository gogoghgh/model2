package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8088/Model2/board ㄴㄴ
// http://localhost:8088/Model2/board2 ㄴㄴ
// http://localhost:8088/Model2/test.bo 
// http://localhost:8088/Model2/BoardWrite.bo  글 쓰기 페이지 
// http://localhost:8088/Model2/BoardList.bo   글 전체 목록 조회 페이지

public class BoardFrontController extends HttpServlet{
			// 컨트롤러 == 얘는 서블릿!!!!!!!!
	
	/////////doProcess 시작///////////////////////////////////////////////////
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from BoardFrontController.doProcess) doProcess() = doGet + doPost 실행");

		System.out.println("\n1. 가상 주소 계산 시작-----------");
		// 1. 가상 주소 계산 --------------------------------------------
//		request.getRequestURL(); // 풀 주소,, http부터~~ .bo까지
//		request.getRequestURI(); //  /Model2/test.bo 
								// 프로토콜 X, 포트번호도 X..
								// 같은 프로젝트니까 Model2도 똑같네,, 없애고 /test.bo 이렇게만^^
		// 변수에 담아주기
		String requestURI = request.getRequestURI();
		System.out.println("(from BoardFrontController.doProcess) C: requestURI: " + requestURI);
		
		String ctxPath = request.getContextPath(); // 프로젝트 = 컨텍스트
		System.out.println("(from BoardFrontController.doProcess) C: contextPath: " + ctxPath);
		
		// ctxPath 길이만큼 자르고, 나머지를 가져와랏~~
		String command = requestURI.substring(ctxPath.length());
		System.out.println("(from BoardFrontController.doProcess) C: command: " + command);
		// 1. 가상 주소 계산 끝 -----------------------------------------
		System.out.println("1. 가상 주소 계산 끝-----------\n");

		
		System.out.println("\n2. 가상 주소 매핑 시작-----------");
		// 2. 가상 주소 매핑 -----------------------------------
		Action action = null; // ~~~Action (= process.. = Model!!!) 중 젤 대가리!!!!!! 인터페이스
		// 3)
		ActionForward forward = null; // 미리 선언,, 왜냐면 이 변수(통행권) 여러 번 쓸거라서
		
		// 1)
		if(command.equals("/BoardWrite.bo")){
			// ★★ 패턴1 ★★
			// 이 주소랑 똑같으면 if문 안으로 들어오세용
			// 글쓰기 페이지 보여주기 = 글쓰기 페이지로 이동하기,, 티켓(=forward) 필요!! 
			// (= member CRUD에서 insertForm.. DB 필요 X => view로 보내주면 됨)
			// 2) 잠깐~~~~ ActionForward 만들고 옵시다,,,,

			System.out.println("(from BoardFrontController.doProcess) C: /BoardWrite.bo 주소 호출됨");
			System.out.println("(from BoardFrontController.doProcess) C: DB 정보 필요 없음 -> view 페이지로 이동시킬거임");
			
			// 4) 객체 생성해주기~ = 통행권 티켓 만들기~ -> 티켓 안에 정보 저장해주기
			forward = new ActionForward();
//			forward.setPath(path); // 어디로 갈거고
//			forward.setRedirect(isRedirect); // 어떻게 갈건지
			forward.setPath("./board/writeForm.jsp");
				// 찐 주소네? WebContent/board/writeForm.jsp 페이지로 가라~
			forward.setRedirect(false); // Redirect 방식이냐? ㄴㄴ 아니다~ =forward방식으로 갈 것이다~~~
			
			// 이동 완?? ㄴㄴㄴ 아직 안 갔고,, 티켓만 만들었음!!!
			// 이동하러 3단계로 고고
		}// if --- 패턴1 --- /BoardWrite.bo
		else if(command.equals("/BoardWriteAction.bo")){
			// ★★ 패턴2 ★★
			//   /BoardWrite.bo 주소가 호출된 게 아니라~~
			//   /BoardWriteAction.bo (=pro페이지) 얘가 호출됐으면~~~
			System.out.println("(from BoardFrontController.doProcess) C: /BoardWriteAction.bo 호출");
			System.out.println("(from BoardFrontController.doProcess) C: DB 작업 O + 페이지 이동 O");
															//~~Action == pro 페이지니까!!
															// DB에 글 쓴 거 insert 해야 함,,
			// BoardWriteAction() 객체 생성 + 예외 처리,, ㅋ
//			BoardWriteAction bwAction = new BoardWriteAction();
			action = new BoardWriteAction(); // 인제 이렇게 upcasting해서 씁시다,,
			//  上          下
			
			try {
//				bwAction.execute(request, response);
				// 글 쓰는 과정 처리를 위해,, BoardWriteAction 가서 실행하고 온나~~
				
				// ㄴ execute 메서드 실행 결과 -> forward 갖고 옴!!
//				forward = bwAction.execute(request, response);
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// else if  -- 패턴2 -- /BoardWriteAction.bo
		else if (command.equals("/BoardList.bo")) {
			// ★★ 패턴3 ★★
			System.out.println("(from BoardFrontController.doProcess) C: /BoardList.bo 호출");
			System.out.println("(from BoardFrontController.doProcess) C: DB 정보 필요 O, 페이지 이동 X, 기존 페이지에 출력은 O");
			
			// BoardListAction(=Model) 객체 생성하러 고우고우
			// 객체 생성하고 + 예외처리까지
//			BoardListAction listAction = new BoardListAction();
			action = new BoardListAction(); // upcasting
			
			try {
				System.out.println("(from BoardFrontController.doProcess) C: 해당 Model 객체 호출할 거");
//				forward = listAction.execute(request, response);
				// ★ execute메서드 리턴 타입이 ActionForward니까
				// AF 타입 forward 변수로 받아주기!!!!!!!!!
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// else if --- 패턴3 --- /BoardList.bo
		else if(command.equals("/BoardContent.bo")){
			// 제목 누르면 내용 보러 가도록~~
			System.out.println("(from BoardFrontController.doProcess) C: /BoardContent.bo 호출");
			System.out.println("(from BoardFrontController.doProcess) C: DB 정보 필요 O, 페이지 이동 X, 기존 페이지에 출력은 O"); 
			// 패턴3이다!!!!!!!!!!!!!
			
			// BoardContentAction 객체 만들기,, -> execute메서드 불러서 + forward에 정보 담기
			action = new BoardContentAction(); // action 상속받고 있으니까, upcasting 쌉가능
			
			try {
				// forward 변수에 미리!!! 담아놓고 시작,, 까먹을 수 있잖아염
				forward = action.execute(request, response);
					// execute 날려서~~~~ 할 거 다 하고 통행권 정보 갖고 왔음,,
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// else if --- 패턴3 --- /BoardContent.bo 
		
		
		// 2. 가상 주소 매핑 끝 -----------------------------------
		System.out.println("2. 가상 주소 매핑 끝-----------\n");

		
		System.out.println("\n3. 가상 주소 이동 시작-----------");
		// 3. 가상 주소 이동 시작 ----------------------------
		if(forward != null){
			// 페이지 이동 정보가 있을 때!! = 통행권 있는 사람들 중에서,
			
			if(forward.isRedirect()){
				// isRedirect가 True일 때, sendRedirect() 방식으로 이동
					// Is Redirect?? = Redirect냐?? 예스!! True!!
				System.out.println("(from BoardFrontController.doProcess) C: isRedirect? true -> " + forward.getPath()+"로 이동, sendRedirect() 방식으로");
				response.sendRedirect(forward.getPath());
				
			} else {
				// isRedirect가 False일 때, forward() 방식으로 이동
					// Is Redirect?? = Redirect냐?? 노!! 거짓!!
										// 
				System.out.println("(from BoardFrontController.doProcess) C: isRedirect? false -> " + forward.getPath()+"로 이동, forward() 방식으로");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
		}
		// 3. 가상 주소 이동 끝 ----------------------------
		System.out.println("3. 가상 주소 이동 끝-----------\n\n");
		
	}
	/////////doProcess 끝///////////////////////////////////////////////////
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from BoardFrontController.doGet) C: doGet() 실행");
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from BoardFrontController.doPost) C: doPost() 실행");
		doProcess(request, response);
	}
	
}