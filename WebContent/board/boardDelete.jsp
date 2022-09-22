<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/boardDelete.jsp</title>
</head>
<body>
	<h1>board/boardDelete.jsp</h1>
	
	<!-- 스크립틀릿 안에서 
		int bno = Integer.parseInt(request.getParameter("bno"));  
		이렇ㄱ ㅔ쓰던 걸 ,,,, EL로 간단하게 쓸 수 있,, ㄷㄷㄷㄷㄷ
		헐 맞다,,,,,,,, 댑악, ,, , 잊고 있었다   -->
	bno: ${param.bno } <!-- 파라메타에 있는 애들 불러오려면 ,,,  --> <br>
	pageNum: ${param.pageNum }
	
		<!-- // DB 안에 들어가는 애들은 폼 태그 안에 넣어주는 게 좋고 (bno는 hidden으로!!! 폼 태그 안에서)
		pageNum은 DB에 없는 애니까 주소줄에 붙여서 보내기 -->
		
	<form action="./BoardDeleteAction.bo?pageNum=${param.pageNum }" method="post">
		<input type="hidden" name="bno" value="${param.bno }"> 
		비번을 입력해주세요 👉👉 
		<input type="password" name="pass">
		<input type="submit" value="확인">
	</form>
</body>
</html>