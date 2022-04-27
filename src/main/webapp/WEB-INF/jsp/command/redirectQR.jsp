<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<commandfast:layout pageName="commandQR">
	<div class='container'>
		<div class='row'>
			<div class='col text-center'>
				<h1>Realizar una comanda usando QR</h1>
				<br>
				<img class="img-fluid" src="/resources/images/qrEjemplo.png" alt="Imagen de bienvenida" />

				<br>
				<p>El presente QR simula el que se situara en la mesa numero 1 del restaurante Gomez Martin, permitiendole realizar una 
				comanda para dicho restaurante (En el caso de que borre el restaurante Gomez Martin, este QR no será válido, por lo tanto
				la página dará error).</p>
					<spring:url value="/command/new/1/1" var="qrUrl">
					</spring:url>
				<p>La direccion web a la que redirige el QR al escanearlo es la siguiente: <a href="${fn:escapeXml(qrUrl)}">URL</a>	
			</div>
		</div>
	</div>

</commandfast:layout>
