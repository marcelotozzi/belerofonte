<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Belerofonte | Home</title>
		<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css"  />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.6.1.min.js'/>"></script>
	</head>
	<body>
		<c:import url="/public/header.jsp"></c:import>	
		<div id="content">
			<div class="container">
				<div class="box clearfix" id="dashboard">
					<div class="newapps">
						<h3>Novidades</h3>
						<ul id="recentApps">
						</ul>
					</div>
					<div class="mostdownloaded">
						<h3>Mais baixados</h3>
						<ul id="topDownloads">
						</ul>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/public/footer.jsp"></c:import>
	</body>
	<script type="text/javascript">
		$(document).ready(function(){
			$.getJSON('<c:url value="/files/recentApplications.json"/>', function (json) {
				var html = '';
			    var len = json.recentApps.length;
			    for (var i = 0; i< len; i++) {
			    	html += '<li><p><a href="<c:url value="/file/show/'+json.recentApps[i].id+'" />">'+json.recentApps[i].name+'</a>   '+json.recentApps[i].numberOfDownloads+' downloads</p></li>';
			    }
			    $('#recentApps').append(html);
			});
		});
		
		$(document).ready(function(){
			$.getJSON('<c:url value="/files/topDownloads.json"/>', function (json) {
				var html = '';
			    var len = json.topDownloads.length;
			    for (var i = 0; i< len; i++) {
			    	html += '<li><p><a href="<c:url value="/file/show/'+json.topDownloads[i].id+'" />">'+json.topDownloads[i].name+'</a>   '+json.topDownloads[i].numberOfDownloads+' downloads</p></li>';
			    }
			    $('#topDownloads').append(html);
			});
		});
	</script>
</html>