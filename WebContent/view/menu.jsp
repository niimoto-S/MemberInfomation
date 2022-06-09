<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>

<%session.invalidate(); %>

<p><a href="add.jsp">会員情報登録</a>
<p><a href="update.jsp">会員情報更新</a>
<p><a href="delete.jsp">会員情報削除</a>

<%@include file="footer.html" %>
