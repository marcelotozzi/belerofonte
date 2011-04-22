<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Erro 404! #FUUU</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">	
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<br/><br/>
					<div style="width: 800px; height: 400px; float: left; ">
						<h2>Ops!Essa página não existe!</h2>
						<br/>
						<label>Página não encontrada!</label><br/>
						<label>#FUUUUUUUUUUUUUU</label>
					</div>
					
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
</html>