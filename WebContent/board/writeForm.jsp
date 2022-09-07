<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/writeForm.jsp</title>
</head>
<body>
	<h1>board/writeForm.jsp</h1>
	
	<h2>📝 글쓰기 페이지 📝</h2>
	
	<fieldset>
		<form action="./BoardWriteAction.bo" method="post">
						<!-- 이전에 pro 페이지에서 했던 내용들 인제 여기서 다 할 것 -->
			글쓴이: <input type="text" name="name"> <br> 
			비 번: <input type="password" name="pass"> <br> 
			제 목: <input type="text" name="subject"> <br> 
			내 용: <textarea rows="10" cols="20" name="content"></textarea> <br> 
			
			<input type="submit" value="글쓰기">
		</form>
	</fieldset>
	
	
</body>
</html>