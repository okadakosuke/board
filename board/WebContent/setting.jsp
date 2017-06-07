<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>ユーザー編集</h1>

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

<form action="setting"method="post"><br />

	<input name="user_id" type="hidden" value="${editUser.id}"/>
	<label for="name">名前</label><br>

	<input name="name" id="name" value="${editUser.name }"/>(10字まで)<br />

	<label for="login_id">ログインID(半角英数字6～20字)</label><br>
	<input name="login_id" id="login_id" value="${editUser.login_id }"/><br />

	<label for="password">パスワード(半角文字6～255字)</label><br>
	<input name="password" type="password" id="password"/><br />
	<label for="password">パスワード(再入力)</label><br>
	<input name="checkPassword" type="password" id="checkPassword"/><br />


	<c:if test="${editUser.id == loginUser.id}">
		<input type="hidden" name="branch_id" value="${editUser.branch_id}">
		<input type="hidden" name="department" value="${editUser.department_id}">
	</c:if>

	<c:if test="${editUser.id != loginUser.id}">
		<label for="branch_id">支店名</label><br>
		<select name="branch_id">
				<c:forEach items="${branches}" var="branch">
					<c:if test="${editUser.branch_id == branch.id }">
						<option selected value="${branch.id}">${branch.name } </option>
					</c:if>
					<c:if test="${editUser.branch_id != branch.id }">
						<option  value="${branch.id}">${branch.name } </option>
					</c:if>
				</c:forEach>
		</select>


		<br><label for="department">部署・役職</label><br>
		<select name="department">
					<c:forEach items="${departments}" var="department">
						<c:if test="${editUser.department_id == department.id }">
							<option selected value="${department.id }">${department.name }</option>
						</c:if>
						<c:if test="${editUser.department_id !=department.id }">
							<option value="${department.id}">${department.name }
						</c:if>
					</c:forEach>
		</select>
	</c:if>


	<br>

	<input type="submit" value="登録" /><br /><br />

</form>
</div>
</div>
</body>
</html>