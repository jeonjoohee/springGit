<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<body>
	<form action="/bod/modifyProc" method>
		<table border=1 align=center>
			<tr>
				<th>제목
				<td><input type="text" name="title" value="${dto.title}">
			</tr>
			<tr>
				<th>내용
				<td><input type="text" name="contents" value="${dto.contents}">
			</tr>
			<tr>
				<td><button>수정완료</button>
			</tr>
		</table>
		<input type="hidden" name="seq" value="${dto.seq}">
	</form>
</body>
</html>