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
	
	<h2>đ ę¸ė°ę¸° íė´ė§ đ</h2>
	
	<fieldset>
		<form action="./BoardWriteAction.bo" method="post">
						<!-- ã´ ė´ė ė pro íė´ė§ėė íë ë´ėŠë¤ ė¸ė  ėŦę¸°ė(..action) ë¤ í  ę˛ -->
			ę¸ė´ė´: <input type="text" name="name"> <br> 
			ëš ë˛: <input type="password" name="pass"> <br> 
			ė  ëĒŠ: <input type="text" name="subject"> <br> 
			ë´ ėŠ: <textarea rows="10" cols="20" name="content"></textarea> <br> 
			
			<input type="submit" value="ę¸ė°ę¸°">
		</form>
	</fieldset>
	
	
</body>
</html>