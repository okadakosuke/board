<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<div class="main-contents">
<a href="./">戻る</a>
<div class="form-area">
		<form action="newMessage" method="post">
		投稿<br />
		<textarea name="message" cols="100" rows="5"class="tweet-box"></textarea>
		<br />
		<input type="submit" value="投稿">(1000字まで)
		</form>
</div>
<div class="copyright">わったい菜</div>
</div>
</body>
</html>