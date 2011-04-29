<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div id="global-nav">
   		<div class="container">
    		<ul>
                <c:if test="${!account.logged}">
	                <li id="global-nav-login"><strong><a href="${pageContext.request.contextPath}/account/login" rel="login">Login</a></strong></li>
	            </c:if>
	            <c:if test="${account.logged}">
		            <li class=""><a href="<c:url value="/account/logoff" />">Logoff</a></li>
	            </c:if>
            </ul>
        </div>
	</div>
    <div id="local-nav">
    	<div class="container">
        	<a href="/belerofonte" id="logo"><img src="" alt="Belerofonte" title="Belerofonte"></a>
            <ul id="account-nav" style="visibility: visible; ">
            	<li class=""><a href="<c:url value="/" />">Home</a></li>
				<li class=""><a href="<c:url value="/apps" />">Aplicacoes</a></li>
				<li class=""><a href="<c:url value="/games" />">Games</a></li>
                <c:if test="${!account.logged}">
					<li class=""><a href="<c:url value="/user/register" />">Registrar</a></li>
				</c:if>
				<c:if test="${account.logged}">
					<li class=""><a href="<c:url value="/account" />">Conta</a></li>
					<c:if test="${account.admin}">
						<li class=""><a href="<c:url value="/admin" />">Admin</a></li>
					</c:if>
				</c:if>			
        	</ul>
        	
    	</div>
	</div>
</div>