<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>設定</title>
</head>
<body>
<h1>設定</h1>
<div class="main-contents"></div>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessaes">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
				</c:forEach>
				</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="setting"method="post"><br />
	<label for="name">名称</label>
	<input name="name" id="name"/>(１０字以内)<br />

	<label for="login_id">ログインID</label>
	<input name="login_id" id="login_id"/>(半角英数字６～２０字)<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/>(半角文字６～２５５字)<br />

	<label for="branch_id">支店名</label>
	<input name="branch_id" id="branch_id"/><br />

	<label for="department_id">部署・役職名</label>
	<input name="department_id" id="department_id"/><br />

	<input type="submit" value="登録" /><br /><br />
	<a href="./">戻る</a>
</form>
<div class="copyright">わったい菜掲示板</div>

</body>
</html>