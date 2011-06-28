<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div id="global-nav">
   		<div class="container">
    		<ul>
                <c:if test="${!account.logged}">
	                <li id="global-nav-login">
	                	<strong><a id="loginlink" href="${pageContext.request.contextPath}/account/login" rel="login">Login</a></strong>
	                </li>
	            </c:if>
	            <c:if test="${account.logged}">
		            <li class=""><a href="<c:url value="/account/logoff" />">Logoff</a></li>
	            </c:if>
            </ul>
        </div>
	</div>
    <div id="local-nav">
    	<div class="container">
        	<div>
            	<form method="post" action="<c:url value="http://localhost:8080/belerofonte/search" />">
                    <div>
                        <input type="text" value="" id="seek" name="seek">
                    	<input type="submit" value="Pesquisar"> 
                		<br class="clear">
                    </div>
            	</form>
            </div>
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
						<li class=""><a id="adminMenu" href="<c:url value="/admin" />">Admin</a></li>
					</c:if>
				</c:if>			
        	</ul>
        	
    	</div>
	</div>
</div>