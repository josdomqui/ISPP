<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<commandfast:layout pageName="commandRestaurant">

	<div class="container">
		<div class="row">
			<h1 class="col-md-12 p-2">Comandas</h1>
			<c:choose>
			<c:when test="${vacio == true}">
				<span style="color: white">No dispone de comandas actualmente</span>
		   	</c:when>
		   	<c:otherwise>
		   	<div class="card-deck">
		   		<c:forEach items="${listaComandas}" var="comanda">
		   			<div class="col-xs-12 col-lg-6">
						<div class="card text-left p-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; max-width:95%">
			  				<p>Número de la mesa: <c:out value="${comanda.mesa.id}"/></p>
				   			<p>Número de comensales: <c:out value="${comanda.costumers}"/></p>
				   			<p>Precio total: <c:out value="${comanda.price}"/> &euro;</p>
				   			<p>Platos:</p>
				   				<c:forEach items="${comanda.lines}" var="linea">
				   					<p>- <c:out value="${linea.plate.name} -> ${linea.quantity}"/><p>
				   				</c:forEach>
			   			</div>
			   		</div>
		   		</c:forEach>
		   	</div>
		   	</c:otherwise>
		   	</c:choose>
		   	
		   	
		</div>
	</div>
 			
</commandfast:layout>