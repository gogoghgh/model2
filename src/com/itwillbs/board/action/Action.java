package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 변수 X   상수 O
	// 인스턴스 메서드 X    추상 메서드 O
	// --> 상속을 통해서 추상메서드를 오버라이딩하여 사용 
	//     (for 강제성!! 상속받은 넘들 모두 다 똑같은 형태로 만들어지게,, for 관리 용이!!!)
	
	
	// 도큐먼트 주석쓰^^
	/** 
	 * 1. 추상메서드이며, 반드시 오버라이딩해서 사용해야 함
	 * 2. 실행할 때 request, response 정보를 매개변수로 전달해야만 이 메서드 호출 가능함
	 * 3. 메서드 호출이 완료되면 ActionForward(=주소, 이동 방식이 담긴 객체) 정보를 리턴
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
		// 리턴타입이 ActionForward.. 
		// execute 메서드 실행하면? -> action forward가 생긴다!! == 이동 주소, 방식 지정하는 객체
		// 즉, 어디로 어떻게 갈지에 대한 정보가 생기는 것이다,, 와우 ㅋ
		// 매개변수 = 실행할 때 넣어줘야 함,, request, response 넣어서 execute 실행하면 actionForward가 생긴다!!

}
