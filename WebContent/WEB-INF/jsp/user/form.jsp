<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Registrar</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<div class="userinfo">
						<h3>Registre-se</h3>
						<c:forEach var="error" items="${errors}">
	    					${error.category} - ${error.message}<br />
						</c:forEach>
						<form id="newUser" method="POST" action="<c:url value="/user" />">
							<label class="labelRegister">Nome:</label><input class="inputRegister" type="text" name="user.name"><br/>
							<label class="labelRegister">Username:</label><input class="inputRegister" type="text" name="user.username"><br/>
							<label class="labelRegister">Email:</label><input class="inputRegister" type="text" name="user.email"><br/>
							<label class="labelRegister">Senha:</label><input class="inputRegister" type="password" name="user.password"><br/>
							<label class="labelRegister">Repita Senha:</label><input class="inputRegister" type="password" name="user.confirmPassword"><br/>
							<label class="labelRegister"></label><input class="inputRegister" type="submit" value="Registrar"> 
						</form>
					</div>	
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>