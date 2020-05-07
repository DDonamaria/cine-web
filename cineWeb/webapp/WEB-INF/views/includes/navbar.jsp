<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<spring:url value="/" var="urlRoot"/>

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
  
  	<security:authorize access="isAnonymous()">
  	 <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${urlRoot}">My CineSite</a>
    </div>
    
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">         
      	<li><a href="${urlRoot}about">Acerca</a></li>
      	<li><a href="${urlRoot}formLogin">Iniciar Sesion</a></li> 
      </ul>
    </div>
   </security:authorize>  
   
   <security:authorize access="hasAnyAuthority('EDITOR')">
     <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${urlRoot}">My CineSite | Administracion </a>
    </div>
    
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">         
      	<li><a href="${urlRoot}peliculas/indexPaginate?page=0">Peliculas</a></li>  
      	<li><a href="${urlRoot}horarios/indexPaginate?page=0">Horarios</a></li>
        <li><a href="${urlRoot}noticias/indexPaginate?page=0">Noticias</a></li>
      	<li><a href="${urlRoot}admin/logout">Cerrar Sesion</a></li>
      </ul>
    </div>
   </security:authorize>    
    
   <security:authorize access="hasAnyAuthority('ADMIN')">    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${urlRoot}">My CineSite | Administracion </a>
    </div>
    
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">         
      	<li><a href="${urlRoot}peliculas/indexPaginate?page=0">Peliculas</a></li>  
      	<li><a href="${urlRoot}horarios/indexPaginate?page=0">Horarios</a></li>
        <li><a href="${urlRoot}noticias/indexPaginate?page=0">Noticias</a></li>
      	<li><a href="${urlRoot}banners/indexPaginate?page=0">Banners</a></li>   
      	<li><a href="${urlRoot}usuarios/index">Usuarios</a></li>   
      	<li><a href="${urlRoot}admin/logout">Cerrar Sesion</a></li>
      </ul>
    </div>
   </security:authorize>    
    
  </div>
</nav>
