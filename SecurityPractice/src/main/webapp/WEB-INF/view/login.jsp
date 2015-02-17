<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
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
				<td>PWD</td>
				<td>
					<input style="width: 145px;" type="text" id="loginPw" name="loginPw" value=""/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" id="loginBtn" value="로그인" />
				</td>
		</table>
	</form>
</body>
</html>