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
					<div class="userinfo">
						<h3>${user.name}</h3>
						<label class="labelShow">Nome:</label>
						<label>${user.name}</label><br/>
						<label class="labelShow">Username:</label>
						<label>${user.username}</label><br/>
						<label class="labelShow">Email:</label>
						<label>${user.email}</label><br/><br/>
						<div class="userApps">
							<h3>Aplicações</h3>
							<c:if test="${empty user.files}">
								Não existem aplicações cadastradas.<br/><br/>
							</c:if>
							<c:forEach items="${user.files}" var="file">
								<li>
									<p><a href="<c:url value="/file/show/${file.id}" />">${file.name}</a>   ${file.numberOfDownloads} downloads</p>
								</li><br/>
							</c:forEach>
						</div>	
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
					</div>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>