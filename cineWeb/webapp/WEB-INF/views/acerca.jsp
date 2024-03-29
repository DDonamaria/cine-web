<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>CineSite | Bienvenido</title>
	<spring:url value="/resources" var="urlPublic" />
		
	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	
	</head>

<body>

	<jsp:include page="includes/navbar.jsp" />
	
	<div class="container theme-showcase" role="main">

			<div class="container theme-showcase" role="main">
				<h3>Acerca de Nosotros</h3>
			</div>
			
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<p class="text-center">
							<img class="img-rounded" src="${urlPublic}/images/acerca.png" alt="Generic placeholder image" height="220">
						</p>
					</div>
					<div class="col-sm-9">
					
						<div class="panel-heading">
							<h3 class="panel-title">CineWeb</h3>
						</div>
						
						<div class="panel-body">
							<div class="alert alert-info" role="alert">
							Esta es la pagina web de CineWeb, una simulacion de una web de un cine imaginario donde poder ver el 
							catalogo de peliculas disponibles. Ha sido desarrollada con Java y Spring.
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<jsp:include page="includes/footer.jsp" />		

	</div> <!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js" ></script>
</body>
</html>