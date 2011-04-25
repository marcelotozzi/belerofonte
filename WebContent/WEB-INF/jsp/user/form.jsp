<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Registrar</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<div style="width: 920px;">
						<form id="newUser" method="POST" action="<c:url value="/user" />">
							Nome: <input type="text" name="user.name"><br/>
							Username: <input type="text" name="user.username"><br/>
							Email: <input type="text" name="user.email"><br/>
							Senha: <input type="password" name="user.password"><br/>
							Confirma Senha: <input type="password" name="user.confirmPassword"><br/>
							<input type="submit" value="Registrar"> 
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>