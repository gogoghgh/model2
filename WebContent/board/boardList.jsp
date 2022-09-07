<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>/board/boardList.jsp</title>
</head>
<body>
	<h1>/board/boardList.jsp</h1>
	<h2> 가현스 게시판 💖  </h2> 
	<h1 style="color: salmon">공지: 깃허브 닉네임 추천 받아요^^  </h1>
	<%
		// List<BoardDTO> boardList = (List<BoardDTO>)request.getAttribute("boardList"); 
	
		
/* 		// 페이징 처리 정보 전달 (request 영역)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage); */
		
/* 		// ↑ BoardListAction 에서 영역에 저장한 놈들 다 받아와서 변수에 넣기
		String pageNum = (String) request.getAttribute("pageNum");
		int cnt = (int) request.getAttribute("cnt");
		int pageCount = (int) request.getAttribute("pageCount");
		int pageBlock = (int) request.getAttribute("pageBlock");
		int startPage = (int) request.getAttribute("startPage");
		int endPage = (int) request.getAttribute("endPage"); 
		
		request영역 안에 다 들어있고, 인제 EL표현식으로 나타낼거니까  이제 이놈들 필요 없다!!!!!!!!
		
		*/
		
	%>
	
	<h3><a href="./BoardWrite.bo">글쓰기 페이지로 이동</a></h3>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th width="150px">제목</th>
			<th>내용</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>IP</th>
		</tr>
		
	<c:forEach var="dto" items="${requestScope.boardList }">   <!-- 정석^^ requestScope. 생략해도 똑같다 ,,, 
															List에서 꺼낸 데이터는? DTO -> dto.get해서 꺼내기-->
		<tr>
			<td>${dto.bno }</td>
			<td>
				<%-- ↓↓↓↓ <a href="./BoardContent.bo"> ${dto.subject } </a>   --%>  
				<%-- ↓↓↓↓ <a href="./BoardContent.bo?bno=${dto.bno }"> ${dto.subject } </a> --%>  
					<!--  ㄴ 이 링크 눌렀을 때, 몇 번(bno) 글 눌렀는지 그 정보도 같이 보내야 함!!!
								컨트롤러 1단계에서 파라메타까지 넘어가서,, else if 에서 false 걸리는 거 아냐?! 
								아님~~ ?뒤는 안 보임~~~ ㄱㅊ~~    -->
				<a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${requestScope.pageNum}"> ${dto.subject } </a>
						   <!-- ㄴ 그리고,, 주소줄에 pageNum도 같이 보내야,,, 
						   			글 내용 읽고 뒤로 돌아왔을 때, 보던 그 페이지가 보이겠지요??
						   			request 영역에 pageNum 저장해놨으니까 이렇게 삽가능 	
								   -->
			</td>
			<td>${dto.content }</td>
			<td>${dto.name }</td>
			<td>${dto.readcount }</td>
			<td>${dto.date }</td>
			<td>${dto.ip }</td>
		</tr>
	</c:forEach>
		
		
<%-- 		<% 구식,,, EL 로 나타낼거임!!!!!!!!!!!!!!!!!!!!!
			for(int i = 0; i < boardList.size(); i++){
				// boardList 안에 DTO로 저장되어 있으니까 
				// dto 먼저 만들어서 거기서 낱개 요소들 꺼내기
				BoardDTO dto = boardList.get(i);
				%>
				<tr>
					<td> <%=dto.getBno()%> </td>
					<td> <%=dto.getSubject()%> </td>
					<td> <%=dto.getName()%> </td>
					<td> <%=dto.getReadcount()%> </td>
					<td> <%=dto.getDate()%> </td>
					<td> <%=dto.getIp()%> </td>
				</tr>
				<%
			}// for
		%> --%>
		
	</table>
	
	
	
	<!-- EL표현식, JSTL로 이전,, 1 2 3 ,,, 다음 구현 -->
	<c:if test="${cnt != 0 }">
		<c:if test="${startPage > pageBlock }">
			<a href="./BoardList.bo?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
			<a href="./BoardList.bo?pageNum=${i }">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount }">
			<a href="./BoardList.bo?pageNum=${startPage+pageBlock }">[다음]</a>
		</c:if>
	</c:if>
	
</body>
</html>