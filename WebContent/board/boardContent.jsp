<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/boardContent.jsp</title>
</head>
<body>
	<h1> board/boardContent.jsp </h1>
	
	<%
		// 여서 내용 보고-> 답글 버튼 누르면 -> ./BoardReWrite.bo -> reWriteForm.jsp로 가는데,, 
		// 이 페이지 requestScope에 dto는 저장되어있는 상태..
		
		// forward 방식으로 이동해야만,, request 영역에 저장됨.. 그치그치...
		
		BoardDTO dto = (BoardDTO)request.getAttribute("dto");
		System.out.println("(from boardContent.jsp) requestScope에 저장된 dto: " + dto);
		
		// 여기서 session에 dto 저장해볼까???
		session.setAttribute("dto", dto);
		
	%>
	
	
	<table border="1" style="font-size: x-large;">
	<!-- bno name pass subject content / readcount re_ref re_lev re_seq / date ip file -->
		<tr>
			<td>글번호</td>
			<td>${requestScope.dto.bno }</td>
			<td>조회수</td>
			<td>${dto.readcount }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${dto.name }</td>
			<td>작성일</td>
			<td>${dto.date }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${dto.subject } </td> 
			<!-- 제목(colspan = "3" 열 3개 합친거) -->
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3" height="300px"> ${dto.content }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td colspan="3"> ${dto.file }</td>
		</tr>
		<tr>
			<td><input type="button" value="수정" onclick="location.href='./BoardUpdate.bo?bno=${dto.bno}&pageNum=${pageNum }';"></td>
			<td><input type="button" value="삭제"></td>
			<td><input type="button" value="답글" onclick="location.href='./BoardReWrite.bo?bno=${dto.bno}';"></td>
		<!-- <td><input type="button" value="목록" onclick="location.href='boardList.jsp';"></td> 땡!!!! 가상 주소로 부르기-->
		<!-- <td><input type="button" value="목록" onclick="location.href='./BoardList.bo';"></td> 땡... 
			    근데 4페이지에 글 읽고 목록 눌러서 뒤로 갔는데,, 1페이지로 돌아가버림;; 우짜냐 
				pageNum은 어딨지?? boardList에 있습니다~~ .... 
				페이지 정보까지 같이 전달하려면? pageNum 까지 적어주기,,,, boardContentAction에서 request로 저장한 그거? 같이 보냄
			-->
			<td><input type="button" value="목록" onclick="location.href='./BoardList.bo?pageNum=${pageNum}';"></td>
		</tr>
	</table>
	
</body>
</html>