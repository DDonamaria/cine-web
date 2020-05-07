<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de imagenes del banner</title>
    <spring:url value="/resources" var="urlPublic" />
    <spring:url value="/banners/create" var="urlCreate" />
    <spring:url value="/banners/edit" var="urlEdit" />
    <spring:url value="/banners/delete" var="urlDelete" />
    <spring:url value="/banners/indexPaginate" var="urlBanners" />
    <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

    <!-- Fixed navbar -->
    <jsp:include page="../includes/navbar.jsp" />

    <div class="container theme-showcase" role="main">

      <h3>Listado de imagenes del Banner</h3>
      
       <c:if test="${mensaje !=null }">        
        		<div class='alert alert-success' role='alert'>${mensaje}</div>
        </c:if>	
      
      <a href="${urlCreate}" class="btn btn-success" role="button" title="Nuevo Banner" >Nuevo</a><br><br>

      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Titulo</th>                           
                <th>Nombre Archivo</th>
                <th>Estatus</th>
                <th>Opciones</th>              
            </tr>
            <c:forEach items="${banners.content}" var="banner">
	            <tr>
	                <td>${banner.id}</td>
	                <td>${banner.titulo}</td>
	                <td>${banner.archivo}</td>
	                <c:choose>
	                	<c:when test="${banner.estatus eq 'Activo'}">
	                		<td><span class="label label-success">${banner.estatus}</span></td>
	                	</c:when>
	                	<c:otherwise>
	                		<td><span class="label label-danger">${banner.estatus}</span></td>
	                	</c:otherwise>
	                </c:choose>
	                <td>
	                    <a href="${urlEdit}/${banner.id}" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
	                    <a href="${urlDelete}/${banner.id}" onclick='return confirm("¿Desea eliminar el banner?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
	                </td>
	            </tr>
            </c:forEach>
        </table>
        
        
        <nav aria-label="">
				<ul class="pager">
				
					<c:if test="${banners.number>0 }">
						<li><a href="${urlBanners}?page=${banners.number - 1 }">Anterior</a></li>
					</c:if>
				
					<c:if test="${banners.number<banners.totalPages -1}">
						<li><a href="${urlBanners}?page=${banners.number + 1 }">Siguiente</a></li>
					</c:if>
					
				</ul>
			</nav>
        
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
