<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Creacion de imagen para Banner</title>
      <spring:url value="/resources" var="urlPublic"></spring:url>    
      <spring:url value="/banners/save" var="urlSave" />
      <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
      <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">

   </head>

   <body>

      <!-- Fixed navbar -->
      <jsp:include page="../includes/navbar.jsp" />

      <div class="container theme-showcase" role="main">
			
         <h3 class="blog-title">Datos de la imagen</h3>
         
		 <spring:hasBindErrors name="banner">
			<div class="alert alert-danger" role="alert">
				Se han producido los siguientes errores:
				<ul>
					<c:forEach items="${errors.allErrors}" var="error">
						<li> <spring:message message="${error}" /> </li>
					</c:forEach>
				</ul>
			</div>
		 </spring:hasBindErrors>
		
         <form:form action="${urlSave}" method="post" enctype="multipart/form-data" modelAttribute="banner">
            <div class="row"> 
	            <div class="col-sm-3">
		            <div class="form-group">
		            	<img class="img-rounded" src="${urlPublic }/images/${banner.archivo}" title="Imagen actual del banner" width="1140" height="250">
		            </div>  
	            </div>  
	        </div>
	       	<div class="row">       
         		<div class="col-sm-6">
                  <div class="form-group">
                     <label for="titulo">Titulo</label>  
                     <form:hidden path="id"/>
                     <form:input type="text" class="form-control" path="titulo" id="titulo" required="required" />
                  </div>
               </div>
               
               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="estatus">Estatus</label>             
                     <form:select id="estatus" path="estatus" class="form-control">
                        <form:option value="Activo">Activo</form:option>
                        <form:option value="Inactivo">Inactivo</form:option>                
                     </form:select>  
                  </div>
               </div>
            </div>
            <div class="row">      
               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="imagen">Imagen</label>
                     <input type="file" id="archivoImagen" name="archivoImagen" />
                     <p class="help-block">Tama�o recomendado: 1140 x 250 </p>
                  </div> 
               </div> 
            </div>

            <button type="submit" class="btn btn-danger" >Guardar</button>
         </form:form> 

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
