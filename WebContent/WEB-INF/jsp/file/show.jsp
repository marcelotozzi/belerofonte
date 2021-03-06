<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Arquivo | ${applicationFile.name}</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<h2>${file.name}</h2>
					Descri��o: ${file.description} <br/>
					Plataforma: <a href="<c:url value="/plataform/${file.plataform.id}"/>">${file.plataform.name}</a><br/>
					Categoria: <a href="<c:url value="/category/${file.applicationCategory.id}"/>">${file.applicationCategory.name}</a> <br/>
					Numero de Downloads: ${file.numberOfDownloads}<br/><br/>
					
					<a target="_blank" href="<c:url value="/file/download/${file.id}"/>">DOWNLOAD</a>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>