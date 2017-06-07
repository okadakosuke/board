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

<div class="header">
<h1>ホーム画面</h1>
</div>


<div class="nav">
	<ul class="nl clearFix">
		<li><a href="message">新規投稿</a></li>
		<c:if test="${loginUser.department_id ==1}">
			<li><a href="manage">ユーザー管理</a></li>
		</c:if>
			<span style="float: right"><li><a href="logout" id="logout" ><c:out value="ログアウト"></c:out></a></li>
	</span>
	</ul>
</div>



<div class="nav">
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

<form action="index.jsp"method="get"><br />

	<br />
	<div class="focus">
	絞込み
	<br />
	<label for="category"></label>
		<select name="categorybox" >
		<option value=""><c:out value="カテゴリ"/></option>
			<c:forEach items="${categorys}" var="category">
				<c:if test="${category.category == categorybox }">
					<option selected value="${category.category}"><c:out value="${category.category}" /></option>
				</c:if>
				<c:if test="${category.category !=categorybox }">
					<option value="${category.category}"><c:out value="${category.category}" /></option>
				</c:if>
			</c:forEach>
		</select>

	<input type="date" name="timedate" value="${timedate}">
	<input type="date" name="finishdate" value="${finishdate}">
	<span style="cursor: hand; cursor:pointer;"><input type="submit" value="実行"></span>
	</div>
</form>
<br>

<div class="main-contents">
		<c:forEach items="${usermessages}" var="usermessage">
	<div class="message">

			<div class="title"><h3><c:out value="${usermessage.title}" /></h3></div>
			<div class="name"><p>投稿者:<c:out value="${usermessage.name}" /></p></div>
			<div class="category"><p>カテゴリ:<c:out value="${usermessage.category}" /></p></div>
			<div class="time"><fmt:formatDate value="${usermessage.insert_date}" pattern="yyyy年MM月dd日（E） a KK時mm分" /></div>
			<div class="text"><pre><c:out value="${usermessage.text}" /></pre></div>



			<c:if test="${loginUser.department_id == 2 ||loginUser.id == usermessage.user_id
			||loginUser.branch_id == usermessage.branch_id &&loginUser.department_id ==3}">
				<div class="deletemant">
					<form action="delete_message" method="post" onSubmit="return Check()">
						<input type="hidden" name="message_id" value="${usermessage.id }" >
						<input type="submit" value="削除">
					</form>
				</div>
			</c:if>
	</div>

			<div class="comments">
				<c:forEach items="${usercomments}" var="usercomment">
					<c:if test="${ usercomment.message_id == usermessage.id }">
						<div class="title"><h3><c:out value="コメント" /></h3></div>

						<div class="name"><p>投稿者:<c:out value="${usercomment.name}" /></div>
						<div class="time"><fmt:formatDate value="${usercomment.insert_date}" pattern="yyyy年MM月dd日（E) a KK時mm分" /></div>
						<div class="text"><pre><c:out value="${usercomment.text}" /></pre></div>

						<c:if test="${loginUser.department_id == 2 ||loginUser.id == usercomment.user_id
									||loginUser.id == usercomment.user_id &&loginUser.department_id ==3}">
						<div class="deletemant">
						<form action="delete_comment" method="post" onSubmit="return Check()">
							<input type="hidden" name="comment_id" value="${usercomment.id }" >
							<input type="submit" value="削除">
						</form>
						</div>
						</c:if>
					</c:if>
				</c:forEach>


			</div>



			<div class="form-area">
				<form action="comment" method="post" ><br />
					<input type="hidden" name="message_id" value="${usermessage.id }" >
					<div class="com">コメント(500字まで)</div><br />
					<textarea name="text" cols="80" rows="3" wrap="hard" class="tweet-box"></textarea><br /><br />

					<div class="bot"><input type="submit" value="コメント"></div><br>
				</form>
			</div>

		</c:forEach>
	</div>

</div>
</body>
</html>