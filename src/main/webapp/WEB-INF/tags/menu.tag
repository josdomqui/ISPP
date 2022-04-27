<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <h1><a style="text-decoration: none; color: black;" href="/"><strong>Command Fast</strong></a></h1>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="glyphicon glyphicon-align-justify"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
        </li>
		    <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/restaurante/list"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-search" aria-hidden="true"></span> Restaurantes</a>
        </li>

        <sec:authorize access="!hasAuthority('restaurant')">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/command/new"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> Pedir</a>
        </li>
        </sec:authorize>

        <sec:authorize access="!hasAuthority('restaurant')">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/command/redirect/qr"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> Prueba QR</a>
        </li>
        </sec:authorize>
        
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/terms"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-duplicate" aria-hidden="true"></span> Condiciones de uso</a>

        </li>
        
        <sec:authorize access="hasAuthority('restaurant')">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/command/all"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Comandas</a>
        </li>
        </sec:authorize>
        
        <sec:authorize access="hasAuthority('restaurant')">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/restaurante/notifications"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span> Notificaciones</a>
        </li>
        </sec:authorize>
        
        <sec:authorize access="hasAuthority('admin')">
           <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/admin/paymentPanel"><span style="color: rgb(0,0,0);"  aria-hidden="true"></span>Administrador</a>
        </li>
        </sec:authorize>
        
        <sec:authorize access="!isAuthenticated()">
          	<li class="nav-item">
          		<a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/login"><span style="color:rgb(0,0,0);" class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&ensp;Iniciar sesion</a>
        	</li>
        </sec:authorize>
        
        <sec:authorize access="isAuthenticated()">
          <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/logout"><span style="color:rgb(0,0,0);" class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&ensp;Cerrar sesion</a>
        </li>
        </sec:authorize>
        
        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/restaurante/signup"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> Registrar restaurante</a>
        </li>
  		</sec:authorize>
  		
  		        <sec:authorize access="hasAuthority('restaurant')">
        <li class="nav-item">
          <a style="text-decoration: none; color: black;" class="navbar-text active" aria-current="page" href="/restaurante/editar"><span style="color: rgb(0,0,0);" class="glyphicon glyphicon-home" aria-hidden="true" ></span> Perfil</a>
        </li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>