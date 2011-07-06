<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Usuário | ${user.name}</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<h2>${user.name}</h2>
					Nome: ${user.name}"<br/>
					Username: ${user.username}"<br/>
					SEmail: ${user.email}"<br/>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>