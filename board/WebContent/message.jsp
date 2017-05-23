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
		<form action="message" method="post"><br />

		タイトル<br />
		<label for="title"></label>
		<input name="title" id="title"/><br />
		投稿(1000字まで)<br /><br />
		<textarea name="text" cols="100" rows="5" class="tweet-box"></textarea>
		カテゴリ<br />
		<label for="category"></label>
		<input name="category" id="category"/><br />


		<input type="submit" value="投稿">

		</form>
</div>
<div class="copyright">わったい菜</div>
</div>
</body>
</html>