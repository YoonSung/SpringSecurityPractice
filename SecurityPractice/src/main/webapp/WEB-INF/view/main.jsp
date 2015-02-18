<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="domain.MemberInfo" %>
<%

/*
	인증이 되지 않은 상태에서 auth.getPrincipal()하게되면 
	anonymousUser란 문자열이 있는 String 객체가 이 코드에서 return 된다.
*/
Authentication auth = SecurityContextHolder.getContext().getAuthentication();

Object principal = auth.getPrincipal();
String name = "";

if (principal != null && principal instanceof MemberInfo) {
	name = ((MemberInfo)principal).getName();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h3>Main Page</h3>
	<sec:authorize access="isAuthenticated()">
		<%=name%>님 반갑습니다
		<a href="${ctx}/logout">로그아웃</a>
	</sec:authorize>
	
	<ul>
		<sec:authorize access="hasRole('admin')">
			<li>관리자 화면</li>
		</sec:authorize>
	
		<sec:authorize access="permitAll">
			<li>비회원 게시판</li>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<li>준회원 게시판</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('member2', 'admin')">
			<li>정회원 확인</li>
		</sec:authorize>
	</ul>
</body>
</html>