<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
</head>
<body>
<div class="users-table">
<a href="./">戻る</a>
<table>
  <tr>
    <th>name</th>
    <th>login_id</th>
    <th>branch_id</th>
    <th>department_id</th>
    <th>設定</th>
  </tr>
<c:forEach items="${users}" var="user" >
  <tr>
    <td>${user.name }</td>
    <td>${user.login_id}</td>
    <td>${user.branch_id }</td>
    <td>${user.department_id}</td>
    	<form action="setting"method="post"></form>
    <td><input type="submit" value="設定" /></td>

  </tr>


</c:forEach>
</table>
<div class="copyright">わったい菜</div>
</div>
</body>
</html>



