<%@page import="java.util.List"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.board.db.CommentDTO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/boardContent.jsp</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script type="text/javascript">
// 	$(document).ready(function(){
		
<%-- 		<% --%>
// 		// 댓글 작업 시작,,, 객체 생성 해야 되겠지,,?
// 		CommentDTO cdto = new CommentDTO();
<%-- 		%> --%>

		
// 		// 1.댓글 적고 버튼 클릭하면 
// 		// -> 2.해당 함수로 ajax가 실행되어
// 		// -> 3. 페이지 이동 없이!! controller로 접속 가능하게 되고
// 		// -> 4. data 전달 성공 시 
// 		// -> 5. reload를 통해 실시간으로 댓글이 작성되는 모습을 기대할 수 있다,,
// 		$("#cmtCnt-btn").click(function(){
		
// // 			alert("정상 작동합니더");
// 		// 1.댓글 적고 버튼 클릭하면 
<%-- 	<%--  --%>
<%-- 			나중에 로그인 처리할 때 이것도 넣자~~ --%>
<%-- 			로그인 안 한 사람이 댓글 쓰려고 하면,, 안 되게 제어 --%>
<%-- 			<% --%>
<%-- 				if(id==null){ --%>
<%-- 			%>  --%>
<%-- 			alert("로그인이 필요합니다"); --%>
<%-- 			location.href="/login.bo"; --%>
<%-- 			<% --%>
<%-- 				} else { --%>
<%-- 			%>  --%>
			
<%-- 	--%> --%>
// 			// -> 2.해당 함수로 ajax가 실행되어
// 			$.ajax({
// 				// -> 3. 페이지 이동 없이!! controller로 접속 가능하게 되고
// 				url: "/commentIn.bo",
// 				type:"POST",
// 				data:{
<%-- 					num: '<%=cdto.getB_bno() %>', --%>
// 					content:$("#cmtCnt").val()
// 				},
// 				// -> 4. data 전달 성공 시 
// 				success: function () {
// 					console.log("(boardContent.jsp) 보내기 성공");
// 					location.reload();
// 					// -> 5. reload를 통해 실시간으로 댓글이 작성되는 모습을 기대할 수 있다,, 
// 				}
				
// 				}); // ajax
			
// 			}// function
// 		});// click
		
// 	});// jquery ready

</script>
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
			<td colspan="3" width="600px" height="300px"> ${dto.content }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td colspan="3"> <a href="./upload/${dto.file }">${dto.file }</a></td>
		</tr>
		<tr>
			<td><input type="button" value="수정" onclick="location.href='./BoardUpdate.bo?bno=${dto.bno}&pageNum=${pageNum }';"></td>
			<td><input type="button" value="삭제" onclick="location.href='./BoardDelete.bo?bno=${dto.bno}&pageNum=${pageNum }';"></td>
			<td><input type="button" value="답글" 
					onclick="location.href='./BoardReWrite.bo?bno=${dto.bno}&pageNum=${pageNum }&re_ref=${dto.re_ref }&re_lev=${dto.re_lev }&re_seq=${re_seq }';"></td>
		<!-- <td><input type="button" value="목록" onclick="location.href='boardList.jsp';"></td> 땡!!!! 가상 주소로 부르기-->
		<!-- <td><input type="button" value="목록" onclick="location.href='./BoardList.bo';"></td> 땡... 
			    근데 4페이지에 글 읽고 목록 눌러서 뒤로 갔는데,, 1페이지로 돌아가버림;; 우짜냐 
				pageNum은 어딨지?? boardList에 있습니다~~ .... 
				페이지 정보까지 같이 전달하려면? pageNum 까지 적어주기,,,, boardContentAction에서 request로 저장한 그거? 같이 보냄
			-->
			<td><input type="button" value="목록" onclick="location.href='./BoardList.bo?pageNum=${pageNum}';"></td>
		</tr>
	</table>
	
	<br><br>
	
	
<!-- ----------------------- 댓글 구간^^ --------------------------------- -->
				<form action="./CommentWrite.bo?pageNum=${pageNum }" method="post" name="frm" >
					<!-- 댓글 수정, 삭제를 위한,, 파라메타.... -->
<!-- 					<input type="hidden" name="bno" value="1"> 댓글 수정, 삭제할 때 js 함수에서 value 변경,, -->
<!-- 					<input type="hidden" name="exe" value="1"> exe:1(댓글 추가) -->
<%-- 					<input type="hidden" name="pageNum" value="${pageNum }"> <!-- 굳이 필요한감? 주소줄에 적었음 --> --%>
					<input type="hidden" name="bno" value="${dto.bno }">  <!-- bno : 메인 글의 bno!! (BoardDTO의 bno!!!!) 여기가 중요 ★★★-->
					
					<table>
						<tr>
							<th colspan="2"> 댓글</th>
						</tr>
						<tr>
							<td width="100"> 이름 </td>
							<td width="150"> <input type="text" name="name"> </td>
						</tr>
						<tr>
							<td> 내용 </td>
							<td> <textarea rows="5" cols="60" name="content"></textarea> </td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="댓글 작성" name="cmd">
								<input type="reset" value="리셋">
							</td>
						</tr>
					</table>
				</form>
				<!-- ----------------------- 댓글 작성 구간 끝^^ --------------------------------- -->
				
				<br>
				<hr>
				
				
				<!-- ----------------------- 댓글 리스트 구간 --------------------------------- -->
				<%
					BoardDAO dao = new BoardDAO();
					int bno = Integer.parseInt(request.getParameter("bno"));
					List<CommentDTO> cmtList = dao.getCommentList(bno);
					request.setAttribute("cmtList", cmtList);
				%>
				
						<input type="hidden" name="c_bno" value="${cdto.c_bno }">
				<c:forEach var="cdto" items="${cmtList }">
					<table width="60%" style="border: 1px solid gray">
						<tr>
							<td> name: ${cdto.name } </td>
							<td align="right"> <fmt:formatDate value="${cdto.date }" pattern="yyyy.MM.dd hh:mm"/>
						</tr>
						<tr height="60px">
							<td colspan="2"> content: <br> ${cdto.content } </td>
						</tr>
					</table>
				</c:forEach>
						<input type="button" value="수정" onclick="location.href='./CommentUpdate.bo?c_bno=${cdto.c_bno}';">
						<input type="button" value="삭제" onclick="location.href='#';">
				
				<!-- ----------------------- 댓글 리스트 구간 끝^^ --------------------------------- -->
	
</body>
</html>