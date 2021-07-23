<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 상세보기</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
</style>

<script>
	$(function() {
		$("#modify").on("click", function() {
			$("#frm").attr("action", "/bod/boardModify").submit();
		})
		$("#delete").on("click", function() {
			$("#frm").attr("action", "bod/boardDelete").submit();
		})
		$("#tolist").on("click", function() {
			location.href = "";
		})
	})
</script>
</head>

<body>

	<form action="" method="post" id="frm">
		<table border="1">
			<tr>
				<td>No
				<td>${list.seq}<input type="hidden" name="seq" value="${list.seq}">
				<td>Writer
				<td>${list.writer}
				<td>Date
				<td>${list.write_date}
			</tr>
			<tr>
				<td colspan=3>Title
				<td colspan=3><input type="text" size="50" name="title" value="${list.title}">
			</tr>
			<tr>
				<td colspan=6><textarea cols="80" rows="15" name="contents">${list.contents}</textarea>
			</tr>
			
			<c:choose>
				<c:when test="${login.id == list.writer}">
					<tr>
						<td colspan=6>
							<input type="button" value="수정" id="update">
							<input type="button" value="삭제" id="delete"> 
							<input type="button" value="목록" id="tolist">
					</tr>
				</c:when>
				
				<c:otherwise>
					<tr>
						<td colspan=6><input type="button" value="목록으로" id="tolist">
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>

</body>
</html>