<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Administração | Categorias | Tipos</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">	
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<h3>Tipos</h3>
					<a href="category/register">Adicionar</a>
					<br/>
					<ul>
					<c:forEach var="type" items="${types}">
						<li>
							<p> - ${type.name} - <a href="<c:url value="category/type/${type.id}" />">Ver</a> - <a href="<c:url value="category/type/edit/${type.id}" />">Editar</a></p>
						</li><br/>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>	
	</body>
</html>