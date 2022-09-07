<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>/board/boardListAll.jsp</title>
</head>
<body>
	<h1>/board/boardListAll.jsp</h1>
	<h2> 깃허브 닉네임 추천 받아요^^ </h2>
	<%
	// request.setAttribute("boardList", boardList);
	// Action = Model 에서 이렇게 저장했으니까 그거 get으로 꺼내기
// 		List<BoardDTO> boardList = request.getAttribute("boardList");
		// Type mismatch: cannot convert from Object to List<BoardDTO>
		List<BoardDTO> boardList = (List<BoardDTO>)request.getAttribute("boardList");
	
		
/* 		// 페이징 처리 정보 전달 (request 영역)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage); */
		
		// ↑ BoardListAction 에서 영역에 저장한 놈들 다 받아와서 변수에 넣기
		String pageNum = (String) request.getAttribute("pageNum");
		int cnt = (int) request.getAttribute("cnt");
		int pageCount = (int) request.getAttribute("pageCount");
		int pageBlock = (int) request.getAttribute("pageBlock");
		int startPage = (int) request.getAttribute("startPage");
		int endPage = (int) request.getAttribute("endPage");
		
	
	
		
	%>
	
	<h3><a href="./BoardWrite.bo">글쓰기 페이지로 이동</a></h3>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>IP</th>
		</tr>
		<%
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
			}
		%>
	</table>
	
	
	<%
		// 하단 페이징 처리    이전 1 2 3 ..  9 10 다음
		if (cnt != 0) {
			
			// 이전 :   직전 페이지 블럭!!의 첫번째 페이지 호출
			if(startPage > pageBlock) {
				// 5번   >   3개        5 6 7
				%>
					<a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
				<%							// << 4 5 6 중 5번 페이지라고 가정하면, 
											// startpage는 4, pageblock은 3
											// 이전 누르면? 4-3 = 1페이지로 가서  1 2 3 이렇게 보임
													
													// << 9 10 11 중 11페이지 보고 있으면
													// startpage는 9, pageblock은 3
													// 이전 누르면? 9-3 = 6페이지로 가서    6 7 8 보임
			}
			
			
			// 1  2  3  4  5  ,,,,  9  10
			for (int i = startPage; i <= endPage; i++) {
				%>
					<a href="./BoardList.bo?pageNum=<%=i%>">[<%= i  %>]</a>
				<% 
			}// for
			
			
			// 다음
			if (endPage < pageCount) {
				%>
					<a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
				<%									// 1페이지에 있음.. 1 + 3개 => 4페이지로 간다
			}// if
			
		}// 젤 밖 if
		
		// 얘네를, ,, , , EL 표현식이랑 반복문은 JSTL로 바꾸기^^...
	
	%>
	
	
</body>
</html>