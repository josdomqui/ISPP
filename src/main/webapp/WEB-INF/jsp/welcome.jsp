<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
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
<petclinic:layout pageName="home"> 
    <div class="row">
        <div align="center">
            <spring:url value="/resources/images/Imagen1.png" htmlEscape="true" var="petsImage"/>
            <img class="rounded" alt="..." style="object-fit: cover;width: 27%; height: 85%" class="img-responsive" src="${petsImage}"/>
            <h2>Bienvenidos a Command-Fast, ¿desea consultar restaurantes cercanos que usen nuestra tecnología?</h2>
        	
        	<form:form class="form-horizontal" id="add-command-form">
        	<div class="location">
        	</div>
        		<button class="buton-detalles-listado mt-3 mb-3" type="submit"><span style="color: white;">Consultar</span></button>
        	</form:form>
        </div>
    </div>
</petclinic:layout>
