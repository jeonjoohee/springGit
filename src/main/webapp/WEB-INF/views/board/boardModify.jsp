<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<style>
#title{width:90%;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<body>
	<form action="/bod/modifyProc" method="post">
		<table border=1 align=center>
		    <tr>
            <td>No
            <td>${dto.seq}<input type="hidden" name="seq" value="${dto.seq}">
            <td>Writer
            <td>${dto.writer}
            <td>Date
            <td>${dto.write_date}
         </tr>
         <tr>
            <td colspan=2>Title
            <td colspan=4><input type="text" name="title" id="title" value="${dto.title}">
         </tr>
         <tr>
            <td colspan=6><textarea cols="80" rows="15" name="contents">${dto.contents}</textarea>
         </tr>
			<tr>
				<td colspan=6 align=center><button>수정완료</button>
			</tr>
		</table>
	</form>
</body>
</html>