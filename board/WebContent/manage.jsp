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
<link href="css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">

	function Check(){

		if(window.confirm("本当によろしいですか？")){
		location.href = "./manage" ;
		return true;
		}

	else{
	}

	window.alert("キャンセルしました")
	return false;
	}

	</script>
</head>
<body>
<h1>ユーザー管理</h1>
<div class="nav">
	<ul class="nl clearFix">
<li><a href="signup">ユーザー新規登録</a></li>
<li><a href="./">戻る</a></li>

	</ul>

<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
				</c:forEach>
				</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>





<table class="managetable">
  <thead>
  <tr>
    <th scope="col">名前</th>
    <th scope="col">ログインID</th>
    <th scope="col">支店</th>
    <th scope="col">部署・役職</th>
    <th colspan="3">設定</th>


  </tr>
  </thead>
  <tbody>


	<c:forEach items="${users}" var="user" varStatus="status">

		<c:if test="${status.count % 2 == 0}">
		<tr class="even">
		</c:if>
		<c:if test="${status.count % 2 == 1}">
		<tr class="odd">
		</c:if>

		<th scope="row">${user.name }</th>
   		<td>${user.login_id}</td>
   		<td>
  		<c:forEach items="${branches }" var="branch" >
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
   	 	<c:if test="${loginUser.id != user.id}">
   	 		<td>
   	 			<form action="stop" method="post" onSubmit="return Check()">
   	 				<input type="hidden" name="id" value="${user.id }" >
					<c:if test="${user.is_deleted == 0 }">
						<input type="hidden" name="num" value=1 >
						<input type="submit" value="停止" />
					</c:if>
					<c:if test="${user.getIs_deleted() == 1 }">
						<input type="hidden" name="num" value=0>
						<input type="submit" value="復活" />
					</c:if>
   	 			</form>
   	 		</td>
   	 		</c:if>

		<c:if test="${loginUser.id != user.id}">
 	 	<td>
	   	 	<form action="delete" method="post" onSubmit="return Check()">
	   	 		<input type="hidden" name="id" value="${user.id }" >
	   	 		<input type="submit" value="削除"/>
	   	 	</form>
   	 	</td>
   	 	</c:if>

	</tr>

	</c:forEach>
	</tbody>

</table>
</div>
</div>
</body>
</html>



