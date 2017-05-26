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
<h1>投稿一覧</h1>

	<a href="message">新規投稿画面</a>
	<a href="manage">ユーザー管理</a>
	<a href="logout">ログアウト</a>

<div class="messages">
	<c:forEach items="${usermessages}" var="usermessage">

		<fmt:formatDate value="${usermessage.insert_date}" pattern="yyyy年MM月dd日（E） a KK時mm分" />
		<div class="name"><c:out value="${usermessage.name}" /></div>
		<div class="title"><c:out value="${usermessage.title}" /></div>
		<div class="text"><c:out value="${usermessage.text}" /></div>
		<div class="category"><c:out value="${usermessage.category}" /></div>


		<hr>
		<div class="comments">
			<c:forEach items="${usercomments}" var="usercomment">
				<c:if test="${ usercomment.message_id == usermessage.id }">
					<fmt:formatDate value="${usercomment.insert_date}" pattern="yyyy年MM月dd日（E) KK時mm分" />
					<div class="name"><c:out value="${usercomment.name}" /></div>
					<div class="text"><c:out value="${usercomment.text}" /></div>
				</c:if>
			</c:forEach>
		</div>
		<hr>

		<div class="form-area">
		<form action="comment" method="post"><br />
		<input type="hidden" name="message_id" value="${usermessage.id }" >
		コメント(500字まで)<br />
		<textarea name="text" cols="100" rows="3" class="tweet-box"></textarea><br /><br />


			<input type="submit" value="返信">

		</form>
</div>
	</c:forEach></div>



</div>
</div>
</body>
</html>