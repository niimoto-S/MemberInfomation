<%@page import="jp.co.aforce.bean.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報更新</title>
</head>
<body>
<script type="text/javascript" src="../js/script.js"></script>
<%try{ %>
	<% if(session.getAttribute("updateMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("updateMessage") %></h3>
	<%}
	session.removeAttribute("updateMessage");%>

<%} catch (Exception e) {} %>

<strong>会員情報更新</strong>

<form action="./../SearchServlet" method="post" name="search">
	会員番号<br>
	<input type="text" name="memberId" id="memberId" oninput="updateValue();" value="${sessionScope.memberId }">
	<button type="submit">検索</button>
</form>

<form action="../UpdateServlet" method="post">

	<input type="hidden" name="memberId" id="memberIdHidden" value="${sessionScope.memberId }">

	<p>名前<br>
	姓<input type="text" id="lastName" name="lastName" value="${sessionScope.updateBean.lastName }">
	名<input type="text" id="firstName" name="firstName" value="${sessionScope.updateBean.firstName }"></p>

	<p>性別<br>
	<input type="radio" name="sex" value="1" <c:if test="${sessionScope.updateBean.sex == 1 }">checked</c:if>>男
	<input type="radio" name="sex" value="2" <c:if test="${sessionScope.updateBean.sex == 2 }">checked</c:if>>女</p>

	<p>生年月日<br>
	<input type="number" id="year" name="year" max="9999" min="1" value="${sessionScope.updateBean.year }">年
	<select name="month" id="month">
		<c:forEach var="i" begin="1" end="12">
			<option value="${i}" <c:if test="${sessionScope.updateBean.month == i }">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	<select name="day" id="day">
		<c:forEach var="i" begin="1" end="31">
			<option value="${i}" <c:if test="${sessionScope.updateBean.day == i }">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</p>

	<p>職業<br>
	<select name="job" id="job">
		<option value="100" <c:if test="${sessionScope.updateBean.job == 100 }">selected</c:if>>会社員</option>
		<option value="200" <c:if test="${sessionScope.updateBean.job == 200 }">selected</c:if>>自営業</option>
		<option value="300" <c:if test="${sessionScope.updateBean.job == 300 }">selected</c:if>>学生</option>
		<option value="400" <c:if test="${sessionScope.updateBean.job == 400 }">selected</c:if>>その他</option>
	</select></p>

	<p>電話番号<br>
	<input type="number"id="phoneNumber" name="phoneNumber" value="${sessionScope.updateBean.phoneNumber }"></p>

	<p>メールアドレス<br>
	<input type="text" id="mailAddress" name="mailAddress" value="${sessionScope.updateBean.mailAddress }"></p>


	<a href="menu.jsp">
    	<button type="button">戻る</button>
	</a>

	<button type="button" onclick="formReset()">リセット</button>
	<button type="submit">更新</button>

</form>
<%@include file="footer.html" %>