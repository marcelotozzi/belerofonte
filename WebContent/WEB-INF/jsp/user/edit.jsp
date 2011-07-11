<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Editar >> ${account.user.username}</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<c:forEach var="error" items="${errors}">
    					${error.category} - ${error.message}<br />
					</c:forEach>
					<form id="editUser" method="post" enctype="multipart/form-data" action="<c:url value="/user" />">
						<div class="userinfo">
							<h3>Edite seus dados</h3>
							<input type="hidden" name="user.id" value="${user.id}"><br/>
							<label class="labelEdit">Nome:</label><input class="inputEdit" type="text" name="user.name" value="${user.name}"><br/>
							<label class="labelEdit">Username:</label><input class="inputEdit" type="text" name="user.username" value="${user.username}"><br/>
							<label class="labelEdit">Email:</label><input class="inputEdit" type="text" name="user.email" value="${user.email}"><br/>
							<label class="labelEdit">Nova senha:</label><input class="inputEdit" type="password" name="user.password" value="${user.password}"><br/>
							<label class="labelEdit">Repita senha:</label><input class="inputEdit" type="password" name="user.confirmPassword" value="${user.confirmPassword}"><br/>
							
							<button type="submit" value="put" name="_method">Alterar</button> 
						</div>
						<div class="userphoto">
							<h3>Foto</h3>
							<c:if test="${!empty urlPhoto}">
								<img class="userAvatar" alt="${user.username}" src="/belerofonte${urlPhoto}">
							</c:if>
							<c:if test="${empty urlPhoto}">
								<img class="userAvatar" alt="${user.username}" src="/belerofonte/image/avatar.jpg">
							</c:if>
							<br/><br/>
							<input type="file" id="photo" name="photo" value="${photo}" >
						</div>
					</form>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>