<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Administração | Aplicativo | Registrar</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.6.1.min.js'/>"></script>
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<h3>Registre uma aplicação</h3>
						<form id="newFile" enctype="multipart/form-data" method="post" action="<c:url value='/admin/file/create' />">
							Informe os dados do arquivo<br/><br/>
							Nome: <input type="text" name="file.name" ><br/><br/>
							Descrição: <input type="text" name="file.description"><br/><br/>
							Plataforma: 
							<select id="selectPlataform">
							</select>
							Categoria:
							<select id="selectCategory">
							</select>
							
							<input type="hidden" name="file.plataform.id" id="filePlataform">
							<input type="hidden" name="file.applicationCategory.id" id="fileCategory">
							
							<input type="file" name="uploadedFile" id="uploadedFile"/>
													
							<input type="submit" id="submitFile"/>
						</form>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$.getJSON('<c:url value="/admin/plataforms.json"/>', function (json) {
				var html = '<option value="0"></option>';
			    var len = json.plataforms.length;
			    for (var i = 0; i< len; i++) {
			        html += '<option value="' + json.plataforms[i].id+ '">' + json.plataforms[i].name + '</option>';
			    }
			    $('#selectPlataform').append(html);
			});		
		});
		
		$(document).ready(function(){
			$.getJSON('<c:url value="/admin/categories.json"/>', function (json) {
				var html = '<option value="0"></option>';
			    var len = json.categories.length;
			    for (var i = 0; i< len; i++) {
			        html += '<option value="' + json.categories[i].id+ '">' + json.categories[i].name + '</option>';
			    }
			    $('#selectCategory').append(html);
			});	
		});
		
		$(document).ready(function(){
			$.getJSON('<c:url value="/admin/types.json"/>', function (json) {
				var html = '<option value="0"></option>';
				var len = json.types.length;
				for (var i = 0; i< len; i++) {
					html += '<option value="' + json.types[i].id+ '">' + json.types[i].name + '</option>';
				}
				$('#selectType').append(html);
			});	
		});
		
		$('#submitFile').live("click",function(){
			if($("#selectPlataform").val() != 0){
				$("#filePlataform").attr("value", $("#selectPlataform").val());				
			}
			
			if($("#selectCategory").val() != 0){
				$("#fileCategory").attr("value", $("#selectCategory").val());	
			}
		});
	</script>
</html>