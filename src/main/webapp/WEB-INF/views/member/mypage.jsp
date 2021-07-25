<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>


</head>
<body>
	<table align="center" border=1>
		<tr>
			<th colspan=2>마이페이지
		</tr>
		<tr>
			<th>${mydto.id }
			<td><input type="text" name="id" id="id">
		</tr>
		<tr>
			<th><button type="button" id="btn_idck">ID중복체크</button>
			<td><div id="idck"></div>
		</tr>
		<tr>
			<th>비밀번호
			<td><input type="password" name="pw">
		</tr>
		<tr>
			<th>이름
			<td><input type="text" name="name">
		</tr>
		<tr>
			<th>핸드폰번호
			<td><input type="text" name="phone">
		</tr>
		<tr>
			<th>이메일
			<td><input type="text" name="email">
		</tr>
		<tr>
			<th>우편번호
			<td><input type="text" name="zipcode">
		</tr>
		<tr>
			<th>도로명주소
			<td><input type="text" name="address1">
		</tr>
		<tr>
			<th>상세주소
			<td><input type="text" name="address2">
		</tr>
		<tr>
			<td align=center>
				<button type="button" id="btn_home">뒤로가기</button>
			<td align=center>
				<button>회원가입</button>
		</tr>
	</table>
</body>
</html>