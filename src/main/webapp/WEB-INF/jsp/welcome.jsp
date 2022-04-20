<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script type="text/javascript">
//Step 1: Get user coordinates
function getCoordintes() {
	var options = {
		enableHighAccuracy: true,
		timeout: 5000,
		maximumAge: 0
	};

	function success(pos) {
		var crd = pos.coords;
		var lat = crd.latitude.toString();
		var lng = crd.longitude.toString();
		var coordinates = [lat, lng];
		console.log(`Latitude: ${lat}, Longitude: ${lng}`);
		getCity(coordinates);
		return;

	}

	function error(err) {
		console.warn(`ERROR(${err.code}): ${err.message}`);
	}

	navigator.geolocation.getCurrentPosition(success, error, options);
}

// Step 2: Get city name
function getCity(coordinates) {
	var xhr = new XMLHttpRequest();
	var lat = coordinates[0];
	var lng = coordinates[1];

	// Paste your LocationIQ token below.
	xhr.open('GET', "https://us1.locationiq.com/v1/reverse.php?key=pk.22728e8ad2a81aaa26a31d348ee9d47a&lat=" +
	lat + "&lon=" + lng + "&format=json", true);
	xhr.send();
	xhr.onreadystatechange = processRequest;
	xhr.addEventListener("readystatechange", processRequest, false);

	function processRequest(e) {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = JSON.parse(xhr.responseText);
			var city = response.address.city;
			console.log(city);
			var container = '<input label="City" name="city" value="'+ city + '" type="hidden"/>';
			var div = document.querySelector(".location");
			div.innerHTML = container;
			return;
		}
	}
}

getCoordintes();

</script>
<commandfast:layout pageName="home"> 
    <div class="row">
        <div class ="col text-center">
            <spring:url value="/resources/images/Imagen1.png" htmlEscape="true" var="petsImage"/>
            <img class="img-fluid" src="${petsImage}" alt="Imagen de bienvenida" />
            <h2>Bienvenidos a Command-Fast, ¿desea consultar restaurantes cercanos que usen nuestra tecnología?</h2>
        	
        	<form:form class="form-horizontal" id="add-command-form">
        	<div class="location">
        	</div>
        		<button class="buton-detalles-listado mt-3 mb-3" type="submit"><span style="color: black;">Consultar</span></button>
        	</form:form>
        </div>
    </div>
    <h1>Restaurantes más valorados</h1>
    		<c:forEach items="${listaRestaurante}" var="restaurante">
			<div class="card base-card-listado mb-5 mt-5" style="width: auto;">
				<div class="row">

					<div class="col-3">
						<img id="imgCardRestaurant" src="${restaurante.photo}"
							class="rounded" alt="...">
					</div>
					<div class="col-9">
						<div class="card-body d-flex flex-column"
							style="padding-bottom: 0px;">
							<h1 class="card-title mb-3" style="color: #ffff;">
								<strong> <c:out value="${restaurante.name}" />
								</strong>
							</h1>
							<p class="card-text" style="color: #ffff; font-size: 16px;">
								<c:out value="${restaurante.city}" />
							</p>
							<p class="card-text" style="color: #ffff; font-size: 18px;">
								<c:out value="${restaurante.description}" />
							</p>
							<div class="col-12 mb-3">
								<c:forEach items="${restaurante.type}" var="tipos">
									<span class="badge"
										style="padding: 6px; font-size: 10px; background-color: #bb924b; margin-right: 6px;">
										<c:out value="${tipos}" />
									</span>
								</c:forEach>
							</div>
						</div>
						<div class="col-6 mb-3 mt-3 m-3">
							<spring:url value="/restaurante/{id}/detalles" var="url">
								<spring:param name="id" value="${restaurante.id}" />
							</spring:url>
							<p>
								<a type="button" class="buton-detalles-listado"
									href="${fn:escapeXml(url)}"
									style="text-decoration: none; color: black; font-size: 16px;">Ver
									detalles</a>
							</p>
							<spring:url value="/restaurante/{id2}/valoraciones" var="url2">
								<spring:param name="id2" value="${restaurante.id}" />
							</spring:url>
							<p>
								<a type="button" class="buton-detalles-listado"
									href="${fn:escapeXml(url2)}"
									style="text-decoration: none; color: black; font-size: 16px;">Opiniones de nuestros clientes</a>
							</p>

						</div>
					</div>


				</div>
			</div>
		</c:forEach>
    
</commandfast:layout>
