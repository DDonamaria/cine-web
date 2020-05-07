<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="author" content="">
		<title>Creacion de Usuarios</title>
		<spring:url value="/resources" var="urlPublic" />
		<spring:url value="/usuarios/save" var="urlSave" />
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">    
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	</head>

	<body>

		<!-- Fixed navbar -->
		<jsp:include page="../includes/navbar.jsp" />

		<div class="container theme-showcase" role="main">

			<h3>Datos del Usuario</h3>
			
			<c:if test="${mensaje !=null }">        
        		<div class='alert alert-success' role='alert'>${mensaje}</div>
        	</c:if> 

			<form:form action="${urlSave}" method="post" modelAttribute="usuario">
				<div class="row">         
					<div class="col-sm-3">
						<div class="form-group">
							<label for="tipoPerfil" class="control-label">PERFIL</label>              
							<select id="tipoPerfil" name="tipoPerfil" class="form-control">
								<option value="EDITOR">EDITOR</option>
								<option value="ADMIN">ADMIN</option>	
							</select>             
						</div> 
					</div>
				</div>	
				
				<div class="row"> 	
					<div class="col-sm-3">
						<div class="form-group">
							<label for="username">Username</label>             
							<form:input type="text" class="form-control" path="username" id="username" required="required"/>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="password">Password</label>             
							<form:input type="password" class="form-control" path="password" id="password" required="required"/>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="email">Email</label>
							<form:input type="text" class="form-control" path="email" id="email" placeholder="Correo electrónico" required="required"/>
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
