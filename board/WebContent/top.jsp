<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
</head>
<body>
<div class="main-contents">

<div class="header">
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<a href="signup">登録する</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<a href="./">ホーム</a>
		<a href="logout">ログアウト</a>
		<a href="message">新規投稿画面</a>
		<a href="manage">ユーザー管理</a>


	</c:if>
</div>


<div class="messages">
	<c:forEach items="${messages}" var="message">
		<div class="title"><c:out value="${messages.title}" /></div>
		<div class="text"><c:out value="${messages.text}" /></div>
		<div class="category"><c:out value="${messages.category}" /></div>
	</c:forEach>
</div>
</form>


<div class="copyright">わったい菜</div>
</div>
</body>
</html>