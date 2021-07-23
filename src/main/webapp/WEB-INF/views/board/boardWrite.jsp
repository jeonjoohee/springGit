<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 작성</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
</style>

<script>
	$(function() {
		$("#tolist").on("click", function() {
			location.href = "/bod/boardlist?cpage=1";
		})
	})
</script>
</head>

<body>

	<form action="/bod/writeProc" method="post">
		<table border="1">
			<tr>
				<td align="center">자유게시판 글 쓰기</td>
			</tr>
			<tr>
				<td><input type="text" size="50" placeholder="제목을 입력하세요." name="title">
			</tr>
			<tr>
				<td><textarea cols="80" rows="15" placeholder="내용을 입력하세요." name="contents"></textarea>
			</tr>
			<tr>
				<td align="right">
					<input type="submit" value="작성" id="write">
					<input type="button" value="목록" id="tolist">
			</tr>
		</table>
	</form>

	<c:choose>
		<c:when test="${result>0}">
			<script>
				alert("글쓰기에 성공하셨습니다");
				location.href = "List.jsp";
			</script>
		</c:when>
	</c:choose>

</body>
</html>