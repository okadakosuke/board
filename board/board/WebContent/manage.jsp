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
<h1>ユーザー管理</h1>

<div class="users-table">
<a href="signup">ユーザー新規登録</a>
<a href="./">戻る</a>
<table>
  <tr>
    <th>名称</th>
    <th>ログインID</th>
    <th>支店</th>
    <th>部署・役職</th>
    <th>設定</th>
  </tr>


	<c:forEach items="${users}" var="user" >
	 <tr>
		<td>${user.name }</td>
   		<td>${user.login_id}</td>
   		<td>
  		<c:forEach items="${branches }" var="branch">
			<c:if test="${ user.branch_id == branch.id }">
  				<option value="${branch.id}">${branch.name}</option>
  			</c:if>
   		 </c:forEach>
		</td>

   	 	<td>
		<c:forEach items="${departments }" var="department">
			<c:if test="${ user.department_id == department.id }">
  				<option value="${department.id}">${department.name}</option>
  			</c:if>
   		 </c:forEach>
		</td>
   	 	<td>
	   	 	<form action="setting" method="get">
	   	 		<input type="hidden" name="id" value="${user.id }" >
	   	 		<input type="submit" value="編集" />
	   	 	</form>
   	 	</td>
   	 		<td>
   	 			<form action="stop" method="post">
   	 				<input type="hidden" name="id" value="${user.id }" >
					<c:if test="${user.is_deleted == 0 }">
						<input type="hidden" name="num" value=1>
						<input type="submit" value="停止" />
					</c:if>
					<c:if test="${user.getIs_deleted() == 1 }">
						<input type="hidden" name="num" value=0>
						<input type="submit" value="復活" />
					</c:if>
   	 			</form>
   	 		</td>

 	 	<td>
	   	 	<form action="delete" method="post">
	   	 		<input type="hidden" name="id" value="${user.id }" >
	   	 		<input type="submit" value="削除" />
	   	 	</form>
   	 	</td>

  </tr>
</c:forEach>
</table>
</div>
</body>
</html>



