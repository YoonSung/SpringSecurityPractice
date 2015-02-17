<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script type="text/javascript" src="/javascript/jquery-1.11.2.js"></script>
<script>
	$(document).ready(function() {
		
		$("#loginBtn").click(function() {
			if ($("#loginId").val() == "") {
				alert("로그인 아이디를 입력해 주세요");
				$("#loginId").focus();
			} else if ($("#loginPw").val() == "") {
				alert("로그인 비밀번호를 입력해주세요");
				$("#loginPw").focus();
			} else {
				$("#loginForm").attr("action", "<c:url value='/j_spring_security_check'/>");
				$("#loginForm").submit();
			}
		});
	});

</script>
</head>
<body>
	<h3>Login Page</h3>
	<form id="loginForm" name="loginForm" method="POST" action="${ctx}/j_spring_security_check">
		<table>
			<tr>
				<td style="width:50px;">id</td>
				<td style="width: 150px;">
					<input style="width:145px;" type="text" id="loginId" name="loginId" value="" />
				</td>
			</tr>
			
			<tr>
				<td>pwd</td>
				<td>
					<input style="width: 145px;" type="text" id="loginPw" name="loginPw" value=""/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" id="loginBtn" value="로그인" />
				</td>
			</tr>
			
			<c:if test="${not empty param.failure}">
			<tr>
				<td colspan="2" style="color:red;">
					<p>Your login attempt was not successful, try again. </p>
					<p>Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
					<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
			</tr>
			</c:if>
		</table>
	</form>
</body>
</html>