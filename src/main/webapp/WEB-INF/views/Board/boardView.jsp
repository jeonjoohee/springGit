<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
td {
	text-align: center;
}
</style>

<script>
	$(function() {
		$("#modify").on("click", function() {
			$("#frm").attr("action", "/bod/modify").submit();
		})
		$("#delete").on("click", function() {
			$("#frm").attr("action", "/bod/delete").submit();
		})
		$("#tolist").on("click", function() {
			location.href = "/bod/boardlist";
		})
	})
</script>
</head>

<body>

	<form action="" method="post" id="frm">
		<table border="1">
			<tr>
				<td>No
				<td>${dto.seq}<input type="hidden" name="seq" value="${dto.seq}">
				<td>Writer
				<td>${dto.writer}
				<td>Date
				<td>${dto.write_date}
			</tr>
			<tr>
				<td colspan=3>Title
				<td colspan=3><input type="text" size="50" name="title" value="${dto.title}">
			</tr>
			<tr>
				<td colspan=6><textarea cols="80" rows="15" name="contents">${dto.contents}</textarea>
			</tr>
			<c:choose>
				<c:when test="${login.id == dto.writer}">
					<tr>
						<td colspan=6>
							<input type="button" value="수정" id="modify">
							<input type="button" value="삭제" id="delete"> 
							<input type="button" value="목록" id="tolist">
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan=6><input type="button" value="목록" id="tolist">
							<!-- a href="java:history.back()" -->
					</tr>

				</c:otherwise>
			</c:choose>
		</table>
	</form>
	
</body>
</html>