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
</head>
<body>
<h1>ユーザー編集</h1>
<a href="manage">戻る</a>
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

	<input name="user_id" type="hidden" value="${editUser.id}"/>
	<label for="name">名称</label>
	<input name="name" id="name" value="${editUser.name }"/>(１０字以内)<br />

	<label for="login_id">ログインID</label>
	<input name="login_id" id="login_id" value="${editUser.login_id }"/>(半角英数字６～２０字)<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/>(半角文字６～２５５字)<br />
	<label for="password">パスワード(確認用)</label>
	<input name="checkPassword" type="password" id="checkPassword"/><br />



	<label for="branch_id">支店名</label>
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


	<label for="department">部署・役職</label>
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



	<input type="submit" value="登録" /><br /><br />

</form>

</body>
</html>