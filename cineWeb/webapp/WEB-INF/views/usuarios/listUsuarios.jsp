<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de Usuarios</title>
    <spring:url value="/resources" var="urlPublic" />
    <spring:url value="/usuarios/create" var="urlCreate" />
    <spring:url value="/usuarios/index" var="urlUsuarios" />
    <spring:url value="/usuarios/delete" var="urlDelete" />
    <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

    <!-- Fixed navbar -->
    <jsp:include page="../includes/navbar.jsp" />

    <div class="container theme-showcase" role="main">

      <h3>Listado de Usuarios</h3>
      
      <c:if test="${mensaje !=null }">        
        		<div class='alert alert-success' role='alert'>${mensaje}</div>
      </c:if> 
      
      <!-- Solo un usuario ADMIN podra crear usuarios -->
      <a href="${urlCreate}" class="btn btn-success" role="button" title="Nuevo Usuario" >Nuevo</a><br><br>

      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Username</th>                           
                <th>Email</th>  
                <th>Activo</th>  
                <th>Opciones</th>          
            </tr>
            <c:forEach items="${usuarios}" var="usuario">
	            <tr>
	                <td>${usuario.id}</td>
	                <td>${usuario.username}</td>
	                <td>${usuario.email}</td>
	                <td>
	                	<c:choose>
							<c:when test="${usuario.activo eq '1'}">
								Habilitado
							</c:when>
							<c:otherwise>
								Deshabilitado
							</c:otherwise>
						</c:choose>	
	                </td>
	                <td>
						<a href="${urlDelete}/${usuario.id}" onclick='return confirm("¿Desea eliminar la pelicula?")' class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>
	                </td>
	                
	                
	            </tr>
            </c:forEach>
        </table>
        
        <!-- 
        <nav aria-label="">
			<ul class="pager">
				<li><a href="${urlBanners}?page=${banners.number - 1 }">Anterior</a></li>
				<li><a href="${urlBanners}?page=${banners.number + 1 }">Siguiente</a></li>
			</ul>
		</nav>  -->
        
      </div>  
      <hr class="featurette-divider">

      <!-- FOOTER -->
      <jsp:include page="../includes/footer.jsp" />

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>     
  </body>
</html>
