<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Editar >> ${account.user.username}</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<div style="width: 920px;">
						<form id="editUser" action="<c:url value="/user" />">
							<input type="hidden" name="user.id" value="${user.id}"><br/>
							Nome: <input type="text" name="user.name" value="${user.name}"><br/>
							Username: <input type="text" name="user.username" value="${user.username}"><br/>
							Email: <input type="text" name="user.email" value="${user.email}"><br/>
							Nova senha: <input type="password" name="user.password" value="${user.password}"><br/>
							Confirma nova senha: <input type="password" name="user.confirmPassword" value="${user.confirmPassword}"><br/>
							<button type="submit" value="put" name="_method">Alterar</button> 
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>