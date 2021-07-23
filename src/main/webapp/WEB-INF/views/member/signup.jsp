<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {
		$("#btn_home").on("click", function() {
			location.href = "/"
		})

		$("#btn_idck").on("click", function() {
			//alert();

			$.ajax({
				url : "/member/duplCheck",
				data : {
					id : $("#id").val()
				}
			}).done(function(resp) {
				if (resp == "1") {
					$("#idck").text("이미 사용중인 Id 입니다.");
				} else {
					$("#idck").text("사용할 수 있는 Id 입니다.");
				}
			})
		})
	})
</script>

</head>
<body>
	<form action="/member/signupProc" method="post">

		<table align="center" border=1>
			<tr>
				<th colspan=2>회원가입
			</tr>
			<tr>
				<th>아이디
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
	</form>
</body>
</html>