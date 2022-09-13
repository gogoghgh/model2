<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/updateForm.jsp</title>
</head>
<body>
	<h1>board/updateForm.jsp</h1>
	
	<h2>📝 글 수정 페이지 📝</h2>
	
	<fieldset>
		<form action="./BoardUpdatePro.bo?pageNum=${pageNum }" method="post">
							<!--   ㄴ Pro말고 Action으로 써야 하는데,, 이미 있어서,, 헷갈릴테니 ㅎㅎ -->
							<!--   ㄱ 밑에 얘네들은 다 DTO.. 액션태그로 setProperty 했을 때 자동으로 다 들어감~ 
										근데 pageNum은 안 들어가니까,, 따로 빼준거 -->
			<input type="hidden" name="bno" value="${dto.bno }"> 
			글쓴이: <input type="text" name="name" value="${dto.name }"> <br> 
			비 번: <input type="password" name="pass"> <br> 
			제 목: <input type="text" name="subject" value="${dto.subject }"> <br> 
			내 용: <textarea rows="10" cols="20" name="content">${dto.content }</textarea> <br> 
			
			<input type="submit" value="글 수정">
		</form>
	</fieldset>
	
</body>
</html>