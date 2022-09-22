<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reWriteForm.jsp</title>
</head>
<body>

 	<%
		/* Content.jsp -> 답글 버튼 눌러서 ./BoardReWrite.bo 가서 
		   -> forward 방식으로 여까지 왔는데염??? 
			   근데 request 영역에 dto 정보가 없네~~ 
		*/
		
		
 		System.out.println("(from reWriteForm.jsp) V: .jsp페이지로 이동 완");
		String id = (String) session.getAttribute("id");
		
/*  		//전달된 파라미터 저장 이렇게 할 필요 없음 EL로 받으니까^^ㅋ
		int bno = Integer.parseInt(request.getParameter("bno"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq")); */
	%>
	
	<h2> 파라메타로 보낸 정보들 잘 넘어왔나 확인 중,,,,, </h2>
	<h4> jsp 코드로 받아도 되지만,, 넘 기니까,, EL로,,,</h4>
	bno: ${param.bno } <br>
	pageNum: ${param.pageNum } <br>
	re_ref: ${param.re_ref } <br>
	re_lev: <%=request.getParameter("re_lev") %><br>
	re_seq: ${param.re_seq } <br>
	
	
	<fieldset>
	<legend>게시판 답글쓰기 🌈</legend>
		<form action="./BoardReWriteAction.bo?pageNum=${param.pageNum}" method="post" name="fr">
			<input type="hidden" name="bno" value="${dto.bno }">
			<input type="hidden" name="re_ref" value="${dto.re_ref }">
			<input type="hidden" name="re_lev" value="${dto.re_lev }">
			<input type="hidden" name="re_seq" value="${dto.re_seq }">  
				<!-- DB에 들어가는 애들이니까,, form 안에 hidden으로  -->
		   글쓴이 : <input type="text" name="name" required><br>
		   비밀번호 : <input type="password" name="pass" required><br>
		   제목 : <input type="text" name="subject" maxlength="15" value="" ><br>
		   내용 : <br> 
		  <textarea rows="10" cols="35" name="content" placeholder="여기에 작성해주세요^^ 뇌절 드립은 금지~~ 🤣🤣" ></textarea><br>
		  <input type="submit" value="답글쓰기" class="btn">
		  <button type="reset" class="btn">초기화</button>
		  <input type="button" value="목록으로" class="btn" onclick="location.href='./BoardList.bo?pageNum=${param.pageNum}';">
		</form>
	</fieldset>


</body>
</html>