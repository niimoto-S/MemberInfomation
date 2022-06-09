<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録</title>
</head>
<body>

<%try{ %>
	<% if(session.getAttribute("message").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("message") %></h3>
	<%}
	session.removeAttribute("message");
	%>

<%} catch (Exception e) {} %>

<strong>会員情報登録</strong>


<form action="../addServlet" method="post">
	<p>名前<br>
	姓<input type="text" name="lastName">  名<input type="text" name="firstName"></p>

	<p>性別<br>
	<input type="radio" name="sex" value="1">男<input type="radio" name="sex" value="2">女</p>

	<p>生年月日<br>
	<input type="number" name="year" max="9999" min="1">年
	<select name="month">
		<c:forEach var="i" begin="1" end="12">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<select name="day">
		<c:forEach var="i" begin="1" end="31">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	</p>

	<p>職業<br>
	<select name="job">
		<option value="100">会社員</option>
		<option value="200">自営業</option>
		<option value="300">学生</option>
		<option value="400">その他</option>
	</select></p>

	<p>電話番号<br>
	<input type="number" name="phoneNumber"></p>

	<p>メールアドレス<br>
	<input type="text" name="mailAddress"></p>

	<a href="menu.jsp">
    	<button type="button">戻る</button>
	</a>
	<button type="reset">リセット</button>
	<button type="submit">登録</button>

</form>
<%@include file="footer.html" %>