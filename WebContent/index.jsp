<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Model2/index.jsp</title>
</head>
<body>
	<%
		// ★★MVC 프로젝트에서는 index.jsp페이지 외 절대 jsp 페이지 실행시키지 마시오★★
			// 와이?? 주소가 달라서,, ㅋ index로 실행하면 가상 주소 떠있고
			// jsp 페이지로 실행하면 .jsp 찐 주소 보임 ==> MVC 패턴 망한 거임~~ 깨진 거
			
		// MVC 프로젝트 실행 시작페이지
			// 내가 가고싶은 주소 여기에 적어서 실행시키삼
// 		response.sendRedirect("./test.bo");
						// == Model2/test.bo
	
		
		// 글쓰기 페이지 이동
// 		response.sendRedirect("./BoardWrite.bo");
						// == Model2/BoardWrite.bo
		
		// 글 리스트 페이지 이동
// 		response.sendRedirect("./BoardList.bo");
						// == Model2/BoardList.bo
		
	%>
	
<!-- 	<a href="./Main.me"><h2>메인</h2></a> -->
<!-- 	<a href="./MemberLogin.me"><h2>로그인</h2></a> -->
	<a href="./BoardWrite.bo"><h2>글쓰기</h2></a>
	<a href="./BoardList.bo"><h2>글목록</h2></a>
	
</body>
</html>