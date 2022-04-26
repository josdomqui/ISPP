<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>

<commandfast:layout pageName="commandQR">
	<div class='container'>
		<div class='row'>
			<div class='col text-center'>
				<h1>Realizar una comanda usando QR</h1>
				<br>
				<img class="img-fluid" src="${}" alt="Imagen de bienvenida" />

				<br>
				<p>El presente QR simula el que se situaría en la mesa numero X del restaurante X, permitiendole realizar una 
				comanda para dicho restaurante.</p>
				<p>La direccion web a la que redirige el QR al escanearlo es la siguiente: <a href='URL'>URL</a>	
			</div>
		</div>
	</div>

</commandfast:layout>
