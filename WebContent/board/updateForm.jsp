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
	
	<h2>๐ ๊ธ ์์  ํ์ด์ง ๐</h2>
	
	<fieldset>
		<form action="./BoardUpdatePro.bo?pageNum=${pageNum }" method="post">
							<!--   ใด Pro๋ง๊ณ  Action์ผ๋ก ์จ์ผ ํ๋๋ฐ,, ์ด๋ฏธ ์์ด์,, ํท๊ฐ๋ฆดํ๋ ใใ -->
							<!--   ใฑ ๋ฐ์ ์๋ค๋ค์ ๋ค DTO.. ์ก์ํ๊ทธ๋ก setProperty ํ์ ๋ ์๋์ผ๋ก ๋ค ๋ค์ด๊ฐ~ 
										๊ทผ๋ฐ pageNum์ ์ ๋ค์ด๊ฐ๋๊น,, ๋ฐ๋ก ๋นผ์ค๊ฑฐ -->
			<input type="hidden" name="bno" value="${dto.bno }"> 
			๊ธ์ด์ด: <input type="text" name="name" value="${dto.name }"> <br> 
			๋น ๋ฒ: <input type="password" name="pass"> <br> 
			์  ๋ชฉ: <input type="text" name="subject" value="${dto.subject }"> <br> 
			๋ด ์ฉ: <textarea rows="10" cols="20" name="content">${dto.content }</textarea> <br> 
			
			<input type="submit" value="๊ธ ์์ ">
		</form>
	</fieldset>
	
</body>
</html>