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
<h1>新規投稿</h1>
<a href="./">戻る</a>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="request"/>
</c:if>


<div class="form-area">
		<form action="message" method="post"><br />

		<input type="hidden" name="user_id" value="${user.id }" >

		タイトル<br />
		<label for="title"></label>
		<input name="title" id="title" value="${newMessage.title }"/><br /><br />
		投稿(1000字まで)<br />
		<textarea name="text" cols="100" rows="5" class="tweet-box"><c:out value="${newMessage.text }"></c:out></textarea><br /><br />
		カテゴリ<br />
		<label for="category"></label>
		<input name="category" id="category" value="${categoryfree}"/><br />

		<label for="category">カテゴリ</label>
			<select name="categorybox" >
			<option value="">選択してください</option>
				<c:forEach items="${categorys}" var="category">
					<c:if test="${categorybox.equals(category.category)}">
						<option selected value="${category.category}"><c:out value="${category.category}" /></option>
					</c:if>
					<c:if test="${!categorybox.equals(category.category)}">
						<option value="${category.category}"><c:out value="${category.category}" /></option>
					</c:if>
				</c:forEach>
			</select>

		<input type="submit" value="投稿">

		</form>
</div>
</div>
</body>
</html>