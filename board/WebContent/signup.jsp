<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー新規登録画面</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<h1>ユーザー新規登録</h1>
<div class="nav">
	<ul class="nl clearFix">
	<li><a href="manage">戻る</a></li>

	</ul>

<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" /></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="signup"method="post"><br />
	<div class="signup">
	<label for="name">名前(10字まで)</label><br>
	<input name="name" id="name" value="${newUser.name }"/><br />

	<label for="login_id">ログインID(半角英数字6～20字)</label><br>
	<input name="login_id" id="login_id" value="${newUser.login_id }"/><br />

	<label for="password">パスワード(半角文字6～255字)</label><br>
	<input name="password" type="password" id="password"/><br />
	<label for="password">パスワード(再入力)</label><br>
	<input name="checkPassword" type="password" id="checkPassword"/><br />

	<label for="branch_id">支店名</label><br>
	<select name="branch_id">
		<option value="0">選択してください</option>
			<c:forEach items="${branches}" var="branch">
			<c:if test="${newUser.branch_id == branch.id }">
					<option selected value="${branch.id}">${branch.name } </option>
				</c:if>
				<c:if test="${newUser.branch_id != branch.id }">
					<option  value="${branch.id}">${branch.name } </option>
				</c:if>
		</c:forEach>
	</select>

	<br><label for="department">部署・役職</label><br>
	<select name="department">
		<option value="0">選択してください</option>
			<c:forEach items="${departments }" var="department">
				<c:if test="${newUser.department_id == department.id }" >
				<option selected value="${department.id}"> ${department.name } </option>
				</c:if>
				<c:if test="${newUser.department_id != department.id }" >
				<option value ="${department.id }">${department.name }</option>
				</c:if>

			</c:forEach>
	</select>

	<br /><br /><input type="submit" value="登録" />
</div>
</form>
</div>
</div>
</body>
</html>